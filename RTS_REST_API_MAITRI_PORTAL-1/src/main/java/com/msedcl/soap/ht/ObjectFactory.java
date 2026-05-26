
package com.msedcl.soap.ht;

import javax.xml.namespace.QName;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlElementDecl;
import jakarta.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.msedcl.soap.ht package. 
 * <p>An ObjectFactory allows you to programatically 
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

    private static final QName _GetArrearsAmountWithCurrentBillFlagConsumerNumber_QNAME = new QName("http://ws.mahadiscom.in/xsd", "consumerNumber");
    private static final QName _GetArrearsAmountWithCurrentBillFlagCurrentBillFlagYn_QNAME = new QName("http://ws.mahadiscom.in/xsd", "currentBillFlagYn");
    private static final QName _GetArrearsAmountWithCurrentBillFlagResponseReturn_QNAME = new QName("http://ws.mahadiscom.in/xsd", "return");
    private static final QName _ArrearsDetailsDTOConsumerNumber_QNAME = new QName("http://dto.arrears.ht.msedcl.in/xsd", "consumerNumber");
    private static final QName _ArrearsDetailsDTOCurrentBillFlagYn_QNAME = new QName("http://dto.arrears.ht.msedcl.in/xsd", "currentBillFlagYn");
    private static final QName _ArrearsDetailsDTOStatusMessage_QNAME = new QName("http://dto.arrears.ht.msedcl.in/xsd", "statusMessage");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.msedcl.soap.ht
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetArrearsAmountWithCurrentBillFlag }
     * 
     * @return
     *     the new instance of {@link GetArrearsAmountWithCurrentBillFlag }
     */
    public GetArrearsAmountWithCurrentBillFlag createGetArrearsAmountWithCurrentBillFlag() {
        return new GetArrearsAmountWithCurrentBillFlag();
    }

    /**
     * Create an instance of {@link GetArrearsAmountWithCurrentBillFlagResponse }
     * 
     * @return
     *     the new instance of {@link GetArrearsAmountWithCurrentBillFlagResponse }
     */
    public GetArrearsAmountWithCurrentBillFlagResponse createGetArrearsAmountWithCurrentBillFlagResponse() {
        return new GetArrearsAmountWithCurrentBillFlagResponse();
    }

    /**
     * Create an instance of {@link ArrearsDetailsDTO }
     * 
     * @return
     *     the new instance of {@link ArrearsDetailsDTO }
     */
    public ArrearsDetailsDTO createArrearsDetailsDTO() {
        return new ArrearsDetailsDTO();
    }

    /**
     * Create an instance of {@link GetArrearsAmount }
     * 
     * @return
     *     the new instance of {@link GetArrearsAmount }
     */
    public GetArrearsAmount createGetArrearsAmount() {
        return new GetArrearsAmount();
    }

    /**
     * Create an instance of {@link GetArrearsAmountResponse }
     * 
     * @return
     *     the new instance of {@link GetArrearsAmountResponse }
     */
    public GetArrearsAmountResponse createGetArrearsAmountResponse() {
        return new GetArrearsAmountResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.mahadiscom.in/xsd", name = "consumerNumber", scope = GetArrearsAmountWithCurrentBillFlag.class)
    public JAXBElement<String> createGetArrearsAmountWithCurrentBillFlagConsumerNumber(String value) {
        return new JAXBElement<>(_GetArrearsAmountWithCurrentBillFlagConsumerNumber_QNAME, String.class, GetArrearsAmountWithCurrentBillFlag.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.mahadiscom.in/xsd", name = "currentBillFlagYn", scope = GetArrearsAmountWithCurrentBillFlag.class)
    public JAXBElement<String> createGetArrearsAmountWithCurrentBillFlagCurrentBillFlagYn(String value) {
        return new JAXBElement<>(_GetArrearsAmountWithCurrentBillFlagCurrentBillFlagYn_QNAME, String.class, GetArrearsAmountWithCurrentBillFlag.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrearsDetailsDTO }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ArrearsDetailsDTO }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.mahadiscom.in/xsd", name = "return", scope = GetArrearsAmountWithCurrentBillFlagResponse.class)
    public JAXBElement<ArrearsDetailsDTO> createGetArrearsAmountWithCurrentBillFlagResponseReturn(ArrearsDetailsDTO value) {
        return new JAXBElement<>(_GetArrearsAmountWithCurrentBillFlagResponseReturn_QNAME, ArrearsDetailsDTO.class, GetArrearsAmountWithCurrentBillFlagResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.mahadiscom.in/xsd", name = "consumerNumber", scope = GetArrearsAmount.class)
    public JAXBElement<String> createGetArrearsAmountConsumerNumber(String value) {
        return new JAXBElement<>(_GetArrearsAmountWithCurrentBillFlagConsumerNumber_QNAME, String.class, GetArrearsAmount.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrearsDetailsDTO }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ArrearsDetailsDTO }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.mahadiscom.in/xsd", name = "return", scope = GetArrearsAmountResponse.class)
    public JAXBElement<ArrearsDetailsDTO> createGetArrearsAmountResponseReturn(ArrearsDetailsDTO value) {
        return new JAXBElement<>(_GetArrearsAmountWithCurrentBillFlagResponseReturn_QNAME, ArrearsDetailsDTO.class, GetArrearsAmountResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://dto.arrears.ht.msedcl.in/xsd", name = "consumerNumber", scope = ArrearsDetailsDTO.class)
    public JAXBElement<String> createArrearsDetailsDTOConsumerNumber(String value) {
        return new JAXBElement<>(_ArrearsDetailsDTOConsumerNumber_QNAME, String.class, ArrearsDetailsDTO.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://dto.arrears.ht.msedcl.in/xsd", name = "currentBillFlagYn", scope = ArrearsDetailsDTO.class)
    public JAXBElement<String> createArrearsDetailsDTOCurrentBillFlagYn(String value) {
        return new JAXBElement<>(_ArrearsDetailsDTOCurrentBillFlagYn_QNAME, String.class, ArrearsDetailsDTO.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://dto.arrears.ht.msedcl.in/xsd", name = "statusMessage", scope = ArrearsDetailsDTO.class)
    public JAXBElement<String> createArrearsDetailsDTOStatusMessage(String value) {
        return new JAXBElement<>(_ArrearsDetailsDTOStatusMessage_QNAME, String.class, ArrearsDetailsDTO.class, value);
    }

}
