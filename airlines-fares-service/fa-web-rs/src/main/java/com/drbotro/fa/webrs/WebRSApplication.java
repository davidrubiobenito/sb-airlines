package com.drbotro.fa.webrs;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.drbotro.fa.coreserviceapi.model.Fare;
import com.drbotro.fa.repository.dao.IFareRepository;

@SpringBootApplication
@ComponentScan(basePackages = {"com.drbotro.*"})
@EntityScan(basePackages = {"com.drbotro.*"})
@EnableJpaRepositories(basePackages = {"com.drbotro.*"})
public class WebRSApplication{

    private static final Logger logger = LoggerFactory.getLogger(WebRSApplication.class);

    public static void main(String[] args){
        SpringApplication.run(WebRSApplication.class);
    }

    @Bean
    public CommandLineRunner setup(IFareRepository iFareRepository){
        return (args) -> {

            List<Fare> fareList = new ArrayList<>();
            fareList.add(Fare.builder().withFlightNumber("BF100").withFlightDate("22-JAN-16").withFare("100").build());
            fareList.add(Fare.builder().withFlightNumber("BF101").withFlightDate("22-JAN-16").withFare("101").build());
            fareList.add(Fare.builder().withFlightNumber("BF102").withFlightDate("22-JAN-16").withFare("102").build());
            fareList.add(Fare.builder().withFlightNumber("BF103").withFlightDate("22-JAN-16").withFare("103").build());
            fareList.add(Fare.builder().withFlightNumber("BF104").withFlightDate("22-JAN-16").withFare("104").build());
            fareList.add(Fare.builder().withFlightNumber("BF105").withFlightDate("22-JAN-16").withFare("105").build());
            fareList.add(Fare.builder().withFlightNumber("BF106").withFlightDate("22-JAN-16").withFare("106").build());

            fareList.forEach(f -> iFareRepository.save(f));

            logger.info("Result: {}", iFareRepository.getFareByFlightNumberAndFlightDate("BF101", "22-JAN-16"));

        };
    }
}
