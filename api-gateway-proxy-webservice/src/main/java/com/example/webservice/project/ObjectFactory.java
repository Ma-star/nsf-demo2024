
package com.example.webservice.project;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.example.webservice.project package. 
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
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.example.webservice.project
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetProjectCode }
     * 
     */
    public GetProjectCode createGetProjectCode() {
        return new GetProjectCode();
    }

    /**
     * Create an instance of {@link GetProjectCodeResponse }
     * 
     */
    public GetProjectCodeResponse createGetProjectCodeResponse() {
        return new GetProjectCodeResponse();
    }

    /**
     * Create an instance of {@link GetProjectName }
     * 
     */
    public GetProjectName createGetProjectName() {
        return new GetProjectName();
    }

    /**
     * Create an instance of {@link GetProjectNameResponse }
     * 
     */
    public GetProjectNameResponse createGetProjectNameResponse() {
        return new GetProjectNameResponse();
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
