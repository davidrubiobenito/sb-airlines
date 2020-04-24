package com.drbotro.fa.webapi.response;

import java.math.BigDecimal;
import java.util.Arrays;

import com.drbotro.fa.common.model.test.AbstractModelBeanTest;

public class GenericResponseFareWebResponseTest extends AbstractModelBeanTest<GenericResponseFareWebResponse>{

    private static final String STATUS_OK = "OK";

    private static final Long FARE_ID = 1L;
    private static final String FLIGHT_NUMBER = "BF100";
    private static final String FLIGHT_DATE = "22-JAN-16";
    private static final BigDecimal FARE = BigDecimal.valueOf(100);
    private static final String CURRENCY = "USD";
    private static final BigDecimal FARE_OTHER = BigDecimal.valueOf(110);

    private static final FareWebResponse FARE_WEB_RESPONSE = FareWebResponse.builder().withId(FARE_ID)
            .withFlightNumber(FLIGHT_NUMBER).withFlightDate(FLIGHT_DATE).withFare(FARE).withCurrency(CURRENCY).build();

    private static final FareWebResponse FARE_WEB_RESPONSE_OTHER = FareWebResponse.builder().withId(FARE_ID)
            .withFlightNumber(FLIGHT_NUMBER).withFlightDate(FLIGHT_DATE).withFare(FARE_OTHER).withCurrency(CURRENCY)
            .build();

    @Override
    public void initEntities(){

        entityA1 = GenericResponseFareWebResponse.builder().withStatus(STATUS_OK)
                .withData(Arrays.asList(FARE_WEB_RESPONSE)).withError(null).build();
        entityA2 = GenericResponseFareWebResponse.builder().withStatus(STATUS_OK)
                .withData(Arrays.asList(FARE_WEB_RESPONSE)).withError(null).build();
        entityB = GenericResponseFareWebResponse.builder().withStatus(STATUS_OK)
                .withData(Arrays.asList(FARE_WEB_RESPONSE_OTHER)).withError(null).build();

    }

}
