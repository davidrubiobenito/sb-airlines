package com.drbotro.bk.coreserviceapi.data.response;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.drbotro.bk.common.model.test.AbstractModelBeanTest;
import com.drbotro.bk.coreserviceapi.data.BookingStatus;

public class BookingRecordResponseTest extends AbstractModelBeanTest<BookingRecordResponse>{

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

    private PassengerResponse passengerResponse;
    private List<PassengerResponse> passengersResponseList = new ArrayList<PassengerResponse>();
    private BookingRecordResponse bookingRecordResponse = BookingRecordResponse.builder()
            .withFlightNumber(FLIGHT_NUMBER).withOrigin(ORIGIN).withDestination(DESTINATION).withFlightDate(FLIGHT_DATE)
            .withBookingDate(BOOKING_DATE).withFare(FARE).withStatus(STATUS).build();

    private PassengerResponse passengerResponseOther;
    private List<PassengerResponse> passengersResponseOtherList = new ArrayList<PassengerResponse>();
    private BookingRecordResponse bookingRecordResponseOther = BookingRecordResponse.builder()
            .withFlightNumber(FLIGHT_NUMBER).withOrigin(ORIGIN).withDestination(DESTINATION).withFlightDate(FLIGHT_DATE)
            .withBookingDate(BOOKING_DATE).withFare(FARE).withStatus(STATUS).build();

    @Override
    public void initEntities(){
        passengerResponse = PassengerResponse.builder().withFirstname(FIRST_NAME_P1).withLastName(LAST_NAME_P1)
                .withGender(GENDER_P1).withBookingRecordResponse(bookingRecordResponse).build();
        passengersResponseList.add(passengerResponse);

        passengerResponseOther = PassengerResponse.builder().withFirstname(FIRST_NAME_P2).withLastName(LAST_NAME_P2)
                .withGender(GENDER_P2).withBookingRecordResponse(bookingRecordResponseOther).build();
        passengersResponseOtherList.add(passengerResponseOther);

        entityA1 = bookingRecordResponse.cloneBuilder().withPassengersResponse(passengersResponseList).build();
        entityA2 = bookingRecordResponse.cloneBuilder().withPassengersResponse(passengersResponseList).build();
        entityB = bookingRecordResponseOther.cloneBuilder().withPassengersResponse(passengersResponseOtherList).build();

    }

}
