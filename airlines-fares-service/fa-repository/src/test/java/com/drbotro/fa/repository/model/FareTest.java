package com.drbotro.fa.repository.model;

import java.math.BigDecimal;

import com.drbotro.fa.common.model.test.AbstractModelBeanTest;

public class FareTest extends AbstractModelBeanTest<Fare>{

    private static final Long FARE_ID = 1L;
    private static final String FLIGHT_NUMBER = "BF100";
    private static final String FLIGHT_DATE = "22-JAN-16";
    private static final BigDecimal FARE = BigDecimal.valueOf(100);
    private static final String CURRENCY = "USD";
    private static final Long FARE_ID_OTHER = 2L;
    private static final BigDecimal FARE_OTHER = BigDecimal.valueOf(101);

    @Override
    public void initEntities(){
        entityA1 = Fare.builder().withFareId(FARE_ID).withFlightNumber(FLIGHT_NUMBER).withFlightDate(FLIGHT_DATE)
                .withFare(FARE).withCurrency(CURRENCY).build();
        entityA2 = Fare.builder().withFareId(FARE_ID).withFlightNumber(FLIGHT_NUMBER).withFlightDate(FLIGHT_DATE)
                .withFare(FARE).withCurrency(CURRENCY).build();
        entityB = Fare.builder().withFareId(FARE_ID_OTHER).withFlightNumber(FLIGHT_NUMBER).withFlightDate(FLIGHT_DATE)
                .withFare(FARE_OTHER).withCurrency(CURRENCY).build();
    }

}
