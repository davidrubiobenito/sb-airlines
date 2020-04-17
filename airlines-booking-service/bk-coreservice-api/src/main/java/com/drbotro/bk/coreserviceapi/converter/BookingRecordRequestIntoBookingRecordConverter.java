package com.drbotro.bk.coreserviceapi.converter;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.drbotro.bk.common.converter.Converter;
import com.drbotro.bk.coreserviceapi.data.request.BookingRecordRequest;
import com.drbotro.bk.repository.model.BookingRecordBooking;

@Component(value = "bookingRecordRequestIntoBookingRecordConverter")
public class BookingRecordRequestIntoBookingRecordConverter
        implements Converter<BookingRecordRequest, BookingRecordBooking>{

    @Autowired
    private PassengerRequestIntoPassengerConverter passengerConverter;

    @Override
    public BookingRecordBooking convert(final BookingRecordRequest bookingRecordRequest){
        if(bookingRecordRequest == null){
            return null;
        }

        /*
        bookingRecordRequest.getPassengersRequest().stream().map(pr -> passengerConverter.convert(pr))
                .collect(Collectors.toSet());
                */

        return BookingRecordBooking.builder().withFlightNumber(bookingRecordRequest.getFlightNumber())
                .withOrigin(bookingRecordRequest.getOrigin()).withDestination(bookingRecordRequest.getDestination())
                .withFlightDate(bookingRecordRequest.getFlightDate())
                .withBookingDate(bookingRecordRequest.getBookingDate()).withFare(bookingRecordRequest.getFare())
                .withStatus(bookingRecordRequest.getStatus())
                .withPassengers(bookingRecordRequest.getPassengersRequest().stream()
                        .map(passengerRequest -> passengerConverter.convert(passengerRequest))
                        .collect(Collectors.toList()))
                .build();

    }

}
