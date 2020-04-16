package com.drbotro.ss.webapi.request;

import com.drbotro.ss.common.model.test.AbstractModelBeanTest;

public class SearchQueryWebRequestTest extends AbstractModelBeanTest<SearchQueryWebRequest>{

    private static final String ORIGIN = "SEA";
    private static final String DESTINATION = "SFO";
    private static final String FLIGHT_DATE = "22-JAN-16";
    private static final String DESTINATION_OTHER = "NYC";

    @Override
    public void initEntities(){

        entityA1 = SearchQueryWebRequest.builder().withOrigin(ORIGIN).withDestination(DESTINATION)
                .withFlightDate(FLIGHT_DATE).build();

        entityA2 = SearchQueryWebRequest.builder().withOrigin(ORIGIN).withDestination(DESTINATION)
                .withFlightDate(FLIGHT_DATE).build();

        entityB = SearchQueryWebRequest.builder().withOrigin(ORIGIN).withDestination(DESTINATION_OTHER)
                .withFlightDate(FLIGHT_DATE).build();

    }
}
