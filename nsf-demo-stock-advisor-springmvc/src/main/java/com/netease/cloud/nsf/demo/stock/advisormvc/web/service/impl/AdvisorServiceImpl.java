package com.netease.cloud.nsf.demo.stock.advisormvc.web.service.impl;

import com.netease.cloud.nsf.demo.stock.advisormvc.web.entity.Stock;
import com.netease.cloud.nsf.demo.stock.advisormvc.web.service.IAdvisorService;
import com.netease.cloud.nsf.demo.stock.advisormvc.web.util.CastKit;
import com.netease.cloud.nsf.demo.stock.advisormvc.web.util.StringKit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class AdvisorServiceImpl implements IAdvisorService {

    private static Logger log = LoggerFactory.getLogger(AdvisorServiceImpl.class);

    ExecutorService scheduled = Executors.newScheduledThreadPool(3);

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Autowired
    RestTemplate restTemplate;

    @Value("${hot_stock_ids}")
    String hotStockIds;

    @Value("${stock_provider_url}")
    String stockProviderUrl;

    @Value("${stock_viewer_url}")
    String stockViewerrUrl;

    @Value("${error:true}")
    boolean errorSwitch;

    private int retryCount = -1;


    String stockProviderHost = "nsf-demo-stock-provider";

    @PostConstruct
    public void init() {
        URI uri = URI.create(stockProviderUrl);
        stockProviderHost = uri.getAuthority();

    }

    @Override
    public List<Stock> getHotStocks() throws Exception {

        log.info("getHotStocks is invoked with retry count = " + retryCount);
        retryCount++;
        if (errorSwitch && (retryCount % 5 != 0)) {
            throw new Exception();
        }

        List<Stock> stocks = null;
        Object[] params = {};
        if (StringKit.isEmpty(hotStockIds)) return stocks;

        String hotIds = getRecommendStockIds();
        try {
            String finalUrl = stockProviderUrl + "/stocks/" + hotIds;
            String stocksStr = restTemplate.getForObject(finalUrl, String.class, params);
            stocks = CastKit.str2StockList(stocksStr);
            Stock debugInfo = new Stock();
            debugInfo.setId("");
            debugInfo.setName("(第" + retryCount + "次请求)");
            stocks.add(debugInfo);
            log.info("get hot stocks from {} successful : {}", finalUrl, stocks);
        } catch (Exception e) {
            log.warn("get hot stocks failed", e);
        }

        return stocks;
    }

    /**
     * @return ids separated by comma
     * e.g. xx,yy,zz
     */
    private String getRecommendStockIds() {
        return hotStockIds;
    }

    @Override
    public List<String> batchHi() {

        List<String> results = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            String result = restTemplate.getForObject(stockProviderUrl + "/hi?p=" + i, String.class);
            results.add(result);
        }
        return results;
    }

    @Override
    public String deepInvoke(int times) {

        if (times-- > 0) {
            return restTemplate.getForObject(stockViewerrUrl + "/deepInvoke?times=" + times, String.class);
        }
        return "finish";
    }

    @Override
    public String echobyecho() {
        StringBuilder sBuilder = new StringBuilder();
        String url = stockProviderUrl + "/echobyecho";
        sBuilder.append(restTemplate.getForObject(url, String.class));
        return sBuilder.toString();
    }

    @Override
    public String echobyechoAsync() {
        StringBuilder sBuilder = new StringBuilder();
        String url = stockProviderUrl + "/echobyecho";
        CountDownLatch countDownLatch = new CountDownLatch(1);
        scheduled.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    sBuilder.append("advisor execute in " + Thread.currentThread().getName() + " get " + restTemplate.getForObject(url, String.class));
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    countDownLatch.countDown();
                }
            }
        });
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return sBuilder.toString();
    }

    @Override
    public String divide(HttpServletRequest request) {
        // TODO Auto-generated method stub
        List<String> results = new ArrayList<>();
        String host = stockProviderHost;
        String providerHost = null;
        HttpHeaders headers = new HttpHeaders();
        Enumeration<String> headerNames = request.getHeaderNames();
        String headerName = null;
        while (headerNames.hasMoreElements()) {
            headerName = headerNames.nextElement();
            if ("providerHost".equalsIgnoreCase(headerName)) {
                providerHost = request.getHeader(headerName);
                headers.add(headerName, request.getHeader(headerName));
            } else if ("host".equalsIgnoreCase(headerName)) {
                //ignore
            } else {
                headers.add(headerName, request.getHeader(headerName));
                log.info("headerName = " + headerName + " value = " + request.getHeader(headerName));
            }
        }
        if (providerHost != null) {
            host = providerHost;
        }
        headers.add("host", host);
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<MultiValueMap<String, String>>(null, headers);

        org.springframework.http.ResponseEntity<String> result = restTemplate.exchange("http://" + host + "/hi?" + request.getQueryString(), HttpMethod.GET, entity, String.class);

        return result.getBody();
    }

    @Override
    public String sendProvider(String content, int delay, String providerName) {
        if (StringUtils.isEmpty(providerName)) {
            providerName = stockProviderHost;
        }
        String url = String.format("http://%s/content?content=%s&delay=%d", providerName, content, delay);
        String result = restTemplate.getForObject(url, String.class);
        return result;
    }


}
