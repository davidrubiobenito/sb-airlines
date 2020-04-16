package com.drbotro.bk.coreserviceapi.converter;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.drbotro.bk.coreserviceapi.data.BookingRecordResponse;
import com.drbotro.bk.coreserviceapi.data.BookingStatus;
import com.drbotro.bk.coreserviceapi.data.PassengerResponse;
import com.drbotro.bk.repository.model.BookingRecord;
import com.drbotro.bk.repository.model.Passenger;

public class BookingRecordIntoBookingRecordResponseConverterTest{

    private static final String FIRST_NAME_P1 = "Gean";
    private static final String LAST_NAME_P1 = "Franc";
    private static final String GENDER_P1 = "Male";

    private static final String FLIGHT_NUMBER = "BF100";
    private static final String ORIGIN = "NYC";
    private static final String DESTINATION = "SFO";
    private static final String FLIGHT_DATE = "22-JAN-16";
    private static final Date BOOKING_DATE = new Date();
    private static final String FARE = "101";
    private static final String STATUS = BookingStatus.BOOKING_CONFIRMED;

    private Passenger passengers;
    private Set<Passenger> passengersSet = new HashSet<Passenger>();
    private BookingRecord bookingRecord = BookingRecord.builder().withFlightNumber(FLIGHT_NUMBER).withOrigin(ORIGIN)
            .withDestination(DESTINATION).withFlightDate(FLIGHT_DATE).withBookingDate(BOOKING_DATE).withFare(FARE)
            .withStatus(STATUS).build();

    private PassengerResponse passengersResponse;
    private Set<PassengerResponse> passengerResponseSet = new HashSet<PassengerResponse>();
    private BookingRecordResponse bookingRecordResponse = BookingRecordResponse.builder()
            .withFlightNumber(FLIGHT_NUMBER).withOrigin(ORIGIN).withDestination(DESTINATION).withFlightDate(FLIGHT_DATE)
            .withBookingDate(BOOKING_DATE).withFare(FARE).withStatus(STATUS).build();

    @Mock
    private PassengerIntoPassengerResponseConverter passengerResponseConverter;

    @InjectMocks
    private BookingRecordIntoBookingRecordResponseConverter converter;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);

        passengers = Passenger.builder().withFirstname(FIRST_NAME_P1).withLastName(LAST_NAME_P1).withGender(GENDER_P1)
                .withBookingRecord(bookingRecord).build();
        passengersSet.add(passengers);
        bookingRecord = bookingRecord.cloneBuilder().withPassengers(passengersSet).build();

        passengersResponse = PassengerResponse.builder().withFirstname(FIRST_NAME_P1).withLastName(LAST_NAME_P1)
                .withGender(GENDER_P1).withBookingRecordResponse(bookingRecordResponse).build();
        passengerResponseSet.add(passengersResponse);
        bookingRecordResponse = bookingRecordResponse.cloneBuilder().withPassengersResponse(passengerResponseSet)
                .build();

    }

    @Test
    public void shouldConvertNull(){
        assertThat(converter.convert(null), is(nullValue()));
    }

    @Test
    public void whenBookingRecord_shouldConvert_BookingResponse(){
        when(passengerResponseConverter.convert(passengers)).thenReturn(passengersResponse);
        assertThat(converter.convert(bookingRecord), is(bookingRecordResponse));
    }

}
