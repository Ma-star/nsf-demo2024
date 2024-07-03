package com.netease.cloud.nsf.demo.stock.viewer.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import com.alibaba.dubbo.config.annotation.Reference;
import com.netease.cloud.nsf.demo.stock.api.WallService;
//import com.netease.cloud.nsf.agent.core.circuitbreaker.NsfRateLimiterInvokeException;
//import com.netease.cloud.nsf.agent.core.exception.NsfRateLimiterException;
import com.netease.cloud.nsf.demo.stock.viewer.web.entity.HttpResponse;
import com.netease.cloud.nsf.demo.stock.viewer.web.entity.Stock;
import com.netease.cloud.nsf.demo.stock.viewer.web.manager.LogManager;
import com.netease.cloud.nsf.demo.stock.viewer.web.service.IStockService;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;


/**
 * @author Chen Jiahan | chenjiahan@corp.netease.com
 */
@Controller
public class PanelController implements EnvironmentAware {

    private static Logger log = LoggerFactory.getLogger(PanelController.class);

    private Environment environment;

    @Autowired
    IStockService stockService;

    @Reference
    WallService wallService;

    @Value("${dubbo:false}")
    String dubbo_switch;

    @GetMapping(value = {"", "/index"})
    public String indexPage() {
        if (dubbo_switch != null && dubbo_switch.equals("true")) return "index";
        return "index_non_dubbo";
    }

    @GetMapping("/exception")
    @ResponseBody
    public String exception(String msg) {
        if (!StringUtils.isEmpty(msg)) {
            throw new RuntimeException(msg);
        }
        return "no exception";
    }

    @GetMapping(value = "/stocks", produces = "application/json")
    @ResponseBody
    public HttpResponse getStockList(@RequestParam(name = "delay", required = false, defaultValue = "0") int delay) {

        List<Stock> stocks;
        try {
            stocks = stockService.getStockList(delay);
        } catch (Exception e) {
            log.warn("get stock list failed ...");
            log.warn("", e);
            return handleExceptionResponse(e);
        }
        return new HttpResponse(stocks);
    }

    @GetMapping(value = "/advices/hot", produces = "application/json")
    @ResponseBody
    public HttpResponse getHotAdvice() {

        List<Stock> stocks;
        try {
            stocks = stockService.getHotStockAdvice();
        } catch (Exception e) {
            e.printStackTrace();
            log.warn("get hot stock advice failed ...");
            log.warn("", e);
            return handleExceptionResponse(e);
        }
        return new HttpResponse(stocks);
    }

    @GetMapping("/stocks/{stockId}")
    @ResponseBody
    public Stock getStockById(@PathVariable String stockId) {

        Stock stock = null;
        try {
            stock = stockService.getStockById(stockId);
        } catch (Exception e) {
            e.printStackTrace();
            log.warn("get stock[{}] info failed ...", stockId);
        }
        return stock;
    }

    @GetMapping("/logs")
    @ResponseBody
    public HttpResponse getHttpLog() {
        return new HttpResponse(LogManager.logs());
    }

    @GetMapping("/logs/clear")
    @ResponseBody
    public HttpResponse clearLogs() {
        LogManager.clear();
        return new HttpResponse("clear logs success");
    }

    @GetMapping("/divide")
    @ResponseBody
    public String divide(HttpServletRequest request) {
        return stockService.divide(request);
    }

    @GetMapping("/echo/advisor")
    @ResponseBody
    public HttpResponse echoAdvisor(HttpServletRequest request,
                                    @RequestParam(name = "time", defaultValue = "10", required = false) int time) {
        String result = stockService.echoAdvisor(time);
        LogManager.put(UUID.randomUUID().toString(), result);
        return new HttpResponse(result);
    }

    @GetMapping("/echobyecho")
    @ResponseBody
    public HttpResponse echobyecho(HttpServletRequest request,
                                   @RequestParam(name = "time", defaultValue = "10", required = false) int time) {
        String result = stockService.echobyecho(time);
        LogManager.put(UUID.randomUUID().toString(), result);
        return new HttpResponse(result);
    }

    @GetMapping("/echobyechoAsync")
    @ResponseBody
    public HttpResponse echobyechoAsync(HttpServletRequest request,
                                        @RequestParam(name = "time", defaultValue = "10", required = false) int time) {
        String result = stockService.echobyechoAsync(time);
        LogManager.put(UUID.randomUUID().toString(), result);
        return new HttpResponse(result);
    }

    @GetMapping("/echo/provider")
    @ResponseBody
    public HttpResponse echoProvider(HttpServletRequest request,
                                     @RequestParam(name = "time", defaultValue = "10", required = false) int time) {
        String result = stockService.echoProvider(time);
        LogManager.put(UUID.randomUUID().toString(), result);
        return new HttpResponse(result);
    }


    @GetMapping("/send/provider")
    @ResponseBody
    public HttpResponse sendProvider(HttpServletRequest request,
                                     @RequestParam(name = "content", defaultValue = "test", required = false) String content,
                                     @RequestParam(name = "delay", defaultValue = "0", required = false) int delay,
                                     @RequestParam(name = "advisorName", required = false) String advisorName,
                                     @RequestParam(name = "providerName", required = false) String providerName
    ) {
        String result = stockService.sendProvider(content, delay, advisorName, providerName);
        LogManager.put(UUID.randomUUID().toString(), result);
        return new HttpResponse(result);
    }

    @PostMapping("/send/post")
    @ResponseBody
    public HttpResponse sendPost(HttpServletRequest request,
                                 @RequestBody String content,
                                 @RequestParam(name = "providerName", required = false) String providerName
    ) {
        String result = stockService.sendPost(content, providerName);
        LogManager.put(UUID.randomUUID().toString(), result);
        result = "req size:" + content.getBytes().length + ", resp size:" + result.getBytes().length + " | " + result;
        return new HttpResponse(result);
    }


    @GetMapping("/health")
    @ResponseBody
    public String health() {
        return "I am good!";
    }

    @RequestMapping("/deepInvoke")
    @ResponseBody
    public String deepInvoke(@RequestParam int times) {
        return stockService.deepInvoke(times);
    }

    private HttpResponse handleExceptionResponse(Exception e) {
        NsfExceptionUtil.NsfExceptionWrapper nsfException = NsfExceptionUtil.parseException(e);
        log.error(nsfException.getThrowable().getMessage());
        if (nsfException.getType() == NsfExceptionUtil.NsfExceptionType.NORMAL_EXCEPTION) {
            return new HttpResponse(nsfException.getThrowable().getMessage());
        }
        return new HttpResponse(nsfException.getType().getDesc());
    }

    //最大/最小差价
    @GetMapping("/spread")
    @ResponseBody
    public String getMaxSpreadStock() {
        String MaxSpreadStock = null;
        try {
            MaxSpreadStock = stockService.getMaxSpreadStock();
        } catch (Exception e) {
            log.warn("get max stock spread failed ...");
        }
        return MaxSpreadStock;
    }

    //预测股票数据
    @GetMapping("/predictPrice/{stockId}")
    @ResponseBody
    public String getPredictPriceById(@PathVariable String stockId) {
        String PredictPrice = null;
        try {
            PredictPrice = stockService.getPredictPriceById(stockId);
        } catch (Exception e) {
            log.warn("get predict stock price failed ...");
        }
        return PredictPrice;
    }

    @GetMapping(value = "/getConfigs")
    @ResponseBody
    public String getConfig(@RequestParam String key) {
        return environment.getProperty(key);
    }

    @GetMapping(value = "/callService")
    @ResponseBody
    public String callService(@RequestParam String service) {
        return stockService.callService(service);
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }


    private static class InnerParam {
        public String service;
        public String path;
        public String contentType;
        public String method;
        public String body;
        public Map<String, String> query;
    }

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ObjectMapper jsonMapper;

    @RequestMapping(value = "/inner")
    public ResponseEntity<String> inner(@RequestBody(required = false) String body) {

        InnerParam param = null;
        try {
            param = jsonMapper.readValue(body, InnerParam.class);
        } catch (Exception e) {

        }
        if (param == null) {
            InnerParam exampleParam = new InnerParam();
            exampleParam.service = "service-a";
            exampleParam.path = "/echo";
            exampleParam.contentType = MediaType.APPLICATION_JSON_UTF8_VALUE.toString();
            exampleParam.method = "POST";
            exampleParam.body = "{\"key\"=\"value\"}";
            Map<String, String> map = new HashMap<>();
            map.put("k1", "v1");
            exampleParam.query = map;
            String json = null;
            try {
                json = jsonMapper.writeValueAsString(exampleParam);
            } catch (JsonProcessingException e) {
                log.error(e.getMessage(), e);
            }
            return ResponseEntity.status(200).body("请提交RequestBody为以下格式的请求: </br> " + json);
        }

        StringBuilder urlBuilder = new StringBuilder("http://" + param.service + "/" + param.path);
        if (param.query != null && !param.query.isEmpty()) {
            urlBuilder.append("?");
            for (Map.Entry<String, String> entry : param.query.entrySet()) {
                urlBuilder.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
            }
            urlBuilder.deleteCharAt(urlBuilder.length() - 1);
        }
        HttpMethod httpMethod = HttpMethod.resolve(param.method.toUpperCase());
        MediaType mediaType = MediaType.parseMediaType(param.contentType);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(mediaType);

        HttpEntity<String> entity = new HttpEntity<>(param.body, headers);
        ResponseEntity<String> result = null;
        try {
            result = restTemplate.exchange(urlBuilder.toString(), httpMethod, entity, String.class);
        } catch (HttpStatusCodeException ex) {
            result = ResponseEntity
                    .status(ex.getStatusCode().value())
                    .headers(ex.getResponseHeaders())
                    .contentType(ex.getResponseHeaders().getContentType())
                    .body(ex.getResponseBodyAsString());
        }

        return result;
    }
}