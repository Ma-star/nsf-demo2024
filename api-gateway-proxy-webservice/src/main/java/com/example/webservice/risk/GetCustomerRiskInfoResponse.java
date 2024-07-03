
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
 *         &amp;lt;element name="GetCustomerRiskInfoResult" type="{http://www.w3.org/2001/XMLSchema}int"/&amp;gt;
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
    "getCustomerRiskInfoResult"
})
@XmlRootElement(name = "GetCustomerRiskInfoResponse")
public class GetCustomerRiskInfoResponse {

    @XmlElement(name = "GetCustomerRiskInfoResult")
    protected int getCustomerRiskInfoResult;

    /**
     * 获取getCustomerRiskInfoResult属性的值。
     * 
     */
    public int getGetCustomerRiskInfoResult() {
        return getCustomerRiskInfoResult;
    }

    /**
     * 设置getCustomerRiskInfoResult属性的值。
     * 
     */
    public void setGetCustomerRiskInfoResult(int value) {
        this.getCustomerRiskInfoResult = value;
    }

}
