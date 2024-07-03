
package com.example.webservice.risk;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.example.webservice.risk package. 
 * &lt;p&gt;An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Int_QNAME = new QName("http://tempuri.org/", "int");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.example.webservice.risk
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetCustomerRiskInfo }
     * 
     */
    public GetCustomerRiskInfo createGetCustomerRiskInfo() {
        return new GetCustomerRiskInfo();
    }

    /**
     * Create an instance of {@link GetCustomerRiskInfoResponse }
     * 
     */
    public GetCustomerRiskInfoResponse createGetCustomerRiskInfoResponse() {
        return new GetCustomerRiskInfoResponse();
    }

    /**
     * Create an instance of {@link CustomerLimitIsOut }
     * 
     */
    public CustomerLimitIsOut createCustomerLimitIsOut() {
        return new CustomerLimitIsOut();
    }

    /**
     * Create an instance of {@link CustomerLimitIsOutResponse }
     * 
     */
    public CustomerLimitIsOutResponse createCustomerLimitIsOutResponse() {
        return new CustomerLimitIsOutResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "int")
    public JAXBElement<Integer> createInt(Integer value) {
        return new JAXBElement<Integer>(_Int_QNAME, Integer.class, null, value);
    }

}
