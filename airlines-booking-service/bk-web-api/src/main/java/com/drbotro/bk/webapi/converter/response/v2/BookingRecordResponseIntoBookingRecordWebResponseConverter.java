package com.drbotro.bk.webapi.converter.response.v2;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.drbotro.bk.common.converter.Converter;
import com.drbotro.bk.coreserviceapi.data.response.v2.BookingRecordResponse;
import com.drbotro.bk.webapi.response.v2.BookingRecordWebResponse;

@Component(value = "bookingRecordResponseIntoBookingRecordWebResponseConverterV2")
public class BookingRecordResponseIntoBookingRecordWebResponseConverter
        implements Converter<BookingRecordResponse, BookingRecordWebResponse>{

    @Autowired
    private PassengerResponseIntoPassengerWebResponseConverter passengerWebResposeConverter;

    @Override
    public BookingRecordWebResponse convert(final BookingRecordResponse bookingRecordResponse){
        if(bookingRecordResponse == null){
            return null;
        }

        return BookingRecordWebResponse.builder().withId(bookingRecordResponse.getId())
                .withFlightNumber(bookingRecordResponse.getFlightNumber()).withOrigin(bookingRecordResponse.getOrigin())
                .withDestination(bookingRecordResponse.getDestination())
                .withFlightDate(bookingRecordResponse.getFlightDate())
                .withBookingDate(bookingRecordResponse.getBookingDate()).withFare(bookingRecordResponse.getFare())
                .withStatus(bookingRecordResponse.getStatus())
                .withPassengersWebResponse(bookingRecordResponse.getPassengersResponse().stream()
                        .map(passengerResponse -> passengerWebResposeConverter.convert(passengerResponse))
                        .collect(Collectors.toList()))
                .build();

    }

}
