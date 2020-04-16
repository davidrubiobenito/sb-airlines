package com.drbotro.bk.coreserviceapi.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.drbotro.bk.common.converter.Converter;
import com.drbotro.bk.coreserviceapi.data.PassengerRequest;
import com.drbotro.bk.repository.model.Passenger;

@Component
public class PassengerRequestIntoPassengerConverter implements Converter<PassengerRequest, Passenger>{

    @Autowired
    private BookingRecordRequestIntoBookingRecordConverter bookingRecordConverter;

    @Override
    public Passenger convert(PassengerRequest passengerRequest){
        if(passengerRequest == null){
            return null;
        }
        return Passenger.builder().withFirstname(passengerRequest.getFirstName())
                .withLastName(passengerRequest.getLastName()).withGender(passengerRequest.getGender())
                .withBookingRecord(bookingRecordConverter.convert(passengerRequest.getBookingRecordRequest())).build();

    }

}
