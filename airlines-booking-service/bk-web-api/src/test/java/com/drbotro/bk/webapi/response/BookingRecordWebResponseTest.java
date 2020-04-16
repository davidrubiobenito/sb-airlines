package com.drbotro.bk.webapi.response;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.drbotro.bk.common.model.test.AbstractModelBeanTest;
import com.drbotro.bk.coreserviceapi.data.BookingStatus;

public class BookingRecordWebResponseTest extends AbstractModelBeanTest<BookingRecordWebResponse>{

    private static final String FIRST_NAME_P1 = "Gean";
    private static final String LAST_NAME_P1 = "Franc";
    private static final String GENDER_P1 = "Male";
    private static final String FIRST_NAME_P2 = "Redi";
    private static final String LAST_NAME_P2 = "Ivan";
    private static final String GENDER_P2 = "Female";

    private static final String FLIGHT_NUMBER = "BF100";
    private static final String ORIGIN = "NYC";
    private static final String DESTINATION = "SFO";
    private static final String FLIGHT_DATE = "22-JAN-16";
    private static final Date BOOKING_DATE = new Date();
    private static final String FARE = "101";
    private static final String STATUS = BookingStatus.BOOKING_CONFIRMED;

    private PassengerWebResponse passengerWebResponse;
    private Set<PassengerWebResponse> passengersWebResponseSet = new HashSet<PassengerWebResponse>();
    private BookingRecordWebResponse bookingRecordWebResponse = BookingRecordWebResponse.builder()
            .withFlightNumber(FLIGHT_NUMBER).withOrigin(ORIGIN).withDestination(DESTINATION).withFlightDate(FLIGHT_DATE)
            .withBookingDate(BOOKING_DATE).withFare(FARE).withStatus(STATUS).build();

    private PassengerWebResponse passengerWebResponseOther;
    private Set<PassengerWebResponse> passengersWebResponseOtherSet = new HashSet<PassengerWebResponse>();
    private BookingRecordWebResponse bookingRecordWebResponseOther = BookingRecordWebResponse.builder()
            .withFlightNumber(FLIGHT_NUMBER).withOrigin(ORIGIN).withDestination(DESTINATION).withFlightDate(FLIGHT_DATE)
            .withBookingDate(BOOKING_DATE).withFare(FARE).withStatus(STATUS).build();

    @Override
    public void initEntities(){
        passengerWebResponse = PassengerWebResponse.builder().withFirstname(FIRST_NAME_P1).withLastName(LAST_NAME_P1)
                .withGender(GENDER_P1).withBookingRecordWebResponse(bookingRecordWebResponse).build();
        passengersWebResponseSet.add(passengerWebResponse);

        passengerWebResponseOther = PassengerWebResponse.builder().withFirstname(FIRST_NAME_P2)
                .withLastName(LAST_NAME_P2).withGender(GENDER_P2)
                .withBookingRecordWebResponse(bookingRecordWebResponseOther).build();
        passengersWebResponseOtherSet.add(passengerWebResponseOther);

        entityA1 = bookingRecordWebResponse.cloneBuilder().withPassengersWebResponse(passengersWebResponseSet).build();
        entityA2 = bookingRecordWebResponse.cloneBuilder().withPassengersWebResponse(passengersWebResponseSet).build();
        entityB = bookingRecordWebResponseOther.cloneBuilder().withPassengersWebResponse(passengersWebResponseOtherSet)
                .build();

    }

}
