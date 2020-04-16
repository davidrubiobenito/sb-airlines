package com.drbotro.ck.webapi.converter.request;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.Date;

import org.junit.Test;

import com.drbotro.ck.coreserviceapi.data.CheckInRecordRequest;
import com.drbotro.ck.webapi.request.CheckInRecordWebRequest;

public class CheckInRecordWebRequestIntoCheckInRecordRequestConverterTest{

    private static final String LAST_NAME = "Franc";
    private static final String FIRST_NAME = "Gean";
    private static final String SEAT_NUMBER = "28A";
    private static final Date CHECK_IN_TIME = new Date();
    private static final String FLIGHT_NUMBER = "BF101";
    private static final String FLIGHT_DATE = "22-JAN-16";
    private static final Long BOOKING_ID = 1L;

    private static final CheckInRecordWebRequest CHECK_IN_RECORD_WEB_REQUEST = CheckInRecordWebRequest.builder()
            .withLastName(LAST_NAME).withFirstName(FIRST_NAME).withSeatNumber(SEAT_NUMBER)
            .withCheckInTime(CHECK_IN_TIME).withFlightNumber(FLIGHT_NUMBER).withFlightDate(FLIGHT_DATE)
            .withBookingId(BOOKING_ID).build();

    private static final CheckInRecordRequest CHECK_IN_RECORD_REQUEST = CheckInRecordRequest.builder()
            .withLastName(LAST_NAME).withFirstName(FIRST_NAME).withSeatNumber(SEAT_NUMBER)
            .withCheckInTime(CHECK_IN_TIME).withFlightNumber(FLIGHT_NUMBER).withFlightDate(FLIGHT_DATE)
            .withBookingId(BOOKING_ID).build();

    private final CheckInRecordWebRequestIntoCheckInRecordRequestConverter converter = new CheckInRecordWebRequestIntoCheckInRecordRequestConverter();

    @Test
    public void shouldConvertNull(){
        assertThat(converter.convert(null), is(nullValue()));
    }

    @Test
    public void whenCheckInRecord_shouldConvert_CheckInRecordResponse(){
        assertThat(converter.convert(CHECK_IN_RECORD_WEB_REQUEST), is(CHECK_IN_RECORD_REQUEST));
    }

}
