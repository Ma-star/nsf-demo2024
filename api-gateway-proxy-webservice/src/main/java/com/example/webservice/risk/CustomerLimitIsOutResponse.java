
package com.example.webservice.risk;

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
 *         &amp;lt;element name="CustomerLimitIsOutResult" type="{http://www.w3.org/2001/XMLSchema}int"/&amp;gt;
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
    "customerLimitIsOutResult"
})
@XmlRootElement(name = "CustomerLimitIsOutResponse")
public class CustomerLimitIsOutResponse {

    @XmlElement(name = "CustomerLimitIsOutResult")
    protected int customerLimitIsOutResult;

    /**
     * 获取customerLimitIsOutResult属性的值。
     * 
     */
    public int getCustomerLimitIsOutResult() {
        return customerLimitIsOutResult;
    }

    /**
     * 设置customerLimitIsOutResult属性的值。
     * 
     */
    public void setCustomerLimitIsOutResult(int value) {
        this.customerLimitIsOutResult = value;
    }

}
