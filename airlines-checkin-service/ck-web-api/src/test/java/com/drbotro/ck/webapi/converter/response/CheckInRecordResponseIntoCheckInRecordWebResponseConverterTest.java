package com.drbotro.ck.webapi.converter.response;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.Date;

import org.junit.Test;

import com.drbotro.ck.coreserviceapi.data.CheckInRecordResponse;
import com.drbotro.ck.webapi.response.CheckInRecordWebResponse;

public class CheckInRecordResponseIntoCheckInRecordWebResponseConverterTest{

    private static final String LAST_NAME = "Franc";
    private static final String FIRST_NAME = "Gean";
    private static final String SEAT_NUMBER = "28A";
    private static final Date CHECK_IN_TIME = new Date();
    private static final String FLIGHT_NUMBER = "BF101";
    private static final String FLIGHT_DATE = "22-JAN-16";
    private static final Long BOOKING_ID = 1L;

    private static final CheckInRecordResponse CHECK_IN_RECORD_RESPONSE = CheckInRecordResponse.builder()
            .withLastName(LAST_NAME).withFirstName(FIRST_NAME).withSeatNumber(SEAT_NUMBER)
            .withCheckInTime(CHECK_IN_TIME).withFlightNumber(FLIGHT_NUMBER).withFlightDate(FLIGHT_DATE)
            .withBookingId(BOOKING_ID).build();

    private static final CheckInRecordWebResponse CHECK_IN_RECORD_WEB_RESPONSE = CheckInRecordWebResponse.builder()
            .withLastName(LAST_NAME).withFirstName(FIRST_NAME).withSeatNumber(SEAT_NUMBER)
            .withCheckInTime(CHECK_IN_TIME).withFlightNumber(FLIGHT_NUMBER).withFlightDate(FLIGHT_DATE)
            .withBookingId(BOOKING_ID).build();

    private final CheckInRecordResponseIntoCheckInRecordWebResponseConverter converter = new CheckInRecordResponseIntoCheckInRecordWebResponseConverter();

    @Test
    public void shouldConvertNull(){
        assertThat(converter.convert(null), is(nullValue()));
    }

    @Test
    public void whenCheckInRecord_shouldConvert_CheckInRecordResponse(){
        assertThat(converter.convert(CHECK_IN_RECORD_RESPONSE), is(CHECK_IN_RECORD_WEB_RESPONSE));
    }

}
