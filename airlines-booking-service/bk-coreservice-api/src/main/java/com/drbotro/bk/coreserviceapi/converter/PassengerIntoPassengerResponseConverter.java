package com.drbotro.bk.coreserviceapi.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.drbotro.bk.common.converter.Converter;
import com.drbotro.bk.coreserviceapi.data.response.PassengerResponse;
import com.drbotro.bk.repository.model.PassengerBooking;

@Component(value = "passengerIntoPassengerResponseConverter")
public class PassengerIntoPassengerResponseConverter implements Converter<PassengerBooking, PassengerResponse>{

    @Autowired
    private BookingRecordIntoBookingRecordResponseConverter bookingRecordResponseConverter;

    @Override
    public PassengerResponse convert(final PassengerBooking passenger){
        if(passenger == null){
            return null;
        }
        return PassengerResponse.builder().withId(passenger.getPassengerId()).withFirstname(passenger.getFirstName())
                .withLastName(passenger.getLastName()).withGender(passenger.getGender())
                .withBookingRecordResponse(bookingRecordResponseConverter.convert(passenger.getBookingRecord()))
                .build();

    }

}
