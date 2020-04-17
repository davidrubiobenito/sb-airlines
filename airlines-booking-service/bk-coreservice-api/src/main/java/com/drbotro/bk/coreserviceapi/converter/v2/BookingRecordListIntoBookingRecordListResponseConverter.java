package com.drbotro.bk.coreserviceapi.converter.v2;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.drbotro.bk.common.converter.Converter;
import com.drbotro.bk.coreserviceapi.data.response.v2.BookingRecordResponse;
import com.drbotro.bk.repository.model.v2.BookingRecordBookingV2;

@Component(value = "bookingRecordListIntoBookingRecordListResponseConverterV2")
public class BookingRecordListIntoBookingRecordListResponseConverter
        implements Converter<List<BookingRecordBookingV2>, List<BookingRecordResponse>>{

    @Autowired
    private PassengerIntoPassengerResponseConverter passengerResposeConverter;

    @Override
    public List<BookingRecordResponse> convert(final List<BookingRecordBookingV2> bookingRecordList){
        if(bookingRecordList == null){
            return null;
        }

        return bookingRecordList.stream().map(br -> BookingRecordResponse.builder().withId(br.getBookingRecordId())
                .withFlightNumber(br.getFlightNumber()).withOrigin(br.getOrigin()).withDestination(br.getDestination())
                .withFlightDate(br.getFlightDate()).withBookingDate(br.getBookingDate()).withFare(br.getFare())
                .withStatus(br.getStatus())
                .withPassengersResponse(br.getPassengers().stream()
                        .map(passenger -> passengerResposeConverter.convert(passenger)).collect(Collectors.toList()))
                .build()).collect(Collectors.toList());
    }

}
