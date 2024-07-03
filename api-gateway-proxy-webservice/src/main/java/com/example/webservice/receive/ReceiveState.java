
package com.example.webservice.receive;

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
 *         &amp;lt;element name="LaunchPayBillNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="ConfirmLaunchDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="LaunchStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
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
    "launchPayBillNo",
    "confirmLaunchDate",
    "launchStatus"
})
@XmlRootElement(name = "ReceiveState")
public class ReceiveState {

    @XmlElement(name = "LaunchPayBillNo")
    protected String launchPayBillNo;
    @XmlElement(name = "ConfirmLaunchDate")
    protected String confirmLaunchDate;
    @XmlElement(name = "LaunchStatus")
    protected String launchStatus;

    /**
     * 获取launchPayBillNo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLaunchPayBillNo() {
        return launchPayBillNo;
    }

    /**
     * 设置launchPayBillNo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLaunchPayBillNo(String value) {
        this.launchPayBillNo = value;
    }

    /**
     * 获取confirmLaunchDate属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConfirmLaunchDate() {
        return confirmLaunchDate;
    }

    /**
     * 设置confirmLaunchDate属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConfirmLaunchDate(String value) {
        this.confirmLaunchDate = value;
    }

    /**
     * 获取launchStatus属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLaunchStatus() {
        return launchStatus;
    }

    /**
     * 设置launchStatus属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLaunchStatus(String value) {
        this.launchStatus = value;
    }

}
