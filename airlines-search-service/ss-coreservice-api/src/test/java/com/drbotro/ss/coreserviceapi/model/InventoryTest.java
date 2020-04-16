package com.drbotro.ss.coreserviceapi.model;

import com.drbotro.ss.common.model.test.AbstractModelBeanTest;

public class InventoryTest extends AbstractModelBeanTest<Inventory>{

    private static final int COUNT_100 = 100;
    private static final int COUNT_200 = 200;

    @Override
    public void initEntities(){
        entityA1 = Inventory.builder().withCount(COUNT_100).build();
        entityA2 = Inventory.builder().withCount(COUNT_100).build();
        entityB = Inventory.builder().withCount(COUNT_200).build();
    }

}
