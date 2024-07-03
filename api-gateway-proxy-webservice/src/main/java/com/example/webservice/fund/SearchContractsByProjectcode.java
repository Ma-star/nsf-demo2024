
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
 *         &amp;lt;element name="PROJECT_NUMBER" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="MASTER_NO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
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
    "projectnumber",
    "masterno"
})
@XmlRootElement(name = "SearchContractsByProjectcode")
public class SearchContractsByProjectcode {

    @XmlElement(name = "PROJECT_NUMBER")
    protected String projectnumber;
    @XmlElement(name = "MASTER_NO")
    protected String masterno;

    /**
     * 获取projectnumber属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPROJECTNUMBER() {
        return projectnumber;
    }

    /**
     * 设置projectnumber属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPROJECTNUMBER(String value) {
        this.projectnumber = value;
    }

    /**
     * 获取masterno属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMASTERNO() {
        return masterno;
    }

    /**
     * 设置masterno属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMASTERNO(String value) {
        this.masterno = value;
    }

}