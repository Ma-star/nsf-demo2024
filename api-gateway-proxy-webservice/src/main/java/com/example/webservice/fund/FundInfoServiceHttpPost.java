package com.example.webservice.fund;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * This class was generated by Apache CXF 3.4.2
 * 2021-03-04T14:52:00.848+08:00
 * Generated source version: 3.4.2
 *
 */
@WebService(targetNamespace = "http://tempuri.org/", name = "FundInfoServiceHttpPost")
@XmlSeeAlso({ObjectFactory.class})
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface FundInfoServiceHttpPost {

    /**
     * S1项目合同接口
     *       
     */
    @WebMethod(operationName = "SearchContractsByProjectcode")
    @WebResult(name = "string", targetNamespace = "http://tempuri.org/", partName = "Body")
    public String searchContractsByProjectcode(

        @WebParam(partName = "PROJECT_NUMBER", name = "PROJECT_NUMBER", targetNamespace = "http://tempuri.org/")
        String projectNUMBER,
        @WebParam(partName = "MASTER_NO", name = "MASTER_NO", targetNamespace = "http://tempuri.org/")
        String masterNO
    );

    @WebMethod(operationName = "HelloWorld")
    @WebResult(name = "string", targetNamespace = "http://tempuri.org/", partName = "Body")
    public String helloWorld()
;

    /**
     * S3项目产品信息登记接口
     *       
     */
    @WebMethod(operationName = "FundInfoConfirm")
    @WebResult(name = "string", targetNamespace = "http://tempuri.org/", partName = "Body")
    public String fundInfoConfirm(

        @WebParam(partName = "PROJECT_NUMBER", name = "PROJECT_NUMBER", targetNamespace = "http://tempuri.org/")
        String projectNUMBER,
        @WebParam(partName = "FUND_STATUS", name = "FUND_STATUS", targetNamespace = "http://tempuri.org/")
        String fundSTATUS
    );
}
