package com.drbotro.bk.repository.model;

import com.drbotro.bk.common.model.test.AbstractModelBeanTest;

public class InventoryTest extends AbstractModelBeanTest<Inventory>{

    private static final String FLIGHT_NUMBER = "BF100";
    private static final String FLIGHT_DATE = "22-JAN-16";
    private static final int AVAILABLE = 100;
    private static final int AVAILABLE_OTHER = 101;

    @Override
    public void initEntities(){
        entityA1 = Inventory.builder().withFlightNumber(FLIGHT_NUMBER).withFlightDate(FLIGHT_DATE)
                .withAvailable(AVAILABLE).build();
        entityA2 = Inventory.builder().withFlightNumber(FLIGHT_NUMBER).withFlightDate(FLIGHT_DATE)
                .withAvailable(AVAILABLE).build();
        entityB = Inventory.builder().withFlightNumber(FLIGHT_NUMBER).withFlightDate(FLIGHT_DATE)
                .withAvailable(AVAILABLE_OTHER).build();
    }

}
