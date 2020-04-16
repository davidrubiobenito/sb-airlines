package com.drbotro.ss.webapi.response;

import com.drbotro.ss.common.model.test.AbstractModelBeanTest;
import com.drbotro.ss.coreserviceapi.model.Fares;
import com.drbotro.ss.coreserviceapi.model.Inventory;

public class FlightWebResponseTest extends AbstractModelBeanTest<FlightWebResponse>{

    private static final String FLIGHT_DATE = "22-JAN-16";
    private static final String DESTINATION = "SFO";
    private static final String ORIGIN = "SEA";
    private static final String FLIGTH_NUMBER = "BF100";
    public static final Fares FARES = Fares.builder().withFare("100").withCurrency("USD").build();
    public static final Inventory INVENTORY = Inventory.builder().withCount(100).build();

    private static final String FLIGTH_NUMBER_OTHER = "BF101";

    @Override
    public void initEntities(){

        entityA1 = FlightWebResponse.builder().withFligthNumber(FLIGTH_NUMBER).withOrigin(ORIGIN)
                .withDestination(DESTINATION).withFlightDate(FLIGHT_DATE).withFares(FARES).withInventory(INVENTORY)
                .build();

        entityA2 = FlightWebResponse.builder().withFligthNumber(FLIGTH_NUMBER).withOrigin(ORIGIN)
                .withDestination(DESTINATION).withFlightDate(FLIGHT_DATE).withFares(FARES).withInventory(INVENTORY)
                .build();

        entityB = FlightWebResponse.builder().withFligthNumber(FLIGTH_NUMBER_OTHER).withOrigin(ORIGIN)
                .withDestination(DESTINATION).withFlightDate(FLIGHT_DATE).withFares(FARES).withInventory(INVENTORY)
                .build();

    }
}
