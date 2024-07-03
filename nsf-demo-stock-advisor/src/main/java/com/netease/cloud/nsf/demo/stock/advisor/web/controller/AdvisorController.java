package com.netease.cloud.nsf.demo.stock.advisor.web.controller;


import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netease.cloud.nsf.demo.stock.advisor.web.entity.Stock;
import com.netease.cloud.nsf.demo.stock.advisor.web.service.IAdvisorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class AdvisorController {

    private static Logger log = LoggerFactory.getLogger(AdvisorController.class);

    @Autowired
    IAdvisorService advisorService;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ObjectMapper jsonMapper;

    @Value("${server.port}")
    int port;
    private String mode = System.getProperty("mode", "unknow");
    private String color = System.getProperty("color", "");
    InetAddress address;

    {
        try {
            address = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    int count = 0;
	/*@Autowired
    TestJavaConfigBean testJavaConfigBean;*/

    private static class InnerParam {
        public String service;
        public String path;
        public String contentType;
        public String method;
        public String body;
        public Map<String, String> query;
    }


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

        HttpEntity<String> entity = new HttpEntity<>(body, headers);
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

    /**
     * 仅支持标准版状态码
     *
     * @param status [status]
     * @return 自定义返回码
     */
    @GetMapping("/http")
    public ResponseEntity<String> status(@RequestParam("status") String status) {
        log.info("status advisor invoked : {}", status);

        int code;
        try {
            code = Integer.parseInt(status);
            HttpStatus.valueOf(code);
        } catch (Exception e) {
            log.info("请输入正确的 HttpStatus 状态码.");
            code = 200;
        }

        return ResponseEntity.status(code)
                .body(JSONObject.toJSONString(getMap("return special code", status, "status", "/http")));
    }

    private Map<String, Object> getMap(String message, String code, String method, String path) {
        Map<String, Object> errorAttributes = new HashMap<>(4);
        errorAttributes.put("message", message);
        errorAttributes.put("code", code);
        errorAttributes.put("method", method);
        errorAttributes.put("path", path);
        return errorAttributes;
    }

    /**
     * 返回包含 [degrade] 字段的响应, 用于Mock返回200对特殊字段的处理
     */
    @GetMapping("/degrade")
    public String degrade() {
        log.info("degrade advisor invoked");
        String host = address.getHostAddress();

        String message = " from " + name + "[" + host + ":" + port + "(" + mode + ")]" + System.lineSeparator();
        return "degrade " + message;
    }

    @GetMapping("/advices/hot")
    public List<Stock> getHotAdvice() throws Exception {
        return advisorService.getHotStocks();
    }

    @GetMapping("/hi")
    public List<String> greeting() {
        return advisorService.batchHi();
    }

    @Value("${spring.application.name}")
    String name;


    @GetMapping("/content")
    public String content(String content, int delay, String providerName) {
        log.info("advisor content executed");
        if ("advisorerror".equals(content)) {
            throw new RuntimeException(content);
        }
        if ("advisorrand".equals(content)) {
            if (count++ % 3 == 0) {
                throw new RuntimeException(content);
            }
        }
        String host = address.getHostAddress();
        return content + " from " + name + "[" + host + ":" + port + "(" + mode + ",color:" + color + ")] | " + advisorService.sendProvider(content, delay, providerName);

    }

    @RequestMapping(value = "/echo", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
            RequestMethod.DELETE})
    public String echo(HttpServletRequest request) {
        log.info("echo advisor invoked");

        Map<String, String> headers = new HashMap<>();
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String name = headerNames.nextElement();
            headers.put(name, request.getHeader(name));
        }
        log.info("echo request headers:{}", headers);

        String host = address.getHostAddress();

        return "echo from " + name + "[" + host + ":" + port + "(" + mode + ",color:" + color + ")]" + System.lineSeparator();
    }

    @GetMapping("/echobyecho")
    public String echobyecho(HttpServletRequest request) {
        String host = address.getHostAddress();
        String color = request.getHeader("X-NSF-COLOR");
        StringBuilder sb = new StringBuilder(" meta[");
        Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String header = (String) headerNames.nextElement();
            if (header.startsWith("nsf-biz-")) {
                sb.append(header).append(":").append(request.getHeader(header)).append(" ");
            }
        }
        sb.append("]");
        String res = advisorService.echobyecho();
        return "echo from " + name + "[" + host + ":" + port + "(" + mode + ",color:" + color + ")] color:" + color + sb.toString() + " | " + res + System.lineSeparator();

    }

    @GetMapping("/echobyechoAsync")
    public String echobyechoAsync(HttpServletRequest request) {
        String host = address.getHostAddress();
        String color = request.getHeader("X-NSF-MARK");
        StringBuilder sb = new StringBuilder(" meta[");
        Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String header = (String) headerNames.nextElement();
            if (header.startsWith("nsf-biz-")) {
                sb.append(header).append(":").append(request.getHeader(header)).append(" ");
            }
        }
        sb.append("]");
        String res = advisorService.echobyechoAsync();
        return "echo from " + name + "[" + host + ":" + port + "(" + mode + ",color:" + color + ")] color:" + color + sb.toString() + " | " + res + System.lineSeparator();
    }

    @GetMapping("/health")
    @ResponseBody
    public String health() {
        return "I am good!";
    }

    @RequestMapping("/deepInvoke")
    @ResponseBody
    public String deepInvoke(@RequestParam int times) {
        return advisorService.deepInvoke(times);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public String register(@RequestBody String jsonString) {
        return "register json :\r\n" + jsonString;
    }

    @Value("${test:hi}")
    String test;

    @GetMapping("/test")
    public String TestApollo() {
        return test;
    }

    @Value("${test2}")
    String test2;

    @GetMapping("/test2")
    public String TestApollo2() {
        return test2;
    }

    @GetMapping("/divide")
    public String divide(HttpServletRequest request) {
        String host = address.getHostAddress();
        return "greeting from " + name + "[" + host + ":" + port + "(" + mode + ")]  |  " + advisorService.divide(request);
    }

    /* 监控统计响应码 */

    @GetMapping("/metric/success")
    public String getSuccessMsg() {
        log.info("/metric/success invoked");
        return "success";
    }

    @GetMapping("/metric/error")
    public String getErrorMsg() {
        log.info("/metric/error invoked");
        return "error";
    }
}
