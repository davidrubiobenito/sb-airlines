package com.drbotro.bk.coreserviceapi.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.drbotro.bk.common.converter.Converter;
import com.drbotro.bk.coreserviceapi.data.request.PassengerRequest;
import com.drbotro.bk.repository.model.PassengerBooking;

@Component(value = "passengerRequestIntoPassengerConverter")
public class PassengerRequestIntoPassengerConverter implements Converter<PassengerRequest, PassengerBooking>{

    @Autowired
    private BookingRecordRequestIntoBookingRecordConverter bookingRecordConverter;

    @Override
    public PassengerBooking convert(final PassengerRequest passengerRequest){
        if(passengerRequest == null){
            return null;
        }
        return PassengerBooking.builder().withFirstname(passengerRequest.getFirstName())
                .withLastName(passengerRequest.getLastName()).withGender(passengerRequest.getGender())
                .withBookingRecord(bookingRecordConverter.convert(passengerRequest.getBookingRecordRequest())).build();

    }

}
