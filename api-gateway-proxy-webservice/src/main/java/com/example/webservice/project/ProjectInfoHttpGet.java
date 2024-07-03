package com.example.webservice.project;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * This class was generated by Apache CXF 3.4.2
 * 2021-03-04T14:52:39.932+08:00
 * Generated source version: 3.4.2
 *
 */
@WebService(targetNamespace = "http://tempuri.org/", name = "ProjectInfoHttpGet")
@XmlSeeAlso({ObjectFactory.class})
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface ProjectInfoHttpGet {

    @WebMethod(operationName = "GetProjectName")
    @WebResult(name = "string", targetNamespace = "http://tempuri.org/", partName = "Body")
    public String getProjectName(

        @WebParam(partName = "ProjectID", name = "ProjectID", targetNamespace = "http://tempuri.org/")
        String projectID
    );

    @WebMethod(operationName = "GetProjectCode")
    @WebResult(name = "string", targetNamespace = "http://tempuri.org/", partName = "Body")
    public String getProjectCode(

        @WebParam(partName = "ProjectID", name = "ProjectID", targetNamespace = "http://tempuri.org/")
        String projectID
    );
}