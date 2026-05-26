
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
 *         <element name="return" type="{http://dto.arrears.ht.msedcl.in/xsd}ArrearsDetailsDTO" minOccurs="0"/>
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
    "_return"
})
@XmlRootElement(name = "GetArrearsAmountResponse")
public class GetArrearsAmountResponse {

    @XmlElementRef(name = "return", namespace = "http://ws.mahadiscom.in/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<ArrearsDetailsDTO> _return;

    /**
     * Gets the value of the return property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrearsDetailsDTO }{@code >}
     *     
     */
    public JAXBElement<ArrearsDetailsDTO> getReturn() {
        return _return;
    }

    /**
     * Sets the value of the return property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrearsDetailsDTO }{@code >}
     *     
     */
    public void setReturn(JAXBElement<ArrearsDetailsDTO> value) {
        this._return = value;
    }

}
