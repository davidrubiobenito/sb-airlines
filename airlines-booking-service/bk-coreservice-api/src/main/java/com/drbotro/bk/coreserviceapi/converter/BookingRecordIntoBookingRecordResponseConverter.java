package com.drbotro.bk.coreserviceapi.converter;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.drbotro.bk.common.converter.Converter;
import com.drbotro.bk.coreserviceapi.data.response.BookingRecordResponse;
import com.drbotro.bk.repository.model.BookingRecordBooking;

@Component(value = "bookingRecordIntoBookingRecordResponseConverter")
public class BookingRecordIntoBookingRecordResponseConverter
        implements Converter<BookingRecordBooking, BookingRecordResponse>{

    @Autowired
    private PassengerIntoPassengerResponseConverter passengerResposeConverter;

    @Override
    public BookingRecordResponse convert(final BookingRecordBooking bookingRecord){
        if(bookingRecord == null){
            return null;
        }

        /*
        bookingRecord.getPassengers().stream().map(pr -> passengerResposeConverter.convert(pr))
                .collect(Collectors.toSet());
                */

        return BookingRecordResponse.builder().withId(bookingRecord.getBookingRecordId())
                .withFlightNumber(bookingRecord.getFlightNumber()).withOrigin(bookingRecord.getOrigin())
                .withDestination(bookingRecord.getDestination()).withFlightDate(bookingRecord.getFlightDate())
                .withBookingDate(bookingRecord.getBookingDate()).withFare(bookingRecord.getFare())
                .withStatus(bookingRecord.getStatus())
                .withPassengersResponse(bookingRecord.getPassengers().stream()
                        .map(passenger -> passengerResposeConverter.convert(passenger)).collect(Collectors.toList()))
                .build();

    }

}
