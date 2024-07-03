package com.example.webservice.receive;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import java.net.URL;

/**
 * This class was generated by Apache CXF 3.4.2
 * 2021-03-04T14:53:04.248+08:00
 * Generated source version: 3.4.2
 *
 */
@WebServiceClient(name = "ReceivePutStateService",
                  wsdlLocation = "classpath:wsdl/ReceivePutStateService.xml",
                  targetNamespace = "http://tempuri.org/")
public class ReceivePutStateService extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://tempuri.org/", "ReceivePutStateService");
    public final static QName ReceivePutStateServiceHttpGet = new QName("http://tempuri.org/", "ReceivePutStateServiceHttpGet");
    public final static QName ReceivePutStateServiceHttpPost = new QName("http://tempuri.org/", "ReceivePutStateServiceHttpPost");
    public final static QName ReceivePutStateServiceSoap = new QName("http://tempuri.org/", "ReceivePutStateServiceSoap");
    public final static QName ReceivePutStateServiceSoap12 = new QName("http://tempuri.org/", "ReceivePutStateServiceSoap12");
    static {
        URL url = ReceivePutStateService.class.getClassLoader().getResource("wsdl/ReceivePutStateService.xml");
        if (url == null) {
            java.util.logging.Logger.getLogger(ReceivePutStateService.class.getName())
                .log(java.util.logging.Level.INFO,
                     "Can not initialize the default wsdl from {0}", "classpath:wsdl/ReceivePutStateService.xml");
        }
        WSDL_LOCATION = url;
    }

    public ReceivePutStateService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public ReceivePutStateService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public ReceivePutStateService() {
        super(WSDL_LOCATION, SERVICE);
    }

    public ReceivePutStateService(WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    public ReceivePutStateService(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE, features);
    }

    public ReceivePutStateService(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
        super(wsdlLocation, serviceName, features);
    }




    /**
     *
     * @return
     *     returns ReceivePutStateServiceHttpGet
     */
    @WebEndpoint(name = "ReceivePutStateServiceHttpGet")
    public ReceivePutStateServiceHttpGet getReceivePutStateServiceHttpGet() {
        return super.getPort(ReceivePutStateServiceHttpGet, ReceivePutStateServiceHttpGet.class);
    }

    /**
     *
     * @param features
     *     A list of {@link WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns ReceivePutStateServiceHttpGet
     */
    @WebEndpoint(name = "ReceivePutStateServiceHttpGet")
    public ReceivePutStateServiceHttpGet getReceivePutStateServiceHttpGet(WebServiceFeature... features) {
        return super.getPort(ReceivePutStateServiceHttpGet, ReceivePutStateServiceHttpGet.class, features);
    }


    /**
     *
     * @return
     *     returns ReceivePutStateServiceHttpPost
     */
    @WebEndpoint(name = "ReceivePutStateServiceHttpPost")
    public ReceivePutStateServiceHttpPost getReceivePutStateServiceHttpPost() {
        return super.getPort(ReceivePutStateServiceHttpPost, ReceivePutStateServiceHttpPost.class);
    }

    /**
     *
     * @param features
     *     A list of {@link WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns ReceivePutStateServiceHttpPost
     */
    @WebEndpoint(name = "ReceivePutStateServiceHttpPost")
    public ReceivePutStateServiceHttpPost getReceivePutStateServiceHttpPost(WebServiceFeature... features) {
        return super.getPort(ReceivePutStateServiceHttpPost, ReceivePutStateServiceHttpPost.class, features);
    }


    /**
     *
     * @return
     *     returns ReceivePutStateServiceSoap
     */
    @WebEndpoint(name = "ReceivePutStateServiceSoap")
    public ReceivePutStateServiceSoap getReceivePutStateServiceSoap() {
        return super.getPort(ReceivePutStateServiceSoap, ReceivePutStateServiceSoap.class);
    }

    /**
     *
     * @param features
     *     A list of {@link WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns ReceivePutStateServiceSoap
     */
    @WebEndpoint(name = "ReceivePutStateServiceSoap")
    public ReceivePutStateServiceSoap getReceivePutStateServiceSoap(WebServiceFeature... features) {
        return super.getPort(ReceivePutStateServiceSoap, ReceivePutStateServiceSoap.class, features);
    }


    /**
     *
     * @return
     *     returns ReceivePutStateServiceSoap
     */
    @WebEndpoint(name = "ReceivePutStateServiceSoap12")
    public ReceivePutStateServiceSoap getReceivePutStateServiceSoap12() {
        return super.getPort(ReceivePutStateServiceSoap12, ReceivePutStateServiceSoap.class);
    }

    /**
     *
     * @param features
     *     A list of {@link WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns ReceivePutStateServiceSoap
     */
    @WebEndpoint(name = "ReceivePutStateServiceSoap12")
    public ReceivePutStateServiceSoap getReceivePutStateServiceSoap12(WebServiceFeature... features) {
        return super.getPort(ReceivePutStateServiceSoap12, ReceivePutStateServiceSoap.class, features);
    }

}
