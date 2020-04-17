package com.drbotro.bk.webapi.converter.request.v2;

import org.springframework.stereotype.Component;

import com.drbotro.bk.common.converter.Converter;
import com.drbotro.bk.coreserviceapi.data.request.v2.PassengerRequest;
import com.drbotro.bk.webapi.request.v2.PassengerWebRequest;

@Component(value = "passengerWebRequestIntoPassengerRequestConverterV2")
public class PassengerWebRequestIntoPassengerRequestConverter
        implements Converter<PassengerWebRequest, PassengerRequest>{

    @Override
    public PassengerRequest convert(final PassengerWebRequest passengerWebRequest){
        if(passengerWebRequest == null){
            return null;
        }
        return PassengerRequest.builder().withFirstname(passengerWebRequest.getFirstName())
                .withLastName(passengerWebRequest.getLastName()).withGender(passengerWebRequest.getGender()).build();

    }

}
