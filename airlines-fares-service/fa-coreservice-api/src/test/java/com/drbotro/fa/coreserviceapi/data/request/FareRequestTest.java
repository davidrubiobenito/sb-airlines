package com.drbotro.fa.coreserviceapi.data.request;

import java.math.BigDecimal;

import com.drbotro.fa.common.model.test.AbstractModelBeanTest;

public class FareRequestTest extends AbstractModelBeanTest<FareRequest>{

    private static final String FLIGHT_NUMBER = "BF100";
    private static final String FLIGHT_DATE = "22-JAN-16";
    private static final BigDecimal FARE = BigDecimal.valueOf(100);
    private static final String CURRENCY = "USD";
    private static final BigDecimal FARE_OTHER = BigDecimal.valueOf(101);

    @Override
    public void initEntities(){
        entityA1 = FareRequest.builder().withFlightNumber(FLIGHT_NUMBER).withFlightDate(FLIGHT_DATE).withFare(FARE)
                .withCurrency(CURRENCY).build();
        entityA2 = FareRequest.builder().withFlightNumber(FLIGHT_NUMBER).withFlightDate(FLIGHT_DATE).withFare(FARE)
                .withCurrency(CURRENCY).build();
        entityB = FareRequest.builder().withFlightNumber(FLIGHT_NUMBER).withFlightDate(FLIGHT_DATE).withFare(FARE_OTHER)
                .withCurrency(CURRENCY).build();
    }

}
