
package com.msedcl.soap.ht;

import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElementRef;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>{@code
 * <complexType>
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="consumerNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="currentBillFlagYn" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "consumerNumber",
    "currentBillFlagYn"
})
@XmlRootElement(name = "GetArrearsAmountWithCurrentBillFlag")
public class GetArrearsAmountWithCurrentBillFlag {

    @XmlElementRef(name = "consumerNumber", namespace = "http://ws.mahadiscom.in/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> consumerNumber;
    @XmlElementRef(name = "currentBillFlagYn", namespace = "http://ws.mahadiscom.in/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> currentBillFlagYn;

    /**
     * Gets the value of the consumerNumber property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getConsumerNumber() {
        return consumerNumber;
    }

    /**
     * Sets the value of the consumerNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setConsumerNumber(JAXBElement<String> value) {
        this.consumerNumber = value;
    }

    /**
     * Gets the value of the currentBillFlagYn property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCurrentBillFlagYn() {
        return currentBillFlagYn;
    }

    /**
     * Sets the value of the currentBillFlagYn property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCurrentBillFlagYn(JAXBElement<String> value) {
        this.currentBillFlagYn = value;
    }

}
