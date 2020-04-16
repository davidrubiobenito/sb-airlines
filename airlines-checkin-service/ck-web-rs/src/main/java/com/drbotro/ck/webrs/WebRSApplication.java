package com.drbotro.ck.webrs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"com.drbotro.*"})
@EntityScan(basePackages = {"com.drbotro.*"})
@EnableJpaRepositories(basePackages = {"com.drbotro.*"})
public class WebRSApplication{

    private static final Logger logger = LoggerFactory.getLogger(WebRSApplication.class);

    public static void main(String[] args){
        SpringApplication.run(WebRSApplication.class);
    }
}
