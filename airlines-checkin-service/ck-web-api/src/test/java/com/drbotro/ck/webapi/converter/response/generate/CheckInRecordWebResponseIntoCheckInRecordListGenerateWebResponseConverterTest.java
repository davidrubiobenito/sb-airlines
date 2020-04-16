package com.drbotro.ck.webapi.converter.response.generate;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.Arrays;
import java.util.Date;

import org.junit.Test;

import com.drbotro.ck.common.response.GenericResponse;
import com.drbotro.ck.webapi.response.CheckInRecordWebResponse;

public class CheckInRecordWebResponseIntoCheckInRecordListGenerateWebResponseConverterTest{

    private static final String STATUS_OK = "OK";
    private static final String LAST_NAME = "Franc";
    private static final String FIRST_NAME = "Gean";
    private static final String SEAT_NUMBER = "28A";
    private static final Date CHECK_IN_TIME = new Date();
    private static final String FLIGHT_NUMBER = "BF101";
    private static final String FLIGHT_DATE = "22-JAN-16";
    private static final Long BOOKING_ID = 1L;

    private static final CheckInRecordWebResponse CHECK_IN__RECORD_WEB_RESPONSE = CheckInRecordWebResponse.builder()
            .withLastName(LAST_NAME).withFirstName(FIRST_NAME).withSeatNumber(SEAT_NUMBER)
            .withCheckInTime(CHECK_IN_TIME).withFlightNumber(FLIGHT_NUMBER).withFlightDate(FLIGHT_DATE)
            .withBookingId(BOOKING_ID).build();

    public static final GenericResponse<CheckInRecordWebResponse> GENERIC_CHECK_IN__RECORD_WEB_RESPONSE = GenericResponse
            .builder().withStatus(STATUS_OK).withData(Arrays.asList(CHECK_IN__RECORD_WEB_RESPONSE)).withError(null)
            .build();

    private final CheckInRecordWebResponseIntoCheckInRecordListGenerateWebResponseConverter converter = new CheckInRecordWebResponseIntoCheckInRecordListGenerateWebResponseConverter();

    @Test
    public void shouldConvertNull(){
        assertThat(converter.convert(null), is(nullValue()));
    }

    @Test
    public void whenFlightWebResponse_shouldConvert_FlightGenerateWebResponse(){
        assertThat(converter.convert(CHECK_IN__RECORD_WEB_RESPONSE), is(GENERIC_CHECK_IN__RECORD_WEB_RESPONSE));
    }

}
