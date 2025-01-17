package com.example.webservice.risk;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 3.4.2
 * 2021-03-04T14:51:34.472+08:00
 * Generated source version: 3.4.2
 *
 */
@WebService(targetNamespace = "http://tempuri.org/", name = "CustomerRiskInfoSoap")
@XmlSeeAlso({ObjectFactory.class})
public interface CustomerRiskInfoSoap {

    @WebMethod(operationName = "GetCustomerRiskInfo", action = "http://tempuri.org/GetCustomerRiskInfo")
    @RequestWrapper(localName = "GetCustomerRiskInfo", targetNamespace = "http://tempuri.org/", className = "com.example.webservice.risk.GetCustomerRiskInfo")
    @ResponseWrapper(localName = "GetCustomerRiskInfoResponse", targetNamespace = "http://tempuri.org/", className = "com.example.webservice.risk.GetCustomerRiskInfoResponse")
    @WebResult(name = "GetCustomerRiskInfoResult", targetNamespace = "http://tempuri.org/")
    public int getCustomerRiskInfo(

        @WebParam(name = "OrgCode", targetNamespace = "http://tempuri.org/")
        String orgCode,
        @WebParam(name = "PlanPutMoney", targetNamespace = "http://tempuri.org/")
        long planPutMoney,
        @WebParam(name = "Key", targetNamespace = "http://tempuri.org/")
        String key
    );

    @WebMethod(operationName = "CustomerLimitIsOut", action = "http://tempuri.org/CustomerLimitIsOut")
    @RequestWrapper(localName = "CustomerLimitIsOut", targetNamespace = "http://tempuri.org/", className = "com.example.webservice.risk.CustomerLimitIsOut")
    @ResponseWrapper(localName = "CustomerLimitIsOutResponse", targetNamespace = "http://tempuri.org/", className = "com.example.webservice.risk.CustomerLimitIsOutResponse")
    @WebResult(name = "CustomerLimitIsOutResult", targetNamespace = "http://tempuri.org/")
    public int customerLimitIsOut(

        @WebParam(name = "ProjectID", targetNamespace = "http://tempuri.org/")
        int projectID
    );
}
