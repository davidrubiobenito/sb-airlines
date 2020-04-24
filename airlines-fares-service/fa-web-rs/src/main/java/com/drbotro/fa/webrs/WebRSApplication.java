package com.drbotro.fa.webrs;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.drbotro.fa.repository.dao.IFareRepository;
import com.drbotro.fa.repository.dao.IUserFareRepository;

@SpringBootApplication
@ComponentScan(basePackages = {"com.drbotro.*"})
@EntityScan(basePackages = {"com.drbotro.*"})
@EnableJpaRepositories(basePackages = {"com.drbotro.*"})
public class WebRSApplication{

    private static final Logger logger = LoggerFactory.getLogger(WebRSApplication.class);

    @Autowired
    private IUserFareRepository iUserRespository;

    @Autowired
    private IFareRepository iFareRepository;

    public static void main(String[] args){
        SpringApplication.run(WebRSApplication.class);
    }

    @PostConstruct
    public void initUsers(){

        /*
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
        
        logger.info("Result: {}", iFareRepository.findFareByFlightNumberAndFlightDate("BF101", "22-JAN-16"));
        
        PermissionFare permissionRead = PermissionFare.builder().withPermissionName(FARE_READ.name()).build();
        PermissionFare permissionWrite = PermissionFare.builder().withPermissionName(FARE_WRITE.name()).build();
        
        RoleFare roleAnonymus = RoleFare.builder().withRoleName(ANONYMOUS.name()).withPermission(Sets.newHashSet())
                .build();
        RoleFare roleAdmin = RoleFare.builder().withRoleName(ADMIN.name())
                .withPermission(Sets.newHashSet(permissionRead, permissionWrite)).build();
        RoleFare roleAdminTrainee = RoleFare.builder().withRoleName(ADMINTRAINEE.name())
                .withPermission(Sets.newHashSet(permissionRead)).build();
        
        List<UserFare> usersFare = Stream.of(
                UserFare.builder().withUsername("annasmith").withPassword("password")
                        .withRoles(Sets.newHashSet(roleAnonymus)).withIsAccountNonExpired(true)
                        .withIsAccountNonLocked(true).withIsCredentialsNonExpired(true).withIsEnabled(true).build(),
                UserFare.builder().withUsername("linda").withPassword("password").withRoles(Sets.newHashSet(roleAdmin))
                        .withIsAccountNonExpired(true).withIsAccountNonLocked(true).withIsCredentialsNonExpired(true)
                        .withIsEnabled(true).build(),
                UserFare.builder().withUsername("tom").withPassword("password")
                        .withRoles(Sets.newHashSet(roleAdminTrainee)).withIsAccountNonExpired(true)
                        .withIsAccountNonLocked(true).withIsCredentialsNonExpired(true).withIsEnabled(true).build())
                .collect(Collectors.toList());
        
        iUserRespository.saveAll(usersFare);
        */
    }

    /*
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
    
            logger.info("Result: {}", iFareRepository.findFareByFlightNumberAndFlightDate("BF101", "22-JAN-16"));
    
        };
    }
    */

}
