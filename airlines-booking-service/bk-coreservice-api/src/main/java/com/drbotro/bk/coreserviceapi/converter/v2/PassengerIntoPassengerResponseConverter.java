package com.drbotro.bk.coreserviceapi.converter.v2;

import org.springframework.stereotype.Component;

import com.drbotro.bk.common.converter.Converter;
import com.drbotro.bk.coreserviceapi.data.response.v2.PassengerResponse;
import com.drbotro.bk.repository.model.v2.PassengerBookingV2;

@Component(value = "passengerIntoPassengerResponseConverterV2")
public class PassengerIntoPassengerResponseConverter implements Converter<PassengerBookingV2, PassengerResponse>{

    @Override
    public PassengerResponse convert(final PassengerBookingV2 passenger){
        if(passenger == null){
            return null;
        }
        return PassengerResponse.builder().withId(passenger.getPassengerId()).withFirstname(passenger.getFirstName())
                .withLastName(passenger.getLastName()).withGender(passenger.getGender()).build();

    }

}
