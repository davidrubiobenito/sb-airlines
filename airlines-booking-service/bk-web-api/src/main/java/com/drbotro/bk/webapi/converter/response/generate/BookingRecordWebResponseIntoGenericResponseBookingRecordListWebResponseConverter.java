package com.drbotro.bk.webapi.converter.response.generate;

import java.util.Arrays;

import org.springframework.stereotype.Component;

import com.drbotro.bk.common.converter.Converter;
import com.drbotro.bk.webapi.response.BookingRecordWebResponse;
import com.drbotro.bk.webapi.response.GenericResponseBookingRecordWebResponse;

@Component
public class BookingRecordWebResponseIntoGenericResponseBookingRecordListWebResponseConverter
        implements Converter<BookingRecordWebResponse, GenericResponseBookingRecordWebResponse>{

    private static final String STATUS_OK = "OK";

    @Override
    public GenericResponseBookingRecordWebResponse convert(final BookingRecordWebResponse bookingRecordWebResponse){
        if(bookingRecordWebResponse == null){
            return null;
        }

        return GenericResponseBookingRecordWebResponse.builder().withStatus(STATUS_OK)
                .withData(Arrays.asList(bookingRecordWebResponse)).withError(null).build();
    }
}
