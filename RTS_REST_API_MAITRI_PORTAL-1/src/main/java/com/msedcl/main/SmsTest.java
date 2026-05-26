package com.msedcl.main;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import org.springframework.web.client.RestTemplate;

public class SmsTest {

    public static void main(String[] args) {

        try {

            // ==========================================
            // DEFAULT TEST VALUES
            // ==========================================

            String consumerNumber =
                    "160225009357";

            String applicationDate =
                    "25/05/2026";

            String applicationNumber =
                    "10083";

            // YOUR MOBILE NUMBER
            String mobileNumber =
                    "9309986649";

            // ==========================================
            // SMS URL
            // ==========================================

            String smsUrl =

                    "https://mse.tdots.in/api/pushsms"

                    + "?aid="
                    + encode("msebapi")

                    + "&pin="
                    + encode("92BAdtQJexM6c")

                    // TEMPLATE PARAMETER 1
                    // Consumer Number
                    + "&F1="
                    + encode(consumerNumber)

                    // TEMPLATE PARAMETER 2
                    // Application Date
                    + "&F2="
                    + applicationDate

                    // TEMPLATE PARAMETER 3
                    // Application Number
                    + "&F3="
                    + encode(applicationNumber)

                    + "&response=Y"

                    + "&mnumber="
                    + encode(mobileNumber)

                    + "&signature="
                    + encode("MSEDCL")

                    + "&templateid="
                    + encode("n300256");

            // ==========================================
            // PRINT URL
            // ==========================================

            System.out.println(
                    "SMS URL = ");

            System.out.println(
                    smsUrl);

            // ==========================================
            // SEND SMS
            // ==========================================

            RestTemplate restTemplate =
                    new RestTemplate();

            String response =
                    restTemplate.getForObject(
                            smsUrl,
                            String.class);

            // ==========================================
            // RESPONSE
            // ==========================================

            System.out.println(
                    "SMS RESPONSE = "
                    + response);

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    // ==========================================
    // URL ENCODER
    // ==========================================

    private static String encode(
            String value) {

        return URLEncoder.encode(
                value,
                StandardCharsets.UTF_8);
    }
}