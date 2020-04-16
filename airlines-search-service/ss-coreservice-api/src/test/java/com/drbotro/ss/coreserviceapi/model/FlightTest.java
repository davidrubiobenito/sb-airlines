package com.drbotro.ss.coreserviceapi.model;

import com.drbotro.ss.common.model.test.AbstractModelBeanTest;

public class FlightTest extends AbstractModelBeanTest<Flight>{

    private static final String FLIGHT_DATE = "22-JAN-16";
    private static final String DESTINATION = "SFO";
    private static final String ORIGIN = "SEA";
    private static final String FLIGTH_NUMBER = "BF100";
    public static final Fares FARES = Fares.builder().withFare("100").withCurrency("USD").build();
    public static final Inventory INVENTORY = Inventory.builder().withCount(100).build();

    private static final String FLIGTH_NUMBER_OTHER = "BF101";

    @Override
    public void initEntities(){

        entityA1 = Flight.builder().withFlightNumber(FLIGTH_NUMBER).withOrigin(ORIGIN).withDestination(DESTINATION)
                .withFlightDate(FLIGHT_DATE).withFares(FARES).withInventory(INVENTORY).build();

        entityA2 = Flight.builder().withFlightNumber(FLIGTH_NUMBER).withOrigin(ORIGIN).withDestination(DESTINATION)
                .withFlightDate(FLIGHT_DATE).withFares(FARES).withInventory(INVENTORY).build();

        entityB = Flight.builder().withFlightNumber(FLIGTH_NUMBER_OTHER).withOrigin(ORIGIN).withDestination(DESTINATION)
                .withFlightDate(FLIGHT_DATE).withFares(FARES).withInventory(INVENTORY).build();

    }

}
