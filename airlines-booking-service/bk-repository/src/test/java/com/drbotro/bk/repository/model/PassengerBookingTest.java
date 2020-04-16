package com.drbotro.bk.repository.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.drbotro.bk.common.model.test.AbstractModelBeanTest;

public class PassengerBookingTest extends AbstractModelBeanTest<PassengerBooking>{

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
    private static final String STATUS = "BOOKING_CONFIRMED";

    private PassengerBooking passengers;
    private List<PassengerBooking> passengersList = new ArrayList<PassengerBooking>();
    private BookingRecordBooking bookingRecord = BookingRecordBooking.builder().withFlightNumber(FLIGHT_NUMBER)
            .withOrigin(ORIGIN).withDestination(DESTINATION).withFlightDate(FLIGHT_DATE).withBookingDate(BOOKING_DATE)
            .withFare(FARE).withStatus(STATUS).build();

    private PassengerBooking passengersOther;
    private List<PassengerBooking> passengersOtherList = new ArrayList<PassengerBooking>();
    private BookingRecordBooking bookingRecordOther = BookingRecordBooking.builder().withFlightNumber(FLIGHT_NUMBER)
            .withOrigin(ORIGIN).withDestination(DESTINATION).withFlightDate(FLIGHT_DATE).withBookingDate(BOOKING_DATE)
            .withFare(FARE).withStatus(STATUS).build();

    @Override
    public void initEntities(){
        passengers = PassengerBooking.builder().withFirstname(FIRST_NAME_P1).withLastName(LAST_NAME_P1)
                .withGender(GENDER_P1).withBookingRecord(bookingRecord).build();
        passengersList.add(passengers);
        //bookingRecord = bookingRecord.cloneBuilder().withPassengers(passengersSet).build();

        passengersOther = PassengerBooking.builder().withFirstname(FIRST_NAME_P2).withLastName(LAST_NAME_P2)
                .withGender(GENDER_P2).withBookingRecord(bookingRecordOther).build();
        passengersOtherList.add(passengersOther);
        //bookingRecordOther = bookingRecordOther.cloneBuilder().withPassengers(passengersOtherSet).build();

        entityA1 = PassengerBooking.builder().withFirstname(FIRST_NAME_P1).withLastName(LAST_NAME_P1)
                .withGender(GENDER_P1).withBookingRecord(bookingRecord).build();
        entityA2 = PassengerBooking.builder().withFirstname(FIRST_NAME_P1).withLastName(LAST_NAME_P1)
                .withGender(GENDER_P1).withBookingRecord(bookingRecord).build();
        entityB = PassengerBooking.builder().withFirstname(FIRST_NAME_P2).withLastName(LAST_NAME_P2)
                .withGender(GENDER_P2).withBookingRecord(bookingRecordOther).build();

    }

}
