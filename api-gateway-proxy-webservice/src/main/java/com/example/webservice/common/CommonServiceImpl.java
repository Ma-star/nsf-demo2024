package com.example.webservice.common;

import org.springframework.stereotype.Component;

import javax.jws.WebService;

/**
 * @Author: Wang Dacheng(wangdacheng@corp.netease.com)
 * @Date: 创建时间: 2018/11/21 下午8:32.
 */
@WebService(serviceName = "CommonService", // 与接口中指定的name一致
        targetNamespace = "http://service.webservice.example.cm/",
        endpointInterface = "com.example.webservice.common.CommonService"
)
public class CommonServiceImpl implements CommonService {

    @Override
    public String sayHello(String name) {

        return "Hello ," + name;
    }

    @Override
    public User getNameAndAge(User user) {
        return user;
    }

    @Override
    public String getNameAndAge1(String name, int age) {
        return name;
    }

    @Override
    public User getUserInfo(User user, String name) {
        user.setUserName(name);
        return user;
    }

}

