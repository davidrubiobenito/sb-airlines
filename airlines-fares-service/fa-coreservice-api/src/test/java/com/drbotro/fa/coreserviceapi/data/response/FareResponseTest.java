package com.drbotro.fa.coreserviceapi.data.response;

import java.math.BigDecimal;

import com.drbotro.fa.common.model.test.AbstractModelBeanTest;

public class FareResponseTest extends AbstractModelBeanTest<FareResponse>{

    private static final Long FARE_RESPONSE_ID = 1L;
    private static final String FLIGHT_NUMBER = "BF100";
    private static final String FLIGHT_DATE = "22-JAN-16";
    private static final BigDecimal FARE = BigDecimal.valueOf(100);
    private static final String CURRENCY = "USD";
    private static final Long FARE_RESPONSE_ID_OTHER = 2L;
    private static final BigDecimal FARE_OTHER = BigDecimal.valueOf(101);

    @Override
    public void initEntities(){
        entityA1 = FareResponse.builder().withFareResponseId(FARE_RESPONSE_ID).withFlightNumber(FLIGHT_NUMBER)
                .withFlightDate(FLIGHT_DATE).withFare(FARE).withCurrency(CURRENCY).build();
        entityA2 = FareResponse.builder().withFareResponseId(FARE_RESPONSE_ID).withFlightNumber(FLIGHT_NUMBER)
                .withFlightDate(FLIGHT_DATE).withFare(FARE).withCurrency(CURRENCY).build();
        entityB = FareResponse.builder().withFareResponseId(FARE_RESPONSE_ID_OTHER).withFlightNumber(FLIGHT_NUMBER)
                .withFlightDate(FLIGHT_DATE).withFare(FARE_OTHER).withCurrency(CURRENCY).build();
    }

}