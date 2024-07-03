package com.netease.cloud.nsf.demo.stock.dubbo.echo.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.dubbo.rpc.RpcContext;
import com.netease.cloud.nsf.demo.stock.api.EchoService;
import com.netease.cloud.nsf.demo.stock.api.entity.Stock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service(group = "A", version = "${echoversion:}")
public class EchoServiceImpl implements EchoService {
    public static final Logger logger = LoggerFactory.getLogger(EchoServiceImpl.class.getName());

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

        return "greeting from dubbo A echo service " + address.getHostAddress() + ":" + port + " (" + mode + ",color:" + color + ")";
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
                System.out.println("rand error。。。");
                throw new RuntimeException(content);
            }
        }
        Map<String, String> attachments = RpcContext.getContext().getAttachments();
        RpcContext.getContext().getMethodName();
        logger.info("Request received attachments:{} content:{} delay:{}.", attachments, content, delay);

        String color = Optional.ofNullable(attachments.get("x-nsf-mark")).orElse("");
        return String.format(
            "Response from echo service [IP:%s Port:%s Content:%s Mode:%s color:%s Attachments:%s Method:%s]",
            address.getHostAddress(), port, content, "", color, attachments, RpcContext.getContext().getMethodName());
    }

    @Override
    public String echoStrAndInt(String str, Integer num) {

        return "A echo service  -- " + str + num + " from " + address.getHostAddress() + ":" + port + " (" + mode + ",color:" + color + ")";
    }

    @Override
    public String echoStrAndInt1(String str, Integer num) {

        if (str.equals("error") && count++ % 5 != 0) {
            throw new RuntimeException("something error");
        }


        return "A echo service  -- " + str + num + " from " + address.getHostAddress() + ":" + port + " (" + mode + ",color:" + color + ")" + " count:" + count;
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
        StringBuilder sb = new StringBuilder(" meta[");
        RpcContext.getContext().getAttachments().forEach((k, v) -> {
            if (k.startsWith("nsf-biz-")) {
                sb.append(k).append(":").append(v).append(" ");
            }
        });
        sb.append("]");
        return "param time : " + time + ", echo from EchoService groupA[" + address.getHostAddress() + ":" + port + " (" + mode + ")] color:" + color + sb.toString();
    }

    /**
     * @param size 返回最大2^31-1=2147483647个字符
     * @return
     */
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

    private String fallback(Throwable t) {
        return "fallback in dubbo A echo service " + address.getHostAddress() + ":" + port + " (" + mode + ")";
    }
}
