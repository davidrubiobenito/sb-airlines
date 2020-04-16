package com.drbotro.fa.webapi.response;

import com.drbotro.fa.common.model.test.AbstractModelBeanTest;

public class FareWebResponseTest extends AbstractModelBeanTest<FareWebResponse>{

    private static final String FLIGTH_NUMBER = "BF100";
    private static final String FLIGHT_DATE = "22-JAN-16";
    private static final String FARE = "100";
    private static final String FARE_OTHER = "101";

    @Override
    public void initEntities(){
        entityA1 = FareWebResponse.builder().withFlightNumber(FLIGTH_NUMBER).withFlightDate(FLIGHT_DATE).withFare(FARE)
                .build();
        entityA2 = FareWebResponse.builder().withFlightNumber(FLIGTH_NUMBER).withFlightDate(FLIGHT_DATE).withFare(FARE)
                .build();
        entityB = FareWebResponse.builder().withFlightNumber(FLIGTH_NUMBER).withFlightDate(FLIGHT_DATE)
                .withFare(FARE_OTHER).build();
    }

}
