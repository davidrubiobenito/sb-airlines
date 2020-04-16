package com.drbotro.ss.webapi.converter.response;

import org.springframework.stereotype.Component;

import com.drbotro.ss.common.converter.Converter;
import com.drbotro.ss.coreserviceapi.model.Flight;
import com.drbotro.ss.webapi.response.FlightWebResponse;

@Component
public class FlightIntoFlightWebResponseConverter implements Converter<Flight, FlightWebResponse>{

    @Override
    public FlightWebResponse convert(final Flight flight){
        if(flight == null){
            return null;
        }
        return FlightWebResponse.builder().withFligthNumber(flight.getFligthNumber()).withOrigin(flight.getOrigin())
                .withDestination(flight.getDestination()).withFlightDate(flight.getFlightDate())
                .withFares(flight.getFares()).withInventory(flight.getInventory()).build();
    }
}
