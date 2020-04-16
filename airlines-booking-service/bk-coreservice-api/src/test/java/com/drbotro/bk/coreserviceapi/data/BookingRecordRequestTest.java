package com.drbotro.bk.coreserviceapi.data;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.drbotro.bk.common.model.test.AbstractModelBeanTest;

public class BookingRecordRequestTest extends AbstractModelBeanTest<BookingRecordRequest>{

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

    private PassengerRequest passengersRequest;
    private Set<PassengerRequest> passengersRequestSet = new HashSet<PassengerRequest>();
    private BookingRecordRequest bookingRecordRequest = BookingRecordRequest.builder().withFlightNumber(FLIGHT_NUMBER)
            .withOrigin(ORIGIN).withDestination(DESTINATION).withFlightDate(FLIGHT_DATE).withBookingDate(BOOKING_DATE)
            .withFare(FARE).withStatus(STATUS).build();

    private PassengerRequest passengersRequestOther;
    private Set<PassengerRequest> passengersRequestOtherSet = new HashSet<PassengerRequest>();
    private BookingRecordRequest bookingRecordRequestOther = BookingRecordRequest.builder()
            .withFlightNumber(FLIGHT_NUMBER).withOrigin(ORIGIN).withDestination(DESTINATION).withFlightDate(FLIGHT_DATE)
            .withBookingDate(BOOKING_DATE).withFare(FARE).withStatus(STATUS).build();

    @Override
    public void initEntities(){
        passengersRequest = PassengerRequest.builder().withFirstname(FIRST_NAME_P1).withLastName(LAST_NAME_P1)
                .withGender(GENDER_P1).withBookingRecordRequest(bookingRecordRequest).build();
        passengersRequestSet.add(passengersRequest);

        passengersRequestOther = PassengerRequest.builder().withFirstname(FIRST_NAME_P2).withLastName(LAST_NAME_P2)
                .withGender(GENDER_P2).withBookingRecordRequest(bookingRecordRequestOther).build();
        passengersRequestOtherSet.add(passengersRequestOther);

        entityA1 = bookingRecordRequest.cloneBuilder().withPassengersRequest(passengersRequestSet).build();
        entityA2 = bookingRecordRequest.cloneBuilder().withPassengersRequest(passengersRequestSet).build();
        entityB = bookingRecordRequestOther.cloneBuilder().withPassengersRequest(passengersRequestOtherSet).build();

    }

}
