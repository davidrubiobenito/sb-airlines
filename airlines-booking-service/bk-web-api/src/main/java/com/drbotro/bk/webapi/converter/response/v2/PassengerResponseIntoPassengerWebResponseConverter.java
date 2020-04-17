package com.drbotro.bk.webapi.converter.response.v2;

import org.springframework.stereotype.Component;

import com.drbotro.bk.common.converter.Converter;
import com.drbotro.bk.coreserviceapi.data.response.v2.PassengerResponse;
import com.drbotro.bk.webapi.response.v2.PassengerWebResponse;

@Component(value = "passengerResponseIntoPassengerWebResponseConverterV2")
public class PassengerResponseIntoPassengerWebResponseConverter
        implements Converter<PassengerResponse, PassengerWebResponse>{

    @Override
    public PassengerWebResponse convert(final PassengerResponse passengerResponse){
        if(passengerResponse == null){
            return null;
        }
        return PassengerWebResponse.builder().withId(passengerResponse.getId())
                .withFirstname(passengerResponse.getFirstName()).withLastName(passengerResponse.getLastName())
                .withGender(passengerResponse.getGender()).build();

    }

}
