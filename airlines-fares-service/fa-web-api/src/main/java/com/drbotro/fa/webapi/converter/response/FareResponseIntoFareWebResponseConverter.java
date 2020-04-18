package com.drbotro.fa.webapi.converter.response;

import org.springframework.stereotype.Component;

import com.drbotro.fa.common.converter.Converter;
import com.drbotro.fa.coreserviceapi.data.response.FareResponse;
import com.drbotro.fa.webapi.response.FareWebResponse;

@Component
public class FareResponseIntoFareWebResponseConverter implements Converter<FareResponse, FareWebResponse>{

    @Override
    public FareWebResponse convert(final FareResponse fareResponse){
        if(fareResponse == null){
            return null;
        }
        return FareWebResponse.builder().withId(fareResponse.getFareResponseId())
                .withFlightNumber(fareResponse.getFlightNumber()).withFlightDate(fareResponse.getFlightDate())
                .withFare(fareResponse.getFare()).withCurrency(fareResponse.getCurrency()).build();
    }

}
