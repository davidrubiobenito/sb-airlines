package com.drbotro.ss.webapi.converter.response.generate;

import java.util.List;

import org.springframework.stereotype.Component;

import com.drbotro.ss.common.converter.Converter;
import com.drbotro.ss.common.response.GenericResponse;
import com.drbotro.ss.webapi.response.FlightWebResponse;

@Component
public class FlightListWebResponseIntoFlightListGenerateWebResponseConverter
        implements Converter<List<FlightWebResponse>, GenericResponse<FlightWebResponse>>{
    private static final String STATUS_OK = "OK";

    @Override
    public GenericResponse<FlightWebResponse> convert(List<FlightWebResponse> flightWebResponse){
        if(flightWebResponse == null){
            return null;
        }
        return GenericResponse.builder().withStatus(STATUS_OK).withData(flightWebResponse).withError(null).build();
    }
}
