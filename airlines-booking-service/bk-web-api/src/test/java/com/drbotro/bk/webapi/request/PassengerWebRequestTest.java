package com.drbotro.bk.webapi.request;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.drbotro.bk.common.model.test.AbstractModelBeanTest;
import com.drbotro.bk.coreserviceapi.data.BookingStatus;

public class PassengerWebRequestTest extends AbstractModelBeanTest<PassengerWebRequest>{

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

    private PassengerWebRequest passengersWebRequest;
    private List<PassengerWebRequest> passengersWebRequestList = new ArrayList<PassengerWebRequest>();
    private BookingRecordWebRequest bookingRecordWebRequest = BookingRecordWebRequest.builder()
            .withFlightNumber(FLIGHT_NUMBER).withOrigin(ORIGIN).withDestination(DESTINATION).withFlightDate(FLIGHT_DATE)
            .withBookingDate(BOOKING_DATE).withFare(FARE).withStatus(STATUS).build();

    private PassengerWebRequest passengersWebRequestOther;
    private List<PassengerWebRequest> passengersWebRequestOtherList = new ArrayList<PassengerWebRequest>();
    private BookingRecordWebRequest bookingRecordWebRequestOther = BookingRecordWebRequest.builder()
            .withFlightNumber(FLIGHT_NUMBER).withOrigin(ORIGIN).withDestination(DESTINATION).withFlightDate(FLIGHT_DATE)
            .withBookingDate(BOOKING_DATE).withFare(FARE).withStatus(STATUS).build();

    @Override
    public void initEntities(){
        passengersWebRequest = PassengerWebRequest.builder().withFirstname(FIRST_NAME_P1).withLastName(LAST_NAME_P1)
                .withGender(GENDER_P1).withBookingRecordWebRequest(bookingRecordWebRequest).build();
        passengersWebRequestList.add(passengersWebRequest);
        //bookingRecordRequest = bookingRecordRequest.cloneBuilder().withPassengersRequest(passengersRequestSet).build();

        passengersWebRequestOther = PassengerWebRequest.builder().withFirstname(FIRST_NAME_P2)
                .withLastName(LAST_NAME_P2).withGender(GENDER_P2)
                .withBookingRecordWebRequest(bookingRecordWebRequestOther).build();
        passengersWebRequestOtherList.add(passengersWebRequestOther);
        //bookingRecordRequestOther = bookingRecordRequestOther.cloneBuilder().withPassengersRequest(passengersRequestOtherSet).build();

        entityA1 = PassengerWebRequest.builder().withFirstname(FIRST_NAME_P1).withLastName(LAST_NAME_P1)
                .withGender(GENDER_P1).withBookingRecordWebRequest(bookingRecordWebRequest).build();
        entityA2 = PassengerWebRequest.builder().withFirstname(FIRST_NAME_P1).withLastName(LAST_NAME_P1)
                .withGender(GENDER_P1).withBookingRecordWebRequest(bookingRecordWebRequest).build();
        entityB = PassengerWebRequest.builder().withFirstname(FIRST_NAME_P2).withLastName(LAST_NAME_P2)
                .withGender(GENDER_P2).withBookingRecordWebRequest(bookingRecordWebRequestOther).build();

    }

}
