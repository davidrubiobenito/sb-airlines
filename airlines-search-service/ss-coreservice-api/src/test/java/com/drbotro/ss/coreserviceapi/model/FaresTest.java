package com.drbotro.ss.coreserviceapi.model;

import com.drbotro.ss.common.model.test.AbstractModelBeanTest;

public class FaresTest extends AbstractModelBeanTest<Fares>{

    private static final String FARE_100 = "100";
    private static final String CURRENCY = "USD";
    private static final String FARE_101 = "101";

    @Override
    public void initEntities(){
        entityA1 = Fares.builder().withFare(FARE_100).withCurrency(CURRENCY).build();
        entityA2 = Fares.builder().withFare(FARE_100).withCurrency(CURRENCY).build();
        entityB = Fares.builder().withFare(FARE_101).withCurrency(CURRENCY).build();
    }

}
