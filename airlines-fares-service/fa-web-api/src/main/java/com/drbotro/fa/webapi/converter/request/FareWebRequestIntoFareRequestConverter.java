package com.drbotro.fa.webapi.converter.request;

import org.springframework.stereotype.Component;

import com.drbotro.fa.common.converter.Converter;
import com.drbotro.fa.coreserviceapi.data.request.FareRequest;
import com.drbotro.fa.webapi.request.FareWebRequest;

@Component
public class FareWebRequestIntoFareRequestConverter implements Converter<FareWebRequest, FareRequest>{

    @Override
    public FareRequest convert(final FareWebRequest fareWebRequest){
        if(fareWebRequest == null){
            return null;
        }

        return FareRequest.builder().withFlightNumber(fareWebRequest.getFlightNumber())
                .withFlightDate(fareWebRequest.getFlightDate()).withFare(fareWebRequest.getFare())
                .withCurrency(fareWebRequest.getCurrency()).build();
    }

}
