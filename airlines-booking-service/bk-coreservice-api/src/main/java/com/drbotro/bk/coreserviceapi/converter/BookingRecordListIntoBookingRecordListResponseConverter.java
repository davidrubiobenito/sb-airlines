package com.drbotro.bk.coreserviceapi.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.drbotro.bk.common.converter.Converter;
import com.drbotro.bk.coreserviceapi.data.BookingRecordResponse;
import com.drbotro.bk.repository.model.BookingRecordBooking;

@Component
public class BookingRecordListIntoBookingRecordListResponseConverter
        implements Converter<List<BookingRecordBooking>, List<BookingRecordResponse>>{

    @Autowired
    private PassengerIntoPassengerResponseConverter passengerResposeConverter;

    @Override
    public List<BookingRecordResponse> convert(final List<BookingRecordBooking> bookingRecordList){
        if(bookingRecordList == null){
            return null;
        }

        /*
        bookingRecord.getPassengers().stream().map(pr -> passengerResposeConverter.convert(pr))
                .collect(Collectors.toSet());
                */

        return bookingRecordList.stream().map(br -> BookingRecordResponse.builder().withId(br.getBookingRecordId())
                .withFlightNumber(br.getFlightNumber()).withOrigin(br.getOrigin()).withDestination(br.getDestination())
                .withFlightDate(br.getFlightDate()).withBookingDate(br.getBookingDate()).withFare(br.getFare())
                .withStatus(br.getStatus())
                .withPassengersResponse(br.getPassengers().stream()
                        .map(passenger -> passengerResposeConverter.convert(passenger)).collect(Collectors.toList()))
                .build()).collect(Collectors.toList());

    }

}
