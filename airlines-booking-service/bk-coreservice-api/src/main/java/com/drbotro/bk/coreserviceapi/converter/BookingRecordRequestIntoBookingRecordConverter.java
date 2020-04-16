package com.drbotro.bk.coreserviceapi.converter;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.drbotro.bk.common.converter.Converter;
import com.drbotro.bk.coreserviceapi.data.BookingRecordRequest;
import com.drbotro.bk.repository.model.BookingRecord;

@Component
public class BookingRecordRequestIntoBookingRecordConverter implements Converter<BookingRecordRequest, BookingRecord>{

    @Autowired
    private PassengerRequestIntoPassengerConverter passengerConverter;

    @Override
    public BookingRecord convert(BookingRecordRequest bookingRecordRequest){
        if(bookingRecordRequest == null){
            return null;
        }

        /*
        bookingRecordRequest.getPassengersRequest().stream().map(pr -> passengerConverter.convert(pr))
                .collect(Collectors.toSet());
                */

        return BookingRecord.builder().withFlightNumber(bookingRecordRequest.getFlightNumber())
                .withOrigin(bookingRecordRequest.getOrigin()).withDestination(bookingRecordRequest.getDestination())
                .withFlightDate(bookingRecordRequest.getFlightDate())
                .withBookingDate(bookingRecordRequest.getBookingDate()).withFare(bookingRecordRequest.getFare())
                .withStatus(bookingRecordRequest.getStatus())
                .withPassengers(bookingRecordRequest.getPassengersRequest().stream()
                        .map(passengerRequest -> passengerConverter.convert(passengerRequest))
                        .collect(Collectors.toSet()))
                .build();

    }

}
