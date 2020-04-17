package com.drbotro.bk.coreserviceapi.converter.v2;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.drbotro.bk.common.converter.Converter;
import com.drbotro.bk.coreserviceapi.data.response.v2.BookingRecordResponse;
import com.drbotro.bk.repository.model.v2.BookingRecordBookingV2;

@Component(value = "bookingRecordIntoBookingRecordResponseConverterV2")
public class BookingRecordIntoBookingRecordResponseConverter
        implements Converter<BookingRecordBookingV2, BookingRecordResponse>{

    @Autowired
    private PassengerIntoPassengerResponseConverter passengerResposeConverter;

    @Override
    public BookingRecordResponse convert(final BookingRecordBookingV2 bookingRecord){
        if(bookingRecord == null){
            return null;
        }

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
