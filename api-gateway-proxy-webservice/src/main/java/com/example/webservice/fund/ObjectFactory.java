
package com.example.webservice.fund;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.example.webservice.fund package. 
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

    private final static QName _String_QNAME = new QName("http://tempuri.org/", "string");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.example.webservice.fund
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link SearchContractsByProjectcode }
     * 
     */
    public SearchContractsByProjectcode createSearchContractsByProjectcode() {
        return new SearchContractsByProjectcode();
    }

    /**
     * Create an instance of {@link SearchContractsByProjectcodeResponse }
     * 
     */
    public SearchContractsByProjectcodeResponse createSearchContractsByProjectcodeResponse() {
        return new SearchContractsByProjectcodeResponse();
    }

    /**
     * Create an instance of {@link FundInfoConfirm }
     * 
     */
    public FundInfoConfirm createFundInfoConfirm() {
        return new FundInfoConfirm();
    }

    /**
     * Create an instance of {@link FundInfoConfirmResponse }
     * 
     */
    public FundInfoConfirmResponse createFundInfoConfirmResponse() {
        return new FundInfoConfirmResponse();
    }

    /**
     * Create an instance of {@link HelloWorld }
     * 
     */
    public HelloWorld createHelloWorld() {
        return new HelloWorld();
    }

    /**
     * Create an instance of {@link HelloWorldResponse }
     * 
     */
    public HelloWorldResponse createHelloWorldResponse() {
        return new HelloWorldResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "string")
    public JAXBElement<String> createString(String value) {
        return new JAXBElement<String>(_String_QNAME, String.class, null, value);
    }

}
