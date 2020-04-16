package com.drbotro.bk.webrs;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;

import com.drbotro.bk.coreserviceapi.data.BookingRecordRequest;
import com.drbotro.bk.coreserviceapi.data.BookingRecordResponse;
import com.drbotro.bk.coreserviceapi.data.PassengerRequest;
import com.drbotro.bk.coreserviceapi.inter.IBookingService;
import com.drbotro.bk.repository.dao.IInventoryRepository;
import com.drbotro.bk.repository.model.Inventory;

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
    public RestTemplate resTemplate(){
        return new RestTemplate();
    }

    @Bean
    public CommandLineRunner setup(IInventoryRepository iInventoryRepository, IBookingService iBookingService){
        return (args) -> {

            /*
            List<Inventory> inventoryList = new ArrayList<>();
            inventoryList.add(Inventory.builder().withFlightNumber("BF100").withFlightDate("22-JAN-16")
                    .withAvailable(100).build());
            inventoryList.add(Inventory.builder().withFlightNumber("BF101").withFlightDate("22-JAN-16")
                    .withAvailable(100).build());
            inventoryList.add(Inventory.builder().withFlightNumber("BF102").withFlightDate("22-JAN-16")
                    .withAvailable(100).build());
            inventoryList.add(Inventory.builder().withFlightNumber("BF103").withFlightDate("22-JAN-16")
                    .withAvailable(100).build());
            inventoryList.add(Inventory.builder().withFlightNumber("BF104").withFlightDate("22-JAN-16")
                    .withAvailable(100).build());
            inventoryList.add(Inventory.builder().withFlightNumber("BF105").withFlightDate("22-JAN-16")
                    .withAvailable(100).build());
            inventoryList.add(Inventory.builder().withFlightNumber("BF106").withFlightDate("22-JAN-16")
                    .withAvailable(100).build());
            
            inventoryList.stream().map(inv -> iInventoryRepository.save(inv));
               */

            Inventory[] invs = {
                    Inventory.builder().withFlightNumber("BF100").withFlightDate("22-JAN-16").withAvailable(100)
                            .build(),
                    Inventory.builder().withFlightNumber("BF101").withFlightDate("22-JAN-16").withAvailable(100)
                            .build(),
                    Inventory.builder().withFlightNumber("BF102").withFlightDate("22-JAN-16").withAvailable(100)
                            .build(),
                    Inventory.builder().withFlightNumber("BF103").withFlightDate("22-JAN-16").withAvailable(100)
                            .build(),
                    Inventory.builder().withFlightNumber("BF104").withFlightDate("22-JAN-16").withAvailable(100)
                            .build(),
                    Inventory.builder().withFlightNumber("BF105").withFlightDate("22-JAN-16").withAvailable(100)
                            .build(),
                    Inventory.builder().withFlightNumber("BF106").withFlightDate("22-JAN-16").withAvailable(100)
                            .build()};

            Arrays.asList(invs).forEach(inventory -> iInventoryRepository.save(inventory));

            BookingRecordRequest bookingRecordRequest = BookingRecordRequest.builder().withFlightNumber("BF101")
                    .withOrigin("NYC").withDestination("SFO").withFlightDate("22-JAN-16").withBookingDate(new Date())
                    .withFare("101").build();
            Set<PassengerRequest> passengersRequest = new HashSet<>();
            passengersRequest.add(PassengerRequest.builder().withFirstname("Gean").withLastName("Franc")
                    .withGender("Male").withBookingRecordRequest(bookingRecordRequest).build());

            BookingRecordRequest bookingRecordRequestAux = bookingRecordRequest.cloneBuilder()
                    .withPassengersRequest(passengersRequest).build();

            logger.info("Booking Request to save ...: {} ", bookingRecordRequestAux);
            BookingRecordResponse bookingRecordResponse = iBookingService.saveBookingRecord(bookingRecordRequestAux);

            logger.info("Booking successfully saved...: {} ", bookingRecordResponse);

            logger.info("Looking to load booking record...");

            logger.info("Result: {}", iBookingService.getBookingRecordById(bookingRecordResponse.getId()));
        };
    }
}
