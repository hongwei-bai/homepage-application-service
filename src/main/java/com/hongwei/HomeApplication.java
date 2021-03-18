package com.hongwei;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;

// Deploy 1 - IntelliJ IDEA Debug
//@SpringBootApplication
//@EnableScheduling
//public class HomeApplication {
//    public static void main(String[] args) {
//        SpringApplication.run(HomeApplication.class, args);
//    }
//}

// Deploy 3 - deploy war to Tomcat/NGINX server
@EntityScan
@SpringBootApplication
@EnableScheduling
public class HomeApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(HomeApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(HomeApplication.class);
    }
}