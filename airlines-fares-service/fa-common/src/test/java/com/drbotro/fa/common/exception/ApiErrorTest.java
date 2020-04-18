package com.drbotro.fa.common.exception;

import java.util.Arrays;

import com.drbotro.fa.common.model.test.AbstractModelBeanTest;

public class ApiErrorTest extends AbstractModelBeanTest<ApiError>{

    public static final int CODE = 404;
    public static final String NOT_FOUND = "Not found";
    public static final String DETAILS = "Not found detail";

    public static final int OTHER_CODE = 500;
    public static final String SERVER_INTERNAL_ERROR = "Server internal error";
    public static final String DETAILS_OTHER = "Server internal error detail";

    @Override
    public void initEntities(){
        entityA1 = ApiError.builder().withCode(CODE).withDescription(NOT_FOUND).withDetails(Arrays.asList(DETAILS))
                .build();
        entityA2 = ApiError.builder().withCode(CODE).withDescription(NOT_FOUND).withDetails(Arrays.asList(DETAILS))
                .build();
        entityB = ApiError.builder().withCode(OTHER_CODE).withDescription(SERVER_INTERNAL_ERROR)
                .withDetails(Arrays.asList(DETAILS_OTHER)).build();
    }

}