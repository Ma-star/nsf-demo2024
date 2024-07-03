package com.netease.cloud.nsf.demo.stock.dubbo.echo.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.dubbo.rpc.RpcContext;
import com.netease.cloud.nsf.demo.stock.api.EchoExtraService;
import org.springframework.beans.factory.annotation.Value;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @Author chenjiahan | chenjiahan@corp.netease.com | 2019/10/15
 **/
@Service(version = "${echoversion:}")
public class EchoExtraServiceImpl implements EchoExtraService {


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

        return "greeting from dubbo A echo extra! service " + address.getHostAddress() + ":" + port + " (" + mode +",color:" + color +  ")";
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
        return "param time : " + time + ", echo from EchoExtraService [" + address.getHostAddress() + ":" + port + " (" + mode + ")] color:" + color + sb.toString();
    }

}
