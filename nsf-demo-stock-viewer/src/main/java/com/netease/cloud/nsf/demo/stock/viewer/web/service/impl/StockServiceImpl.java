package com.netease.cloud.nsf.demo.stock.viewer.web.service.impl;

import com.netease.cloud.nsf.demo.stock.viewer.web.entity.Stock;
import com.netease.cloud.nsf.demo.stock.viewer.web.service.IStockService;
import com.netease.cloud.nsf.demo.stock.viewer.web.util.CastKit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Service
public class StockServiceImpl implements IStockService {

    private static Logger log = LoggerFactory.getLogger(StockServiceImpl.class);

    @Autowired
    RestTemplate restTemplate;

    ExecutorService executorService = Executors.newFixedThreadPool(3);

    @Value("${stock_provider_url}")
    String stockProviderUrl;

    @Value("${stock_advisor_url}")
    String stockAdvisorUrl;

    @Value("${stock_predictor_url}")
    String stockPredictorUrl;

    String stockAdvisorHost = "nsf-demo-stock-advisor";
    String stockProviderHost = "nsf-demo-stock-provider";

    @PostConstruct
    public void init() {
        URI uri = URI.create(stockAdvisorUrl);
        stockAdvisorHost = uri.getAuthority();
        uri = URI.create(stockProviderUrl);
        stockProviderHost = uri.getAuthority();
    }

    // 超时
    @Override
    public List<Stock> getStockList(int delay) throws Exception {

        log.info("start to get stock list ...");

        List<Stock> stocks;
        String finalUrl = stockProviderUrl + "/stocks?delay=" + delay;
        String stocksStr = restTemplate.getForObject(finalUrl, String.class);
        stocks = CastKit.str2StockList(stocksStr);
        log.info("get all stocks from {} successful : {}", finalUrl, stocks);
        return stocks;
    }


    @Override
    public Stock getStockById(String stockId) throws Exception {

        Stock stock = null;
        String finalUrl = stockProviderUrl + "/stocks/" + stockId;

        HttpHeaders headers = new HttpHeaders();
        headers.set("stockCode", stockId);
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

        ResponseEntity<String> respEntity = restTemplate.exchange(finalUrl, HttpMethod.GET, entity, String.class);

        stock = CastKit.str2Stock(respEntity.getBody());
        log.info("get stock from {} by {} successful : {}", finalUrl, stockId, stock);
        return stock;
    }


    @Override
    public List<Stock> getHotStockAdvice() throws Exception {

        log.info("start to get hot stock advice ...");
        List<Stock> stocks;
//        counter++;
//        if (counter % 3 != 0) {
//            throw new Exception("cannot not fetch hot advice : connection failed");
//        }
        String finalUrl = stockAdvisorUrl + "/advices/hot";
        String stocksStr = restTemplate.getForObject(finalUrl, String.class);
        stocks = CastKit.str2StockList(stocksStr);
        log.info("get hot stock advice from {} successful : {}", finalUrl, stocks);

        return stocks;
    }


    public List<Stock> fallback(Throwable t) {
        log.info("fallback for hot stock advice ...");
        Stock stock = new Stock();
        stock.setId("1");
        stock.setName(t.getMessage() + " fallback执行");
        stock.setDailyKLineAddr("");
        stock.setOpeningPrice("");
        return Arrays.asList(stock);
    }


    @Override
    public String echoAdvisor(int times) {

        StringBuilder sBuilder = new StringBuilder();
        String url = stockAdvisorUrl + "/echo";
        while (times-- > 0) {
            sBuilder.append(restTemplate.getForObject(url + "?p=" + times, String.class));
        }
        return sBuilder.toString();
    }

    @Override
    public String echobyecho(int times) {
        StringBuilder sBuilder = new StringBuilder();
        String url = stockAdvisorUrl + "/echobyecho";
        while (times-- > 0) {
            sBuilder.append(restTemplate.getForObject(url + "?p=" + times, String.class));
        }
        return sBuilder.toString();
    }

    @Override
    public String echobyechoAsync(int times) {
        StringBuilder sBuilder = new StringBuilder();
        String url = stockAdvisorUrl + "/echobyechoAsync";
        while (times-- > 0) {
            Future<String> f = executorService.submit(new Callable<String>() {
                @Override
                public String call() throws Exception {
                    try {
                        return "viewer execute in " + Thread.currentThread().getName() + " get " + restTemplate.getForObject(url, String.class);
                    } catch (Exception e) {
                        e.printStackTrace();
                        return "error: " + e.getMessage();
                    }
                }
            });
            try {
                sBuilder.append(f.get());
            } catch (Exception e) {
                sBuilder.append(e.getMessage() + System.lineSeparator());
                e.printStackTrace();
            }
        }
        return sBuilder.toString();
    }

    @Override
    public String echoProvider(int times) {

        StringBuilder sBuilder = new StringBuilder();
        String url = stockProviderUrl + "/echo";
        while (times-- > 0) {
            String result;
            try {
                result = restTemplate.getForObject(url + "?p=" + times, String.class);
            } catch (Exception e) {
                result = e.getMessage() + "\r\n";
            }

            sBuilder.append(result);
        }
        return sBuilder.toString();
    }

    @Override
    public String sendProvider(String content, int delay, String advisorName, String providerName) {
        if (StringUtils.isEmpty(advisorName)) {
            advisorName = stockAdvisorHost;
        }
        if (StringUtils.isEmpty(providerName)) {
            providerName = "";
        }
        String url = String.format("http://%s/content?content=%s&delay=%d&providerName=%s", advisorName, content, delay, providerName);
        String result = restTemplate.getForObject(url, String.class);
        return result;
    }

    @Override
    public String sendPost(String content, String providerName) {
        if (StringUtils.isEmpty(providerName)) {
            providerName = stockProviderHost;
        }
        String url = String.format("http://%s/post", providerName);
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        HttpEntity<String> formEntity = new HttpEntity<String>(content, headers);
        String result = restTemplate.postForObject(url, formEntity, String.class);
        return result;
    }

    @Override
    public String deepInvoke(int times) {
        if (times-- > 0) {
            return restTemplate.getForObject(stockAdvisorUrl + "/deepInvoke?times=" + times, String.class);
        }
        return "finish";
    }

    @Override
    public String getMaxSpreadStock() {
        log.info("start to get max spread stock ...");
        String finalUrl = stockPredictorUrl + "/stocks/spread";
        String spread = restTemplate.getForObject(finalUrl, String.class);
        log.info("get max spread stock from {} successful : {}", finalUrl, spread);
        return spread;
    }

    @Override
    public String getPredictPriceById(String stockId) {
        log.info("start to get predict stock price...");
        String finalUrl = stockPredictorUrl + "/stocks/predictPrice/" + stockId;
        String predictPrice = restTemplate.getForObject(finalUrl, String.class);
        log.info("get predict stock price from {} successful : {}", finalUrl, predictPrice);
        return predictPrice;
    }

    @Override
    public String callService(String service) {
        String res = restTemplate.getForObject("http://" + service + "/echo", String.class);
        return res;
    }

    @Override
    public String divide(HttpServletRequest request) {
        List<String> results = new ArrayList<>();

        HttpHeaders headers = new HttpHeaders();
        Enumeration<String> headerNames = request.getHeaderNames();
        String headerName = null;
        String host = stockAdvisorHost;
        String advisorHost = null;
        while (headerNames.hasMoreElements()) {
            headerName = headerNames.nextElement();
            if ("advisorHost".equalsIgnoreCase(headerName)) {
                advisorHost = request.getHeader(headerName);
                headers.add(headerName, request.getHeader(headerName));
            } else if ("host".equalsIgnoreCase(headerName)) {
                //ignore
            } else {
                headers.add(headerName, request.getHeader(headerName));
                log.info("headerName = " + headerName + " value = " + request.getHeader(headerName));
            }
        }
        if (advisorHost != null) {
            host = advisorHost;
        }
        headers.add("host", host);
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<MultiValueMap<String, String>>(null, headers);

        org.springframework.http.ResponseEntity<String> result = restTemplate.exchange("http://" + host + "/divide?" + request.getQueryString(), HttpMethod.GET, entity, String.class);

        return result.getBody();
    }
}
