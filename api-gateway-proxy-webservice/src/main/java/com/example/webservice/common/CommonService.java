package com.example.webservice.common;

import com.example.webservice.common.User;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

/**
 * @Author: Wang Dacheng(wangdacheng@corp.netease.com)
 * @Date: 创建时间: 2018/11/21 下午8:32.
 */

@WebService(name = "CommonService", // 暴露服务名称
        targetNamespace = "http://service.webservice.example.com/")
public interface CommonService {
    @WebMethod
    @WebResult(name = "String", targetNamespace = "http://service.webservice.example.com/")
    String sayHello(@WebParam(name = "userName") String name);

    @WebMethod
    @WebResult(name = "Object", targetNamespace = "http://service.webservice.example.com/")
    User getNameAndAge(@WebParam(name = "user") User user);

    @WebMethod
    @WebResult(name = "Object", targetNamespace = "http://service.webservice.example.com/")
    String getNameAndAge1(@WebParam(name = "userName") String name, @WebParam(name = "age") int age);

    @WebMethod
    @WebResult(name = "Object", targetNamespace = "http://service.webservice.example.com/")
    User getUserInfo(@WebParam(name = "user") User user, @WebParam(name = "name") String name);

}
