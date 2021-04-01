package com.hongwei

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer
import org.springframework.context.annotation.Bean
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.web.servlet.config.annotation.CorsRegistry

import org.springframework.web.servlet.config.annotation.WebMvcConfigurer


@EntityScan
@SpringBootApplication
@EnableScheduling
open class HomeApplication : SpringBootServletInitializer() {
    override fun configure(builder: SpringApplicationBuilder): SpringApplicationBuilder {
        return builder.sources(HomeApplication::class.java)
    }
}

fun main(args: Array<String>) {
    SpringApplication.run(HomeApplication::class.java, *args)
}

@Bean
fun corsConfigurer(): WebMvcConfigurer? {

    return object : WebMvcConfigurer {
        override fun addCorsMappings(registry: CorsRegistry) {
            registry
                    .addMapping("/*")
                    .allowedOrigins("http://localhost:9000")
        }
    }
}