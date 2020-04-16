package com.drbotro.ss.coreserviceapi.data;

import com.drbotro.ss.common.model.test.AbstractModelBeanTest;

public class SearchQueryRequestTest extends AbstractModelBeanTest<SearchQueryRequest>{

    private static final String ORIGIN = "origin";
    private static final String DESTINATION = "destination";
    private static final String FLIGHT_DATE = "26-JAN-16";
    private static final String FLIGHT_DATE_OTHER = "27-JAN-16";

    @Override
    public void initEntities(){

        entityA1 = SearchQueryRequest.builder().withOrigin(ORIGIN).withDestination(DESTINATION).withFlightDate(FLIGHT_DATE)
                .build();
        entityA2 = SearchQueryRequest.builder().withOrigin(ORIGIN).withDestination(DESTINATION).withFlightDate(FLIGHT_DATE)
                .build();
        entityB = SearchQueryRequest.builder().withOrigin(ORIGIN).withDestination(DESTINATION)
                .withFlightDate(FLIGHT_DATE_OTHER).build();

    }

}
