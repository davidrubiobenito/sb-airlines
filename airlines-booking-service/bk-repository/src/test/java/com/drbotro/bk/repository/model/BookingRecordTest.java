package com.drbotro.bk.repository.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.drbotro.bk.common.model.test.AbstractModelBeanTest;

public class BookingRecordTest extends AbstractModelBeanTest<BookingRecord>{

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

    private Passenger passengers;
    private Set<Passenger> passengersSet = new HashSet<Passenger>();
    private BookingRecord bookingRecord = BookingRecord.builder().withFlightNumber(FLIGHT_NUMBER).withOrigin(ORIGIN)
            .withDestination(DESTINATION).withFlightDate(FLIGHT_DATE).withBookingDate(BOOKING_DATE).withFare(FARE)
            .withStatus(STATUS).build();

    private Passenger passengersOther;
    private Set<Passenger> passengersOtherSet = new HashSet<Passenger>();
    private BookingRecord bookingRecordOther = BookingRecord.builder().withFlightNumber(FLIGHT_NUMBER)
            .withOrigin(ORIGIN).withDestination(DESTINATION).withFlightDate(FLIGHT_DATE).withBookingDate(BOOKING_DATE)
            .withFare(FARE).withStatus(STATUS).build();

    @Override
    public void initEntities(){
        passengers = Passenger.builder().withFirstname(FIRST_NAME_P1).withLastName(LAST_NAME_P1).withGender(GENDER_P1)
                .withBookingRecord(bookingRecord).build();
        passengersSet.add(passengers);

        passengersOther = Passenger.builder().withFirstname(FIRST_NAME_P2).withLastName(LAST_NAME_P2)
                .withGender(GENDER_P2).withBookingRecord(bookingRecordOther).build();
        passengersOtherSet.add(passengersOther);

        entityA1 = bookingRecord.cloneBuilder().withPassengers(passengersSet).build();
        entityA2 = bookingRecord.cloneBuilder().withPassengers(passengersSet).build();
        entityB = bookingRecordOther.cloneBuilder().withPassengers(passengersOtherSet).build();

    }

}
