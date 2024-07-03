package com.netease.cloud.nsf.demo.stock.provider.web.controller;

import com.netease.cloud.nsf.demo.stock.provider.web.entity.Stock;
import com.netease.cloud.nsf.demo.stock.provider.web.service.IProviderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author Chen Jiahan | chenjiahan@corp.netease.com
 */
@RestController
public class StockController implements EnvironmentAware {

    private static Logger log = LoggerFactory.getLogger(StockController.class);

    @Autowired
    IProviderService stockService;

    Environment environment;


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

    /**
     * @param stockIds 以","分隔 , 单个id也可查询
     * @return
     * @throws InterruptedException
     */
    @GetMapping("/stocks/{stockIds}")
    public List<Stock> getStocksByIds(@PathVariable String stockIds,
                                      @RequestParam(name = "delay", required = false, defaultValue = "0") int delay) throws InterruptedException {
        Thread.sleep(delay * 1000);
        log.info("get /stocks/{} success", stockIds);
        return stockService.getStocksByIds(stockIds);
    }

    @GetMapping("/stocks")
    public List<Stock> getAllStocks(@RequestParam(name = "delay", required = false, defaultValue = "0") int delay)
            throws InterruptedException {
        Thread.sleep(delay * 1000);
        return stockService.getAllStocks();
    }

    @Value("${spring.application.name}")
    String name;

    @GetMapping("/hi")
    public String greeting(HttpServletRequest request) {
        String host = address.getHostAddress();
        return "greeting from " + name + "[" + host + ":" + port + "(" + mode + ",color:" + color + ")]" + System.lineSeparator();
    }

    @PostMapping("/post")
    public String post(@RequestBody String content) {
        log.info("post provider executed");
        String host = address.getHostAddress();
        return "post from " + name + "[" + host + ":" + port + "(" + mode + ",color:" + color + ")]   content:" + content + System.lineSeparator();
    }

    @GetMapping("/content")
    public String content(String content, int delay) {
        log.info(" provider content executed");
        try {
            TimeUnit.MILLISECONDS.sleep(delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if ("error".equals(content)) {
            throw new RuntimeException(content);
        }
        if ("rand".equals(content)) {
            if (count++ % 3 == 0) {
                throw new RuntimeException(content);
            }
        }
        String host = address.getHostAddress();
        return content + " from " + name + "[" + host + ":" + port + "(" + mode + ",color:" + color + ")]" + System.lineSeparator();
    }

    @GetMapping("/echo")
    public String echo(HttpServletRequest request) {
        log.info(" echo provider invoked");
        String host = address.getHostAddress();
        return "echo from " + name + "[" + host + ":" + port + "(" + mode + ",color:" + color + ")]" + System.lineSeparator();
    }

    @GetMapping("/echobyecho")
    public String echobyecho(HttpServletRequest request) {
        String host = address.getHostAddress();
        StringBuilder sb = new StringBuilder(" meta[");
        Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String header = (String) headerNames.nextElement();
            if (header.startsWith("nsf-biz-")) {
                sb.append(header).append(":").append(request.getHeader(header)).append(" ");
            }
        }
        sb.append("]");
        return "echo from " + name + "[" + host + ":" + port + "(" + mode + ")] color:" + color + sb.toString();
    }

    @PostMapping("echoPost")
    public String echoPost(@RequestBody String obj) {
        return obj;
    }

    @GetMapping("/health")
    @ResponseBody
    public String health() {
        return "I am good!";
    }


    @GetMapping("/conf")
    public String conf(String key) {
        return environment.getProperty(key);
    }

    /**
     * 熔断测试
     */
    int count = 0;

    @GetMapping("/sleepgw")
    public String sleepgw(HttpServletRequest request, String msg) throws InterruptedException {
        if (count++ % 5 < 3) {
            TimeUnit.SECONDS.sleep(10);
        }
        return "第" + count + "次sleepgw,参数:" + msg + ",响应服务地址:" + request.getServerName() + ":" + request.getServerPort();
    }

    @RequestMapping(value = "/error", method = {RequestMethod.GET, RequestMethod.POST,
            RequestMethod.PUT, RequestMethod.DELETE})
    public String error() {
        throw new RuntimeException("Manual throw ERROR");
    }

    /**
     * 测试网关自定义插件
     *
     * @param request
     * @return
     */
    @GetMapping("/getQueryString")
    public String getQueryString(HttpServletRequest request) {
        String result = "No Query String";

        Map<String, String[]> names = request.getParameterMap();
        if (name != null && names.size() > 0) {
            result = "";
            for (String key : names.keySet()) {
                result = result + key + " : " + names.get(key)[0] + "<br/>";
            }
        }

        return result;
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}
