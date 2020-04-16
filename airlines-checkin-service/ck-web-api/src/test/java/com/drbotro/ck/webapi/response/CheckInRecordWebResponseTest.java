package com.drbotro.ck.webapi.response;

import java.util.Date;

import com.drbotro.ck.common.model.test.AbstractModelBeanTest;

public class CheckInRecordWebResponseTest extends AbstractModelBeanTest<CheckInRecordWebResponse>{

    private static final String LAST_NAME = "Franc";
    private static final String FIRST_NAME = "Gean";
    private static final String SEAT_NUMBER = "28A";
    private static final Date CHECK_IN_TIME = new Date();
    private static final String FLIGHT_NUMBER = "BF101";
    private static final String FLIGHT_DATE = "22-JAN-16";
    private static final Long BOOKING_ID = 1L;
    private static final Long BOOKING_ID_OTHER = 2L;

    @Override
    public void initEntities(){
        entityA1 = CheckInRecordWebResponse.builder().withLastName(LAST_NAME).withFirstName(FIRST_NAME)
                .withSeatNumber(SEAT_NUMBER).withCheckInTime(CHECK_IN_TIME).withFlightNumber(FLIGHT_NUMBER)
                .withFlightDate(FLIGHT_DATE).withBookingId(BOOKING_ID).build();
        entityA2 = CheckInRecordWebResponse.builder().withLastName(LAST_NAME).withFirstName(FIRST_NAME)
                .withSeatNumber(SEAT_NUMBER).withCheckInTime(CHECK_IN_TIME).withFlightNumber(FLIGHT_NUMBER)
                .withFlightDate(FLIGHT_DATE).withBookingId(BOOKING_ID).build();
        entityB = CheckInRecordWebResponse.builder().withLastName(LAST_NAME).withFirstName(FIRST_NAME)
                .withSeatNumber(SEAT_NUMBER).withCheckInTime(CHECK_IN_TIME).withFlightNumber(FLIGHT_NUMBER)
                .withFlightDate(FLIGHT_DATE).withBookingId(BOOKING_ID_OTHER).build();

    }

}
