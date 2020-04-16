package com.drbotro.ck.webapi.converter.response.generate;

import java.util.Arrays;

import org.springframework.stereotype.Component;

import com.drbotro.ck.common.converter.Converter;
import com.drbotro.ck.common.response.GenericResponse;

@Component
public class LongIntoLongListGenerateWebResponseConverter implements Converter<Long, GenericResponse<Long>>{

    private static final String STATUS_OK = "OK";

    @Override
    public GenericResponse<Long> convert(final Long object){
        if(object == null){
            return null;
        }
        return GenericResponse.builder().withStatus(STATUS_OK).withData(Arrays.asList(object)).withError(null).build();
    }
}