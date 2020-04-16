package com.drbotro.ss.webapi.converter.response.generate;

import java.util.Arrays;

import org.springframework.stereotype.Component;

import com.drbotro.ss.common.converter.Converter;
import com.drbotro.ss.common.response.GenericResponse;
import com.drbotro.ss.webapi.response.FlightWebResponse;

@Component
public class FlightWebResponseIntoFlightListGenerateWebResponseConverter
        implements Converter<FlightWebResponse, GenericResponse<FlightWebResponse>>{

    private static final String STATUS_OK = "OK";

    @Override
    public GenericResponse<FlightWebResponse> convert(FlightWebResponse flightWebResponse){
        if(flightWebResponse == null){
            return null;
        }
        return GenericResponse.builder().withStatus(STATUS_OK).withData(Arrays.asList(flightWebResponse))
                .withError(null).build();
    }
}
