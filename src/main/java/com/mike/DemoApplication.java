package com.mike;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.builder.SpringApplicationBuilder;
//import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

// Deploy 1 - IntelliJ IDEA Debug
@SpringBootApplication
public class DemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}

// Deploy 3 - deploy war to Tomcat/NGINX server
//@SpringBootApplication
//public class DemoApplication extends SpringBootServletInitializer {
//
//    public static void main(String[] args) {
//        SpringApplication.run(DemoApplication.class, args);
//    }
//
//    @Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
//        return builder.sources(DemoApplication.class);
//    }
//}
