package com.drbotro.fa.coreserviceapi.model;

import com.drbotro.fa.common.model.test.AbstractModelBeanTest;

public class FareTest extends AbstractModelBeanTest<Fare>{

    private static final String FLIGHT_NUMBER = "BF100";
    private static final String FLIGHT_DATE = "22-JAN-16";
    private static final String FARE = "100";
    private static final String FARE_OTHER = "101";

    @Override
    public void initEntities(){
        entityA1 = Fare.builder().withFlightNumber(FLIGHT_NUMBER).withFlightDate(FLIGHT_DATE).withFare(FARE).build();
        entityA2 = Fare.builder().withFlightNumber(FLIGHT_NUMBER).withFlightDate(FLIGHT_DATE).withFare(FARE).build();
        entityB = Fare.builder().withFlightNumber(FLIGHT_NUMBER).withFlightDate(FLIGHT_DATE).withFare(FARE_OTHER)
                .build();
    }

}
