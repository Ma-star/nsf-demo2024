package com.netease.cloud.nsf.demo.stock.viewer.web.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.rpc.RpcContext;
import com.netease.cloud.nsf.demo.stock.api.WallService;
import com.netease.cloud.nsf.demo.stock.api.entity.Stock;
import com.netease.cloud.nsf.demo.stock.viewer.web.entity.HttpResponse;
import com.netease.cloud.nsf.demo.stock.viewer.web.manager.LogManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/dubbo")
@ConditionalOnProperty(name = "dubbo", havingValue = "true")
public class DubboController {

    private static Logger log = LoggerFactory.getLogger(DubboController.class);


    @Value("${wallversion:}")
    String wallversion;

    @Reference(timeout = 30000, version = "${wallversion:}", retries = -1, check = false)
    WallService wallService;

    @Reference(timeout = 30000, version = "${agentwallversion:agent}", retries = -1, check = false)
    WallService agentWallService;

    @Reference(timeout = 30000, version = "${sidecarwallversion:sidecar}", retries = -1, check = false)
    WallService sidecarWallService;

    @Reference(timeout = 30000, version = "${aswallversion:}", retries = -1, check = false)
    WallService asWallService;

    @GetMapping(value = "/stocks", produces = "application/json")
    @ResponseBody
    public Map<String, Object> getStockList(@RequestParam(name = "delay", required = false, defaultValue = "0") int delay) {

        Map<String, Object> resultMap = new HashMap<>();
        List<Stock> stocks = new ArrayList<>();
        try {
            stocks = wallService.getStockList(delay);
        } catch (Exception e) {
            log.warn("get stock list failed ...");
        }
        resultMap.put("stocks", stocks);
        return resultMap;
    }

    @GetMapping(value = "/advices/hot", produces = "application/json")
    @ResponseBody
    public Map<String, Object> getHotAdvice() {

        Map<String, Object> resultMap = new HashMap<>();
        List<Stock> stocks = new ArrayList<>();
        try {
            stocks = wallService.getHotAdvice();
        } catch (Exception e) {
            log.warn("get hot stock advice failed ...");
            Stock stock = new Stock();
            stock.setName("Cannot get any advice from dubbo service");
            stocks.add(stock);
            resultMap.put("stocks", stocks);
            return resultMap;
        }
        resultMap.put("stocks", stocks);
        return resultMap;
    }

    @GetMapping("/stocks/{stockId}")
    @ResponseBody
    public Stock getStockById(@PathVariable String stockId) {

        Stock stock = null;
        try {
            stock = wallService.getStockById(stockId);
        } catch (Exception e) {
            log.warn("get stock[{}] info failed ...", stockId);
        }
        return stock;
    }

    @GetMapping("/echobyecho")
    @ResponseBody
    public HttpResponse echobyecho(HttpServletRequest request,
                                   @RequestParam(name = "time", defaultValue = "10", required = false) int time) {
        String result = wallService.echobyecho(time);
        LogManager.put(UUID.randomUUID().toString(), result);
        return new HttpResponse(result);
    }

    @GetMapping("/echo/advisor")
    @ResponseBody
    public String echoAdvisor(HttpServletRequest request) {
        String result = wallService.echoAdvisor();
        LogManager.put(UUID.randomUUID().toString(), result);
        return result;
    }

    @GetMapping("/echo/provider")
    @ResponseBody
    public String echoProvider(HttpServletRequest request) {
        String result = wallService.echoProvider();
        LogManager.put(UUID.randomUUID().toString(), result);
        return result;
    }

    @GetMapping("/send/provider")
    @ResponseBody
    public HttpResponse sendProvider(HttpServletRequest request,
                                     @RequestParam(required = false) Map<String, String> parameters) {
        String delayKey = "delay";
        String contentKey = "content";
        String wallKey = "wall";
        int delay = 0;
        String content = "test";
        String serviceVersion = wallversion;
        if (Objects.nonNull(parameters) && !parameters.isEmpty()) {
            if (parameters.containsKey(delayKey)) {
                try {
                    delay = Integer.parseInt(parameters.get(delayKey));
                } catch (NumberFormatException e) {
                    log.warn("Unable parse delay from request:{}.Will use default delay.", parameters);
                }
                parameters.remove(delayKey);
            }
            if (parameters.containsKey(contentKey)) {
                content = parameters.get(contentKey);
                parameters.remove(contentKey);
            }
            if (parameters.containsKey(wallKey)) {
                serviceVersion = parameters.get(wallKey);
                parameters.remove(wallKey);
            }
        }

        if (Objects.nonNull(parameters) && !parameters.isEmpty()) {
            RpcContext.getContext().setAttachments(parameters);
            log.info("Viewer will send request with attachments:{} to wall service.", parameters);
        }

        String result;
        if ("agent".equalsIgnoreCase(serviceVersion)) {
            result = agentWallService.content(content, delay);
        } else if ("sidecar".equalsIgnoreCase(serviceVersion)) {
            result = sidecarWallService.content(content, delay);
        } else {
            result = asWallService.content(content, delay);
        }
        LogManager.put(UUID.randomUUID().toString(), result);
        return new HttpResponse(result);
    }

    @GetMapping("/getConfigs")
    @ResponseBody
    public String getConfig(@RequestParam String namespace) {
        return wallService.getConfig(namespace);
    }

    @GetMapping("/getDebugConfigs")
    public Object getConfig(@RequestParam(name = "key", required = false, defaultValue = "") String preFix,
                            @RequestParam(name = "type", required = false, defaultValue = "html") String type) {
        return wallService.getDebugConfig(preFix, type);
    }

    @GetMapping("/echoStr")
    @ResponseBody
    public String echoStr(@RequestParam String key,
                          @RequestParam int num) {

        String result = wallService.echoStrAndInt(key, num);
        LogManager.put(UUID.randomUUID().toString(), result);
        return result;
    }

    @GetMapping("/echoStr1")
    @ResponseBody
    public String echoStr1(@RequestParam String key,
                           @RequestParam int num) {

        String result = wallService.echoStrAndInt1(key, num);
        LogManager.put(UUID.randomUUID().toString(), result);
        return result;
    }


    @GetMapping("/echo/advisorB")
    @ResponseBody
    public String echoAdvisorB(HttpServletRequest request) {
        String result = wallService.echoAdvisorB();
        LogManager.put(UUID.randomUUID().toString(), result);
        return result;
    }

    @GetMapping("/echo/providerB")
    @ResponseBody
    public String echoProviderB(HttpServletRequest request) {
        String result = wallService.echoProviderB();
        LogManager.put(UUID.randomUUID().toString(), result);
        return result;
    }

    @GetMapping("/echoStrB")
    @ResponseBody
    public String echoStrB(@RequestParam String key,
                           @RequestParam int num) {

        String result = wallService.echoStrAndIntB(key, num);
        LogManager.put(UUID.randomUUID().toString(), result);
        return result;
    }

    @GetMapping("/echoStr1B")
    @ResponseBody
    public String echoStr1B(@RequestParam String key,
                            @RequestParam int num) {

        String result = wallService.echoStrAndInt1B(key, num);
        LogManager.put(UUID.randomUUID().toString(), result);
        return result;
    }

    /**
     * @param size 最大值为Integer.MAX_VALUE
     * @return
     */
    @GetMapping("/getContextOnFixedSize")
    @ResponseBody
    public String getContextOnFixedSize(@RequestParam int size) {
        String result = wallService.getContextOnFixedSize(size);
        LogManager.put(UUID.randomUUID().toString(), result);
        return result;
    }

}
