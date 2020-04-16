package com.drbotro.bk.webapi.converter.response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.drbotro.bk.common.converter.Converter;
import com.drbotro.bk.coreserviceapi.data.PassengerResponse;
import com.drbotro.bk.webapi.response.PassengerWebResponse;

@Component
public class PassengerResponseIntoPassengerWebResponseConverter
        implements Converter<PassengerResponse, PassengerWebResponse>{

    @Autowired
    private BookingRecordResponseIntoBookingRecordWebResponseConverter bookingRecordWebResponseConverter;

    @Override
    public PassengerWebResponse convert(PassengerResponse passengerResponse){
        if(passengerResponse == null){
            return null;
        }
        return PassengerWebResponse.builder().withId(passengerResponse.getId())
                .withFirstname(passengerResponse.getFirstName()).withLastName(passengerResponse.getLastName())
                .withGender(passengerResponse.getGender())
                .withBookingRecordWebResponse(
                        bookingRecordWebResponseConverter.convert(passengerResponse.getBookingRecordResponse()))
                .build();

    }

}
