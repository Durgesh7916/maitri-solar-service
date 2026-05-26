package com.msedcl.main.controller;

import java.util.HashMap;
import oracle.jdbc.OracleTypes;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.msedcl.main.dto.SolarRequestDto;
//import com.msedcl.main.service.DbCredentialService;
import com.msedcl.main.service.SolarService;

import jakarta.validation.Valid;
import oracle.jdbc.OracleTypes;
@RestController
@RequestMapping("/solar")
public class SolarController {

    @Autowired
    private SolarService solarService;

    
   // private final DbCredentialService dbCredentialService;

   // public SolarController(DbCredentialService dbCredentialService) {
   //     this.dbCredentialService = dbCredentialService;
   //s }
    
    
    @GetMapping("/Admin")
    public Map<String, String> service1(
            @RequestHeader("x-api-key") String key) {

        Map<String, String> response = new HashMap<>();

        if ("Viraj".equals(key)) {
        	
        	String url = System.getProperty("db_url_dev");
        	String username =System.getProperty("db_username_dev");
        	String password =System.getProperty("db_pass_dev");
        	

           // String url = System.getenv("db_url_dev");
           // String username = System.getenv("db_username_dev");
            //String password = System.getenv("db_password_dev");

            response.put("db_url_dev", url);
            response.put("db_username_dev", username);
            response.put("db_password_dev", password);

            return response;
        }

        response.put("status", "FAILED");
        response.put("message", "Invalid Key");

        return response;
    }
    
    @PostMapping("/createApplication")
    public ResponseEntity<?> createApplication(
            @Valid @RequestBody SolarRequestDto request) {

        Map<String, Object> response = new HashMap<>();

        try {

            String result =
                    solarService.createSolarApplication(request);

            response.put("status", "SUCCESS");
            response.put(
                    "message",
                    result);
            

            return ResponseEntity.ok(response);

        } catch (Exception e) {

            response.put("status", "FAILED");
            response.put(
                    "message",
                    e.getMessage()
            );


            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(response);
        }
    }
    
    @GetMapping("/test")
    public ResponseEntity<?> testConnection() {

        Map<String, Object> response = new HashMap<>();

        try {

            String message = solarService.testConnection();

            response.put("status", "SUCCESS");
            response.put("message", message);

            return ResponseEntity.ok(response);

        } catch (Exception e) {

            response.put("status", "FAILED");
            response.put(
                    "message",
                    e.getMessage()            );


            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(response);
        }
    }

	/*
	 * @GetMapping("/test1") public ResponseEntity<?> testConnection1() {
	 * 
	 * // testing exception // int a = 10 / 0;
	 * 
	 * Map<String, Object> response = new HashMap<>();
	 * 
	 * String message = solarService.testConnection();
	 * 
	 * response.put("status", "SUCCESS"); response.put("message", message);
	 * 
	 * return ResponseEntity.ok(response); }
	 */
  @GetMapping("/consumer/{consumerNo}")
            public ResponseEntity<?> getSolarConsumerInfo(
                    @PathVariable String consumerNo) {

                Map<String, Object> response = new HashMap<>();

                try {

                    Map<String, Object> data =
                            solarService.getSolarConsumerInfo(consumerNo);

                    response.put("status", "SUCCESS");
                    response.put("message", data);
                    
                    response.put(
                            "message",
                            data                    );


                    return ResponseEntity.ok(response);

                } catch (Exception e) {

                    response.put("status", "FAILED");
                    response.put(
                            "message",
                            e.getMessage()
                    );


                    return ResponseEntity
                            .status(HttpStatus.INTERNAL_SERVER_ERROR)
                            .body(response);
                }
            }
}