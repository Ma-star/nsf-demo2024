package com.netease.cloud.nsf.demo.stock.dubbo.echo.service;

import ch.qos.logback.core.util.TimeUtil;
import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.dubbo.rpc.RpcContext;
import com.netease.cloud.nsf.demo.stock.api.EchoService;
import com.netease.cloud.nsf.demo.stock.api.entity.Stock;
import org.springframework.beans.factory.annotation.Value;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service(group = "B",version = "${echoversion:}")
public class AnotherEchoServiceImpl implements EchoService {

    private int count = 0;

    @Value("${nsf.port:10886}")
    public int port;
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

    @Override
    public String echo() {
        return "greeting from dubbo B echo service " + address.getHostAddress() + ":" + port + " (" + mode + ",color:" + color + ")";
    }

    @Override
    public String content(String content, int delay) {
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
        return content + " from dubbo B echo service " + address.getHostAddress() + ":" + port + " (" + mode + ",color:" + color + ")";
    }

    @Override
    public String echoStrAndInt(String str, Integer num) {
        return "B echo service  -- " + str + num + " from " + address.getHostAddress() + ":" + port + " (" + mode +",color:" + color +  ")";
    }

    @Override
    public String echoStrAndInt1(String str, Integer num) {

        if (str.equals("error") && count++ % 5 != 0) {
            throw new RuntimeException("something error");
        }

        return "B echo service  -- " + str + num + " from " + address.getHostAddress() + ":" + port + " (" + mode +",color:" + color +  ")" + " count:" + count;
    }

    @Override
    public void echoForReturnVoid(String str) {
        System.out.println(str);
    }

    @Override
    public List<String> echoForList(List<String> list) {
        return list;
    }

    @Override
    public Map<String, Object> echoForMap(Map<String, Object> map) {
        return map;
    }

    @Override
    public Stock echoForStock(String str, Stock stock) {
        stock.setName(stock.getName() + str);
        return stock;
    }

    @Override
    public String echobyecho(Integer time) {
        String color = RpcContext.getContext().getAttachment("X-NSF-COLOR");
        StringBuilder sb = new StringBuilder(" meta[");
        RpcContext.getContext().getAttachments().forEach((k, v) -> {
            if (k.startsWith("nsf-biz-")) {
                sb.append(k).append(":").append(v).append(" ");
            }
        });
        sb.append("]");
        return "param time : " + time + ", echo from AnotherEchoService groupB[" + address.getHostAddress() + ":" + port + " (" + mode + ")] color:" + color + sb.toString();
    }

    private String fallback(Throwable t) {
        return "fallback in dubbo B echo service " + address.getHostAddress() + ":" + port + " (" + mode + ")";
    }

    @Override
    public String getContextOnFixedSize(int size) {
        StringBuffer buf = new StringBuffer();
        String ALLCHAR = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            buf.append(ALLCHAR.charAt(random.nextInt(ALLCHAR.length())));
        }
        return buf.toString();
    }
}
