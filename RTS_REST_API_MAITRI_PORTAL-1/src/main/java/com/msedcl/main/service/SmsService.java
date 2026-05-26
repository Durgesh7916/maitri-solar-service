package com.msedcl.main.service;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SmsService {

    // =====================================================
    // SMS CONFIGURATION
    // =====================================================

	/*
	 * private static final String SMS_URL = "https://mse.tdots.in/api/pushsms";
	 * 
	 * private static final String AID = "msebapi";
	 * 
	 * private static final String PIN = "92BAdtQJexM6c";
	 * 
	 * private static final String SIGNATURE = "MSEDCL";
	 * 
	 * // REAL DLT TEMPLATE ID private static final String TEMPLATE_ID = "n300256";
	 */
	
	
	@Value("${sms.url}")
	private String smsUrl;

	@Value("${sms.aid}")
	private String aid;

	@Value("${sms.pin}")
	private String pin;

	@Value("${sms.signature}")
	private String signature;

	@Value("${sms.template-id}")
	private String templateId;

    // =====================================================
    // SEND SOLAR APPLICATION SMS
    // =====================================================

    public void sendSolarApplicationSms(

            String consumerNumber,

            String applicationNumber,

            String mobileNumber) {

        try {

            // =============================================
            // APPLICATION DATE
            // =============================================

            String applicationDate =
                    LocalDate.now()
                    .format(
                            DateTimeFormatter
                            .ofPattern("dd/MM/yyyy"));

            // =============================================
            // SMS URL CREATE
            // =============================================

            String smsApiUrl =
            		smsUrl

                    + "?aid=" + encode(aid)

                    + "&pin=" + encode(pin)

                    // TEMPLATE PARAMETERS

                    // {0} Consumer Number
                    + "&F1=" + encode(consumerNumber)

                    // {1} Application Date
                    + "&F2=" + applicationDate

                    // {2} Application Number
                    + "&F3=" + encode(applicationNumber)

                    + "&response=Y"

                    + "&mnumber="
                    + encode(mobileNumber)

                    + "&signature="
                    + encode(signature)

                    + "&templateid="
                    + encode(templateId);

            // =============================================
            // PRINT URL
            // =============================================

            System.out.println(
                    "SMS URL = ");

            System.out.println(
                    smsApiUrl);

            // =============================================
            // API CALL
            // =============================================

            RestTemplate restTemplate =
                    new RestTemplate();

            String response =
                    restTemplate.getForObject(
                            smsApiUrl,
                            String.class);

            // =============================================
            // RESPONSE
            // =============================================

            System.out.println(
                    "SMS RESPONSE = "
                    + response);

        } catch (Exception e) {

            e.printStackTrace();

            System.out.println(
                    "SMS FAILED : "
                    + e.getMessage());
        }
    }

    // =====================================================
    // URL ENCODER
    // =====================================================

    private String encode(String value) {

        return URLEncoder.encode(
                value,
                StandardCharsets.UTF_8);
    }
}