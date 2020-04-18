package com.drbotro.fa.coreserviceapi.data.converter.response;

import org.springframework.stereotype.Component;

import com.drbotro.fa.common.converter.Converter;
import com.drbotro.fa.coreserviceapi.data.response.FareResponse;
import com.drbotro.fa.repository.model.Fare;

@Component
public class FareIntoFareResponseConverter implements Converter<Fare, FareResponse>{

    @Override
    public FareResponse convert(final Fare fare){
        if(fare == null){
            return null;
        }

        return FareResponse.builder().withFareResponseId(fare.getFareId()).withFlightNumber(fare.getFlightNumber())
                .withFlightDate(fare.getFlightDate()).withFare(fare.getFare()).withCurrency(fare.getCurrency()).build();
    }

}
