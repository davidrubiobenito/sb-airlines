package com.drbotro.fa.webrs;

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

    /**
    @Bean
    public CommandLineRunner setup(IFareRepository iFareRepository){
        return (args) -> {
           
            List<Fare> fareList = new ArrayList<>();
            fareList.add(Fare.builder().withFlightNumber("BF100").withFlightDate("22-JAN-16")
                    .withFare(BigDecimal.valueOf(100)).withCurrency("USD").build());
            fareList.add(Fare.builder().withFlightNumber("BF101").withFlightDate("22-JAN-16")
                    .withFare(BigDecimal.valueOf(101)).withCurrency("USD").build());
            fareList.add(Fare.builder().withFlightNumber("BF102").withFlightDate("22-JAN-16")
                    .withFare(BigDecimal.valueOf(102)).withCurrency("USD").build());
            fareList.add(Fare.builder().withFlightNumber("BF103").withFlightDate("22-JAN-16")
                    .withFare(BigDecimal.valueOf(103)).withCurrency("USD").build());
            fareList.add(Fare.builder().withFlightNumber("BF104").withFlightDate("22-JAN-16")
                    .withFare(BigDecimal.valueOf(104)).withCurrency("USD").build());
            fareList.add(Fare.builder().withFlightNumber("BF105").withFlightDate("22-JAN-16")
                    .withFare(BigDecimal.valueOf(105)).withCurrency("USD").build());
            fareList.add(Fare.builder().withFlightNumber("BF106").withFlightDate("22-JAN-16")
                    .withFare(BigDecimal.valueOf(106)).withCurrency("USD").build());
    
            fareList.forEach(iFareRepository::save);
    
            logger.info("Result: {}", iFareRepository.getFareByFlightNumberAndFlightDate("BF101", "22-JAN-16"));
            
        };        
    }
    */
}
