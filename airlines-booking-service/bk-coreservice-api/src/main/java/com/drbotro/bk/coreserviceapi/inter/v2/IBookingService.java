package com.drbotro.bk.coreserviceapi.inter.v2;

import java.util.List;

import com.drbotro.bk.common.exception.ErrorException;
import com.drbotro.bk.coreserviceapi.data.request.v2.BookingRecordRequest;
import com.drbotro.bk.coreserviceapi.data.response.v2.BookingRecordResponse;
import com.drbotro.bk.repository.model.v2.BookingRecordBookingV2;

public interface IBookingService{

    BookingRecordResponse saveBookingRecord(BookingRecordRequest bookingRecordRequest) throws ErrorException;

    BookingRecordResponse getBookingRecordById(long id);

    BookingRecordResponse updateStatusBookingRecord(String status, long id);

    List<BookingRecordResponse> findAllBookingRecord();

    BookingRecordBookingV2 addBooking(BookingRecordBookingV2 bookingRecordBooking);

}
