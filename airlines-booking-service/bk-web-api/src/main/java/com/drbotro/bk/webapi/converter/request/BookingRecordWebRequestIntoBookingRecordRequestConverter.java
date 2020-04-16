package com.drbotro.bk.webapi.converter.request;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.drbotro.bk.common.converter.Converter;
import com.drbotro.bk.coreserviceapi.data.BookingRecordRequest;
import com.drbotro.bk.webapi.request.BookingRecordWebRequest;

@Component
public class BookingRecordWebRequestIntoBookingRecordRequestConverter
        implements Converter<BookingRecordWebRequest, BookingRecordRequest>{

    @Autowired
    private PassengerWebRequestIntoPassengerRequestConverter passengerRequestConverter;

    @Override
    public BookingRecordRequest convert(final BookingRecordWebRequest bookingRecordWebRequest){
        if(bookingRecordWebRequest == null){
            return null;
        }

        return BookingRecordRequest.builder().withFlightNumber(bookingRecordWebRequest.getFlightNumber())
                .withOrigin(bookingRecordWebRequest.getOrigin())
                .withDestination(bookingRecordWebRequest.getDestination())
                .withFlightDate(bookingRecordWebRequest.getFlightDate())
                .withBookingDate(bookingRecordWebRequest.getBookingDate()).withFare(bookingRecordWebRequest.getFare())
                .withStatus(bookingRecordWebRequest.getStatus())
                .withPassengersRequest(bookingRecordWebRequest.getPassengersWebRequest().stream()
                        .map(passengerRequest -> passengerRequestConverter.convert(passengerRequest))
                        .collect(Collectors.toList()))
                .build();

    }

}
