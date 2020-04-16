package com.drbotro.bk.webapi.converter.response;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.drbotro.bk.common.converter.Converter;
import com.drbotro.bk.coreserviceapi.data.BookingRecordResponse;
import com.drbotro.bk.webapi.response.BookingRecordWebResponse;

@Component
public class BookingRecordResponseIntoBookingRecordWebResponseConverter
        implements Converter<BookingRecordResponse, BookingRecordWebResponse>{

    @Autowired
    private PassengerResponseIntoPassengerWebResponseConverter passengerWebResposeConverter;

    @Override
    public BookingRecordWebResponse convert(BookingRecordResponse bookingRecordResponse){
        if(bookingRecordResponse == null){
            return null;
        }

        /*
        bookingRecordResponse.getPassengersResponse().stream().map(pr -> passengerWebResposeConverter.convert(pr))
                .collect(Collectors.toSet());
                */

        return BookingRecordWebResponse.builder().withId(bookingRecordResponse.getId())
                .withFlightNumber(bookingRecordResponse.getFlightNumber()).withOrigin(bookingRecordResponse.getOrigin())
                .withDestination(bookingRecordResponse.getDestination())
                .withFlightDate(bookingRecordResponse.getFlightDate())
                .withBookingDate(bookingRecordResponse.getBookingDate()).withFare(bookingRecordResponse.getFare())
                .withStatus(bookingRecordResponse.getStatus())
                .withPassengersWebResponse(bookingRecordResponse.getPassengersResponse().stream()
                        .map(passengerResponse -> passengerWebResposeConverter.convert(passengerResponse))
                        .collect(Collectors.toSet()))
                .build();

    }

}
