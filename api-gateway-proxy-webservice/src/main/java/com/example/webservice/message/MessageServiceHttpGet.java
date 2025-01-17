package com.example.webservice.message;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * This class was generated by Apache CXF 3.4.2
 * 2021-03-04T14:52:21.602+08:00
 * Generated source version: 3.4.2
 *
 */
@WebService(targetNamespace = "http://tempuri.org/", name = "MessageServiceHttpGet")
@XmlSeeAlso({ObjectFactory.class})
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface MessageServiceHttpGet {

    @WebMethod(operationName = "QueryMessage")
    @WebResult(name = "string", targetNamespace = "http://tempuri.org/", partName = "Body")
    public String queryMessage(

        @WebParam(partName = "inputXml", name = "inputXml", targetNamespace = "http://tempuri.org/")
        String inputXml
    );
}
