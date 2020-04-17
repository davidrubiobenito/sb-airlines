package com.drbotro.bk.coreserviceapi.inter;

import java.util.List;

import com.drbotro.bk.common.exception.ErrorException;
import com.drbotro.bk.coreserviceapi.data.request.BookingRecordRequest;
import com.drbotro.bk.coreserviceapi.data.response.BookingRecordResponse;
import com.drbotro.bk.repository.model.BookingRecordBooking;

public interface IBookingService{

    BookingRecordBooking saveBookingRecord(BookingRecordRequest bookingRecordRequest) throws ErrorException;

    BookingRecordResponse getBookingRecordById(long id);

    BookingRecordResponse updateStatusBookingRecord(String status, long id);

    List<BookingRecordResponse> findAllBookingRecord();

    BookingRecordBooking addBooking(BookingRecordBooking bookingRecordBooking);

}
