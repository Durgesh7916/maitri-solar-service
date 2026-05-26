
package com.msedcl.soap.lt;

import javax.xml.namespace.QName;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlElementDecl;
import jakarta.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.msedcl.soap.lt package. 
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

    private static final QName _GetCbPdArrears_QNAME = new QName("http://ws.msedcl.com/", "getCbPdArrears");
    private static final QName _GetCbPdArrearsResponse_QNAME = new QName("http://ws.msedcl.com/", "getCbPdArrearsResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.msedcl.soap.lt
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetCbPdArrears }
     * 
     * @return
     *     the new instance of {@link GetCbPdArrears }
     */
    public GetCbPdArrears createGetCbPdArrears() {
        return new GetCbPdArrears();
    }

    /**
     * Create an instance of {@link GetCbPdArrearsResponse }
     * 
     * @return
     *     the new instance of {@link GetCbPdArrearsResponse }
     */
    public GetCbPdArrearsResponse createGetCbPdArrearsResponse() {
        return new GetCbPdArrearsResponse();
    }

    /**
     * Create an instance of {@link CbPdArrearsDto }
     * 
     * @return
     *     the new instance of {@link CbPdArrearsDto }
     */
    public CbPdArrearsDto createCbPdArrearsDto() {
        return new CbPdArrearsDto();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetCbPdArrears }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetCbPdArrears }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.msedcl.com/", name = "getCbPdArrears")
    public JAXBElement<GetCbPdArrears> createGetCbPdArrears(GetCbPdArrears value) {
        return new JAXBElement<>(_GetCbPdArrears_QNAME, GetCbPdArrears.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetCbPdArrearsResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetCbPdArrearsResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.msedcl.com/", name = "getCbPdArrearsResponse")
    public JAXBElement<GetCbPdArrearsResponse> createGetCbPdArrearsResponse(GetCbPdArrearsResponse value) {
        return new JAXBElement<>(_GetCbPdArrearsResponse_QNAME, GetCbPdArrearsResponse.class, null, value);
    }

}
