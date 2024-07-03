
package com.example.webservice.fund;

import javax.xml.bind.annotation.*;


/**
 * &lt;p&gt;anonymous complex type的 Java 类。
 * 
 * &lt;p&gt;以下模式片段指定包含在此类中的预期内容。
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="SearchContractsByProjectcodeResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/restriction&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "searchContractsByProjectcodeResult"
})
@XmlRootElement(name = "SearchContractsByProjectcodeResponse")
public class SearchContractsByProjectcodeResponse {

    @XmlElement(name = "SearchContractsByProjectcodeResult")
    protected String searchContractsByProjectcodeResult;

    /**
     * 获取searchContractsByProjectcodeResult属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSearchContractsByProjectcodeResult() {
        return searchContractsByProjectcodeResult;
    }

    /**
     * 设置searchContractsByProjectcodeResult属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSearchContractsByProjectcodeResult(String value) {
        this.searchContractsByProjectcodeResult = value;
    }

}
