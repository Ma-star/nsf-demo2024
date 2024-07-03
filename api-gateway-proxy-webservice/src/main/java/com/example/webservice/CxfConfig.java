package com.example.webservice;

import com.example.webservice.book.BookService;
import com.example.webservice.book.BookServiceSOAPImpl;
import com.example.webservice.common.CommonService;
import com.example.webservice.common.CommonServiceImpl;
import com.example.webservice.fund.FundInfoServiceSoap;
import com.example.webservice.fund.FundInfoServiceSoap12Impl;
import com.example.webservice.fund.FundInfoServiceSoapImpl;
import com.example.webservice.message.MessageServiceSoap;
import com.example.webservice.message.MessageServiceSoap12Impl;
import com.example.webservice.message.MessageServiceSoapImpl;
import com.example.webservice.project.ProjectInfoSoap;
import com.example.webservice.project.ProjectInfoSoap12Impl;
import com.example.webservice.project.ProjectInfoSoapImpl;
import com.example.webservice.receive.ReceivePutStateServiceSoap;
import com.example.webservice.receive.ReceivePutStateServiceSoap12Impl;
import com.example.webservice.receive.ReceivePutStateServiceSoapImpl;
import com.example.webservice.risk.CustomerRiskInfoSoap;
import com.example.webservice.risk.CustomerRiskInfoSoap12Impl;
import com.example.webservice.risk.CustomerRiskInfoSoapImpl;
import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.ws.Endpoint;

/**
 * @Author: Wang Dacheng(wangdacheng@corp.netease.com)
 * @Date: 创建时间: 2018/11/21 下午8:36.
 */
@Configuration
public class CxfConfig {
    @Autowired
    private Bus bus;

    CommonService commonService = new CommonServiceImpl();

    CustomerRiskInfoSoap customerRiskInfoSoap12 = new CustomerRiskInfoSoap12Impl();

    CustomerRiskInfoSoap customerRiskInfoSoap = new CustomerRiskInfoSoapImpl();

    BookService bookService = new BookServiceSOAPImpl();

    FundInfoServiceSoap fundInfoServiceSoap12 = new FundInfoServiceSoap12Impl();

    FundInfoServiceSoap fundInfoServiceSoap = new FundInfoServiceSoapImpl();

    MessageServiceSoap messageServiceSoap12 = new MessageServiceSoap12Impl();

    MessageServiceSoap messageServiceSoap = new MessageServiceSoapImpl();

    ProjectInfoSoap projectInfoSoap12 = new ProjectInfoSoap12Impl();

    ProjectInfoSoap projectInfoSoap = new ProjectInfoSoapImpl();

    ReceivePutStateServiceSoap receivePutStateServiceSoap12 = new ReceivePutStateServiceSoap12Impl();

    ReceivePutStateServiceSoap receivePutStateServiceSoap = new ReceivePutStateServiceSoapImpl();

    @Bean
    public Endpoint commonEndpoint() {
        EndpointImpl endpoint = new EndpointImpl(bus, commonService);
        endpoint.publish("/soap/common");
        return endpoint;
    }

    @Bean
    public Endpoint risk12Endpoint() {
        EndpointImpl endpoint = new EndpointImpl(bus, customerRiskInfoSoap12);
        endpoint.publish("/soap12/risk");
        return endpoint;
    }

    @Bean
    public Endpoint riskEndpoint() {
        EndpointImpl endpoint = new EndpointImpl(bus, customerRiskInfoSoap);
        endpoint.publish("/soap/risk");
        return endpoint;
    }

    @Bean
    public Endpoint bookEndpoint() {
        EndpointImpl endpoint = new EndpointImpl(bus, bookService);
        endpoint.publish("/soap/book");
        return endpoint;
    }

    @Bean
    public Endpoint fund12Endpoint() {
        EndpointImpl endpoint = new EndpointImpl(bus, fundInfoServiceSoap12);
        endpoint.publish("/soap12/fund");
        return endpoint;
    }

    @Bean
    public Endpoint fundEndpoint() {
        EndpointImpl endpoint = new EndpointImpl(bus, fundInfoServiceSoap);
        endpoint.publish("/soap/fund");
        return endpoint;
    }

    @Bean
    public Endpoint message12Endpoint() {
        EndpointImpl endpoint = new EndpointImpl(bus, messageServiceSoap12);
        endpoint.publish("/soap12/message");
        return endpoint;
    }

    @Bean
    public Endpoint messageEndpoint() {
        EndpointImpl endpoint = new EndpointImpl(bus, messageServiceSoap);
        endpoint.publish("/soap/message");
        return endpoint;
    }

    @Bean
    public Endpoint project12Endpoint() {
        EndpointImpl endpoint = new EndpointImpl(bus, projectInfoSoap12);
        endpoint.publish("/soap12/project");
        return endpoint;
    }

    @Bean
    public Endpoint projectEndpoint() {
        EndpointImpl endpoint = new EndpointImpl(bus, projectInfoSoap);
        endpoint.publish("/soap/project");
        return endpoint;
    }

    @Bean
    public Endpoint receive12Endpoint() {
        EndpointImpl endpoint = new EndpointImpl(bus, receivePutStateServiceSoap12);
        endpoint.publish("/soap12/receive");
        return endpoint;
    }

    @Bean
    public Endpoint receiveEndpoint() {
        EndpointImpl endpoint = new EndpointImpl(bus, receivePutStateServiceSoap);
        endpoint.publish("/soap/receive");
        return endpoint;
    }
}
