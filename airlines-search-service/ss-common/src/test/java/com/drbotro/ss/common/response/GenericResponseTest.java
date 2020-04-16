package com.drbotro.ss.common.response;

import java.util.Arrays;
import java.util.List;

import com.drbotro.ss.common.exception.ErrorException;
import com.drbotro.ss.common.model.test.AbstractModelBeanTest;

public class GenericResponseTest extends AbstractModelBeanTest<GenericResponse>{

    private static final String STATUS_OK = "OK";
    private static final String STATUS_KO = "KO";
    private static final Integer ID_1 = 1;
    private static final Integer ID_2 = 2;
    private static final List<Integer> ID_S = Arrays.asList(ID_1, ID_2);
    private static final Integer CODE_ERROR = 404;
    private static final String DESCRIPTION = "Not found";
    private static final ErrorException ERROR_RESPONSE = ErrorException.builder().withError(CODE_ERROR)
            .withDescription(DESCRIPTION).build();

    @Override
    public void initEntities(){
        this.entityA1 = GenericResponse.builder().withStatus(STATUS_OK).withData(ID_S).withError(null).build();
        this.entityA2 = GenericResponse.builder().withStatus(STATUS_OK).withData(ID_S).withError(null).build();
        this.entityB = GenericResponse.builder().withStatus(STATUS_KO).withData(ID_S).withError(ERROR_RESPONSE).build();
    }

}