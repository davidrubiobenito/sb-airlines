package com.drbotro.bk.webapi.converter.response;

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
import com.drbotro.bk.webapi.response.BookingRecordWebResponse;
import com.drbotro.bk.webapi.response.PassengerWebResponse;

public class BookingRecordResponseIntoBookingRecordWebResponseConverterTest{

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

    private PassengerResponse passengerResponse;
    private Set<PassengerResponse> passengersResponseSet = new HashSet<PassengerResponse>();
    private BookingRecordResponse bookingRecordResponse = BookingRecordResponse.builder()
            .withFlightNumber(FLIGHT_NUMBER).withOrigin(ORIGIN).withDestination(DESTINATION).withFlightDate(FLIGHT_DATE)
            .withBookingDate(BOOKING_DATE).withFare(FARE).withStatus(STATUS).build();

    private PassengerWebResponse passengerWebResponse;
    private Set<PassengerWebResponse> passengersWebResponseSet = new HashSet<PassengerWebResponse>();
    private BookingRecordWebResponse bookingRecordWebResponse = BookingRecordWebResponse.builder()
            .withFlightNumber(FLIGHT_NUMBER).withOrigin(ORIGIN).withDestination(DESTINATION).withFlightDate(FLIGHT_DATE)
            .withBookingDate(BOOKING_DATE).withFare(FARE).withStatus(STATUS).build();

    @Mock
    private PassengerResponseIntoPassengerWebResponseConverter passengerWebResponseConverter;

    @InjectMocks
    private BookingRecordResponseIntoBookingRecordWebResponseConverter converter;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);

        passengerResponse = PassengerResponse.builder().withFirstname(FIRST_NAME_P1).withLastName(LAST_NAME_P1)
                .withGender(GENDER_P1).withBookingRecordResponse(bookingRecordResponse).build();
        passengersResponseSet.add(passengerResponse);
        bookingRecordResponse = bookingRecordResponse.cloneBuilder().withPassengersResponse(passengersResponseSet)
                .build();

        passengerWebResponse = PassengerWebResponse.builder().withFirstname(FIRST_NAME_P1).withLastName(LAST_NAME_P1)
                .withGender(GENDER_P1).withBookingRecordWebResponse(bookingRecordWebResponse).build();
        passengersWebResponseSet.add(passengerWebResponse);
        bookingRecordWebResponse = bookingRecordWebResponse.cloneBuilder()
                .withPassengersWebResponse(passengersWebResponseSet).build();
    }

    @Test
    public void shouldConvertNull(){
        assertThat(converter.convert(null), is(nullValue()));
    }

    @Test
    public void whenBookingRecordResponse_shouldConvert_BookingWebResponse(){
        when(passengerWebResponseConverter.convert(passengerResponse)).thenReturn(passengerWebResponse);
        assertThat(converter.convert(bookingRecordResponse), is(bookingRecordWebResponse));
    }

}
