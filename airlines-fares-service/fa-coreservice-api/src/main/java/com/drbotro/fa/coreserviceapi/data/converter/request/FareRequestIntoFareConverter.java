package com.drbotro.fa.coreserviceapi.data.converter.request;

import org.springframework.stereotype.Component;

import com.drbotro.fa.common.converter.Converter;
import com.drbotro.fa.coreserviceapi.data.request.FareRequest;
import com.drbotro.fa.repository.model.Fare;

@Component
public class FareRequestIntoFareConverter implements Converter<FareRequest, Fare>{

    @Override
    public Fare convert(final FareRequest fareRequest){
        if(fareRequest == null){
            return null;
        }

        return Fare.builder().withFlightNumber(fareRequest.getFlightNumber())
                .withFlightDate(fareRequest.getFlightDate()).withFare(fareRequest.getFare())
                .withCurrency(fareRequest.getCurrency()).build();
    }

}
