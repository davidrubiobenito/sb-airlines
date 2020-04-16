package com.drbotro.bk.coreserviceapi.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.drbotro.bk.common.converter.Converter;
import com.drbotro.bk.coreserviceapi.data.PassengerResponse;
import com.drbotro.bk.repository.model.Passenger;

@Component
public class PassengerIntoPassengerResponseConverter implements Converter<Passenger, PassengerResponse>{

    @Autowired
    private BookingRecordIntoBookingRecordResponseConverter bookingRecordResponseConverter;

    @Override
    public PassengerResponse convert(Passenger passenger){
        if(passenger == null){
            return null;
        }
        return PassengerResponse.builder().withId(passenger.getId()).withFirstname(passenger.getFirstName())
                .withLastName(passenger.getLastName()).withGender(passenger.getGender())
                .withBookingRecordResponse(bookingRecordResponseConverter.convert(passenger.getBookingRecord()))
                .build();

    }

}
