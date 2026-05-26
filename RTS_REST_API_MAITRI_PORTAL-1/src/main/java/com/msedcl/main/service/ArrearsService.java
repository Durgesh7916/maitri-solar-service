package com.msedcl.main.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import jakarta.xml.ws.BindingProvider;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.msedcl.main.dto.ArrearsResponseDto;
import com.msedcl.soap.ht.ArrearsDetailsDTO;
import com.msedcl.soap.ht.HtArrearsWebService;
import com.msedcl.soap.ht.HtArrearsWebServicePortType;

import com.msedcl.soap.lt.CbPdArrearsDto;
import com.msedcl.soap.lt.CbPdArrearsWebService;
import com.msedcl.soap.lt.CbPdArrearsWebServiceService;

@Service
public class ArrearsService {

    @Value("${app.isProd}")
    private String isProd;

    @Value("${soap.ht.url}")
    private String htSoapUrl;

    @Value("${soap.lt.url}")
    private String ltSoapUrl;

    /*
     * MAIN ARREARS CHECK METHOD
     */
    public ArrearsResponseDto checkArrears(
            String consNo,
            String consCatg,
            int reArrangementId,
            Integer schemeName) {

        ArrearsResponseDto result =
                new ArrearsResponseDto();

        try {

            if ("N".equalsIgnoreCase(isProd)) {

                result.setStatus(true);
                result.setMessage("SUCCESS");

                return result;
            }

            if (reArrangementId == 3
                    || schemeName==4
                    || reArrangementId == 7) {

                result.setStatus(true);
                result.setMessage("SUCCESS");

                return result;
            }

           

            if (consCatg.equalsIgnoreCase("HT"))
                     {

                return checkHtArrears(consNo);
            }

            return checkLtArrears(consNo);

        } catch (Exception ex) {

            ex.printStackTrace();

            result.setStatus(false);
            result.setMessage(
                    "Error While getting Arrears. Please check after some time");

            return result;
        }
    }
    /*
     * HT ARREARS CHECK
     */
    private ArrearsResponseDto checkHtArrears(
            String consNo) {

        ArrearsResponseDto result =
                new ArrearsResponseDto();

        try {

            String dueDate = LocalDate.now()
                    .format(
                            DateTimeFormatter.ofPattern(
                                    "dd-MMM-yyyy"));

            HtArrearsWebService service =
                    new HtArrearsWebService();

            HtArrearsWebServicePortType port =
                    service
                            .getHtArrearsWebServiceHttpSoap11Endpoint();

            BindingProvider bp =
                    (BindingProvider) port;

            bp.getRequestContext().put(
                    BindingProvider.ENDPOINT_ADDRESS_PROPERTY,
                    htSoapUrl
            );

            ArrearsDetailsDTO response =
                    port.getArrearsAmountWithCurrentBillFlag(
                            consNo,
                            "N"
                    );

            if (response != null
                    && response.getStatusMessage() != null
                    && "SUCCESS".equalsIgnoreCase(
                    response.getStatusMessage().getValue())) {

                Double arrearsAmount =
                        response.getArrearsAmount();

                if (arrearsAmount != null
                        && arrearsAmount > 10) {

                    String message =
                            "Consumer No. "
                                    + consNo
                                    + " has outstanding dues Rs. "
                                    + arrearsAmount
                                    + " as on dt. "
                                    + dueDate;

                    result.setStatus(false);
                    result.setMessage(message);

                    return result;
                }

                result.setStatus(true);
                result.setMessage("SUCCESS");

                return result;
            }

            result.setStatus(false);
            result.setMessage(
                    "Error while getting HT arrears");

            return result;

        } catch (Exception ex) {

            ex.printStackTrace();

            result.setStatus(false);
            result.setMessage(
                    "Error while getting HT arrears");

            return result;
        }
    }
    /*
     * LT ARREARS CHECK
     */
    private ArrearsResponseDto checkLtArrears(
            String consNo) {

        ArrearsResponseDto result =
                new ArrearsResponseDto();

        try {

            String dueDate = LocalDate.now()
                    .format(
                            DateTimeFormatter.ofPattern(
                                    "dd-MMM-yyyy"));

            /*
             * CREATE SOAP SERVICE
             */
            CbPdArrearsWebServiceService service =
                    new CbPdArrearsWebServiceService();

            /*
             * GET SOAP PORT
             */
            CbPdArrearsWebService port =
                    service.getCbPdArrearsWebServicePort();

            /*
             * DYNAMIC SOAP URL
             */
            BindingProvider bp =
                    (BindingProvider) port;

            bp.getRequestContext().put(
                    BindingProvider.ENDPOINT_ADDRESS_PROPERTY,
                    ltSoapUrl
            );

            /*
             * SOAP CALL
             */
            CbPdArrearsDto response =
                    port.getCbPdArrears(consNo);

            /*
             * RESPONSE CHECK
             */
            if (response != null) {

                BigDecimal arrearsAmount =
                        response.getTotalArrearsAmount();

                /*
                 * ARREARS VALIDATION
                 */
                if (arrearsAmount != null
                        && arrearsAmount.compareTo(
                        BigDecimal.TEN) > 0) {

                    String message =
                            "Consumer No. "
                                    + consNo
                                    + " has outstanding dues Rs. "
                                    + arrearsAmount
                                    + " as on dt. "
                                    + dueDate
                                    + ". Hence according to Regulations 6.6 "
                                    + "of MERC Grid Interactive Rooftop "
                                    + "Renewable Energy Generating System "
                                    + "Regulations 2019, consumer is not "
                                    + "eligible to apply for Net Metering/"
                                    + "Net Billing Connection.";

                    result.setStatus(false);
                    result.setMessage(message);

                    return result;
                }

                /*
                 * NO ARREARS
                 */
                result.setStatus(true);
                result.setMessage("SUCCESS");

                return result;
            }

            result.setStatus(false);
            result.setMessage(
                    "Error while getting LT arrears");

            return result;

        } catch (Exception ex) {

            ex.printStackTrace();

            result.setStatus(false);
            result.setMessage(
                    "Error while getting LT arrears");

            return result;
        }
    }
    
}