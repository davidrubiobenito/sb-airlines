package com.drbotro.fa.webapi.converter.response;

import org.springframework.stereotype.Component;

import com.drbotro.fa.common.converter.Converter;
import com.drbotro.fa.coreserviceapi.model.Fare;
import com.drbotro.fa.webapi.response.FareWebResponse;

@Component
public class FareIntoFareWebResponseConverter implements Converter<Fare, FareWebResponse>{

    @Override
    public FareWebResponse convert(final Fare fare){
        if(fare == null){
            return null;
        }
        return FareWebResponse.builder().withId(fare.getId()).withFlightNumber(fare.getFlightNumber())
                .withFlightDate(fare.getFlightDate()).withFare(fare.getFare()).build();
    }

}
