package com.drbotro.bk.webapi.converter.response.generate.v2;

import java.util.List;

import org.springframework.stereotype.Component;

import com.drbotro.bk.common.converter.Converter;
import com.drbotro.bk.webapi.response.v2.BookingRecordWebResponse;
import com.drbotro.bk.webapi.response.v2.GenericResponseBookingRecordWebResponse;

@Component(value = "bookingRecordListWebResponseIntoGenericResponseBookingRecordListWebResponseConverterV2")
public class BookingRecordListWebResponseIntoGenericResponseBookingRecordListWebResponseConverter
        implements Converter<List<BookingRecordWebResponse>, GenericResponseBookingRecordWebResponse>{

    private static final String STATUS_OK = "OK";

    @Override
    public GenericResponseBookingRecordWebResponse convert(
            final List<BookingRecordWebResponse> bookingRecordWebResponse){
        if(bookingRecordWebResponse == null){
            return null;
        }

        return GenericResponseBookingRecordWebResponse.builder().withStatus(STATUS_OK)
                .withData(bookingRecordWebResponse).withError(null).build();
    }
}
