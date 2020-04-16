package com.drbotro.bk.coreserviceapi.inter;

import com.drbotro.bk.common.exception.ErrorException;
import com.drbotro.bk.coreserviceapi.data.BookingRecordRequest;
import com.drbotro.bk.coreserviceapi.data.BookingRecordResponse;

public interface IBookingService{

    BookingRecordResponse saveBookingRecord(BookingRecordRequest bookingRecordRequest) throws ErrorException;

    BookingRecordResponse getBookingRecordById(long id);

    BookingRecordResponse updateStatusBookingRecord(String status, long id);

}
