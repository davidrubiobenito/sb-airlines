package com.drbotro.fa.repository.model;

import static com.drbotro.fa.common.security.ApplicationFarePermission.FARE_READ;
import static com.drbotro.fa.common.security.ApplicationFarePermission.FARE_WRITE;

import com.drbotro.fa.common.model.test.AbstractModelBeanTest;

public class PermissionFareTest extends AbstractModelBeanTest<PermissionFare>{

    private static final long PERMISSION_FARE_ID = 1L;
    private static final long PERMISSION_FARE_ID_OTHER = 2L;

    @Override
    public void initEntities(){
        entityA1 = PermissionFare.builder().withPermissionFareIdId(PERMISSION_FARE_ID)
                .withPermissionName(FARE_READ.name()).build();
        entityA2 = PermissionFare.builder().withPermissionFareIdId(PERMISSION_FARE_ID)
                .withPermissionName(FARE_READ.name()).build();
        entityB = PermissionFare.builder().withPermissionFareIdId(PERMISSION_FARE_ID_OTHER)
                .withPermissionName(FARE_WRITE.name()).build();

    }

}
