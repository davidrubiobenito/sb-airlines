package com.drbotro.bk.webapi.converter.response.generate;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import com.drbotro.bk.coreserviceapi.data.BookingStatus;
import com.drbotro.bk.webapi.response.BookingRecordWebResponse;
import com.drbotro.bk.webapi.response.GenericResponsePassengerWebResponse;
import com.drbotro.bk.webapi.response.PassengerWebResponse;

public class PassengerWebResponseIntoGerericResponsePassengerListWebResponseConverterTest{

    private static final String STATUS_OK = "OK";
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

    private PassengerWebResponse passengerWebResponse;
    private List<PassengerWebResponse> passengersWebResponseList = new ArrayList<PassengerWebResponse>();
    private BookingRecordWebResponse bookingRecordWebResponse = BookingRecordWebResponse.builder()
            .withFlightNumber(FLIGHT_NUMBER).withOrigin(ORIGIN).withDestination(DESTINATION).withFlightDate(FLIGHT_DATE)
            .withBookingDate(BOOKING_DATE).withFare(FARE).withStatus(STATUS).build();

    @InjectMocks
    private PassengerWebResponseIntoGerericResponsePassengerListWebResponseConverter converter;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);

        passengerWebResponse = PassengerWebResponse.builder().withFirstname(FIRST_NAME_P1).withLastName(LAST_NAME_P1)
                .withGender(GENDER_P1).withBookingRecordWebResponse(bookingRecordWebResponse).build();
    }

    @Test
    public void shouldConvertNull(){
        assertThat(converter.convert(null), is(nullValue()));
    }

    @Test
    public void whenPassengerWebResponse_shouldConvert_GenericResponsePassengerWebResponse(){
        GenericResponsePassengerWebResponse genericBookingRecordWebResponse = GenericResponsePassengerWebResponse
                .builder().withStatus(STATUS_OK).withData(Arrays.asList(passengerWebResponse)).withError(null).build();

        assertThat(converter.convert(passengerWebResponse), is(genericBookingRecordWebResponse));
    }

}
