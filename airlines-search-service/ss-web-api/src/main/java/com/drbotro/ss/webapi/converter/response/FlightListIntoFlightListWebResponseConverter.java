package com.drbotro.ss.webapi.converter.response;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.drbotro.ss.common.converter.Converter;
import com.drbotro.ss.coreserviceapi.model.Flight;
import com.drbotro.ss.webapi.response.FlightWebResponse;

@Component
public class FlightListIntoFlightListWebResponseConverter implements Converter<List<Flight>, List<FlightWebResponse>>{

    @Override
    public List<FlightWebResponse> convert(final List<Flight> flight){
        if(flight == null){
            return null;
        }

        return flight.stream()
                .map(f -> FlightWebResponse.builder().withFligthNumber(f.getFligthNumber()).withOrigin(f.getOrigin())
                        .withDestination(f.getDestination()).withFlightDate(f.getFlightDate()).withFares(f.getFares())
                        .withInventory(f.getInventory()).build())
                .collect(Collectors.toList());
    }
}
