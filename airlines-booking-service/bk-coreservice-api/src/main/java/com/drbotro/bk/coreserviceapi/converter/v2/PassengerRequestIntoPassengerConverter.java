package com.drbotro.bk.coreserviceapi.converter.v2;

import org.springframework.stereotype.Component;

import com.drbotro.bk.common.converter.Converter;
import com.drbotro.bk.coreserviceapi.data.request.v2.PassengerRequest;
import com.drbotro.bk.repository.model.v2.PassengerBookingV2;

@Component(value = "passengerRequestIntoPassengerConverterV2")
public class PassengerRequestIntoPassengerConverter implements Converter<PassengerRequest, PassengerBookingV2>{

    @Override
    public PassengerBookingV2 convert(final PassengerRequest passengerRequest){
        if(passengerRequest == null){
            return null;
        }
        return PassengerBookingV2.builder().withFirstname(passengerRequest.getFirstName())
                .withLastName(passengerRequest.getLastName()).withGender(passengerRequest.getGender()).build();

    }

}
