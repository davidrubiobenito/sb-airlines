package com.drbotro.bk.coreserviceapi.converter.v2;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.drbotro.bk.common.converter.Converter;
import com.drbotro.bk.coreserviceapi.data.request.v2.BookingRecordRequest;
import com.drbotro.bk.repository.model.v2.BookingRecordBookingV2;

@Component(value = "bookingRecordRequestIntoBookingRecordConverterV2")
public class BookingRecordRequestIntoBookingRecordConverter
        implements Converter<BookingRecordRequest, BookingRecordBookingV2>{

    @Autowired
    private PassengerRequestIntoPassengerConverter passengerConverter;

    @Override
    public BookingRecordBookingV2 convert(final BookingRecordRequest bookingRecordRequest){
        if(bookingRecordRequest == null){
            return null;
        }

        return BookingRecordBookingV2.builder().withFlightNumber(bookingRecordRequest.getFlightNumber())
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
