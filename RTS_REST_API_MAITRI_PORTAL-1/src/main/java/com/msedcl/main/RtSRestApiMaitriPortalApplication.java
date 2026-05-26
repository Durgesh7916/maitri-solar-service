package com.msedcl.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import com.msedcl.main.config.DataSourceProperties;

import jakarta.annotation.PostConstruct;

//@EnableConfigurationProperties(DataSourceProperties.class)
@SpringBootApplication
public class RtSRestApiMaitriPortalApplication
        extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(
            SpringApplicationBuilder application) {

        return application.sources(
                RtSRestApiMaitriPortalApplication.class
        );
    }

    
  /*  @PostConstruct
    public void debug() {
        System.out.println("db_url_dev = " + System.getenv("DB_URL"));
        System.out.println("db_username_dev = " + System.getenv("DB_USERNAME"));
        System.out.println("db_pass_dev = " + System.getenv("DB_PASSWORD"));
    }
    
    @PostConstruct
    public void check() {
        System.out.println("URL = " + System.getProperty("spring.datasource.url"));
        System.out.println("ENV URL = " + System.getenv("DB_URL"));
    }*/
    public static void main(String[] args) {

        SpringApplication.run(
                RtSRestApiMaitriPortalApplication.class,
                args
        );
    }
}