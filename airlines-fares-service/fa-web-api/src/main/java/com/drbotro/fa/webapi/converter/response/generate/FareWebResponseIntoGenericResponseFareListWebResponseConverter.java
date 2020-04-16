package com.drbotro.fa.webapi.converter.response.generate;

import java.util.Arrays;

import org.springframework.stereotype.Component;

import com.drbotro.fa.common.converter.Converter;
import com.drbotro.fa.webapi.response.FareWebResponse;
import com.drbotro.fa.webapi.response.GenericResponseFareWebResponse;

@Component
public class FareWebResponseIntoGenericResponseFareListWebResponseConverter
        implements Converter<FareWebResponse, GenericResponseFareWebResponse>{

    private static final String STATUS_OK = "OK";

    @Override
    public GenericResponseFareWebResponse convert(final FareWebResponse fareWebResponse){
        if(fareWebResponse == null){
            return null;
        }
        return GenericResponseFareWebResponse.builder().withStatus(STATUS_OK).withData(Arrays.asList(fareWebResponse))
                .withError(null).build();
    }
}
