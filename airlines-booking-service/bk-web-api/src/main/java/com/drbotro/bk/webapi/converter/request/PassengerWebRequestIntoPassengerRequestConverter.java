package com.drbotro.bk.webapi.converter.request;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.drbotro.bk.common.converter.Converter;
import com.drbotro.bk.coreserviceapi.data.request.PassengerRequest;
import com.drbotro.bk.webapi.request.PassengerWebRequest;

@Component(value = "passengerWebRequestIntoPassengerRequestConverter")
public class PassengerWebRequestIntoPassengerRequestConverter
        implements Converter<PassengerWebRequest, PassengerRequest>{

    @Autowired
    private BookingRecordWebRequestIntoBookingRecordRequestConverter bookingRecordRequestConverter;

    @Override
    public PassengerRequest convert(final PassengerWebRequest passengerWebRequest){
        if(passengerWebRequest == null){
            return null;
        }
        return PassengerRequest.builder().withFirstname(passengerWebRequest.getFirstName())
                .withLastName(passengerWebRequest.getLastName()).withGender(passengerWebRequest.getGender())
                .withBookingRecordRequest(
                        bookingRecordRequestConverter.convert(passengerWebRequest.getBookingRecordWebRequest()))
                .build();

    }

}
