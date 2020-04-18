package com.drbotro.bk.coreservice;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import com.drbotro.bk.common.exception.ApiError;
import com.drbotro.bk.coreserviceapi.converter.BookingRecordIntoBookingRecordResponseConverter;
import com.drbotro.bk.coreserviceapi.converter.BookingRecordRequestIntoBookingRecordConverter;
import com.drbotro.bk.coreserviceapi.data.BookingStatus;
import com.drbotro.bk.coreserviceapi.data.request.BookingRecordRequest;
import com.drbotro.bk.coreserviceapi.data.request.PassengerRequest;
import com.drbotro.bk.coreserviceapi.data.response.BookingRecordResponse;
import com.drbotro.bk.coreserviceapi.data.response.FareWebResponse;
import com.drbotro.bk.coreserviceapi.data.response.PassengerResponse;
import com.drbotro.bk.repository.dao.IBookingRepository;
import com.drbotro.bk.repository.dao.IInventoryRepository;
import com.drbotro.bk.repository.model.BookingRecordBooking;
import com.drbotro.bk.repository.model.InventoryBooking;
import com.drbotro.bk.repository.model.PassengerBooking;

public class BookingServiceImplTest{

    private static final String FARE_URL = "http://localhost:8080/fares";

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
    private static final int AVAILABLE = 100;

    private FareWebResponse fareWebResponse = FareWebResponse.builder().withFlightNumber(FLIGHT_NUMBER)
            .withFlightDate(FLIGHT_DATE).withFare(FARE).build();

    private InventoryBooking inventory = InventoryBooking.builder().withFlightNumber(FLIGHT_NUMBER)
            .withFlightDate(FLIGHT_DATE).withAvailable(AVAILABLE).build();

    private PassengerRequest passengersRequest;
    private List<PassengerRequest> passengersRequestList = new ArrayList<PassengerRequest>();
    private BookingRecordRequest bookingRecordRequest = BookingRecordRequest.builder().withFlightNumber(FLIGHT_NUMBER)
            .withOrigin(ORIGIN).withDestination(DESTINATION).withFlightDate(FLIGHT_DATE).withBookingDate(BOOKING_DATE)
            .withFare(FARE).withStatus(STATUS).build();

    private PassengerBooking passengers;
    private List<PassengerBooking> passengersList = new ArrayList<PassengerBooking>();
    private BookingRecordBooking bookingRecord = BookingRecordBooking.builder().withFlightNumber(FLIGHT_NUMBER)
            .withOrigin(ORIGIN).withDestination(DESTINATION).withFlightDate(FLIGHT_DATE).withBookingDate(BOOKING_DATE)
            .withFare(FARE).withStatus(STATUS).build();

    private PassengerResponse passengersResponse;
    private List<PassengerResponse> passengersResponseList = new ArrayList<PassengerResponse>();
    private BookingRecordResponse bookingRecordResponse = BookingRecordResponse.builder()
            .withFlightNumber(FLIGHT_NUMBER).withOrigin(ORIGIN).withDestination(DESTINATION).withFlightDate(FLIGHT_DATE)
            .withBookingDate(BOOKING_DATE).withFare(FARE).withStatus(STATUS).build();

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private BookingRecordBooking bookingRecordMock;

    @Mock
    private IBookingRepository iBookingRepository;
    @Mock
    private IInventoryRepository iInventoryRepository;

    @Mock
    private BookingRecordRequestIntoBookingRecordConverter bookingRecordConverter;
    @Mock
    private BookingRecordIntoBookingRecordResponseConverter bookingRecordResponseConverter;

    @InjectMocks
    private BookingServiceImpl bookingServiceImpl;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);

        passengersRequest = PassengerRequest.builder().withFirstname(FIRST_NAME_P1).withLastName(LAST_NAME_P1)
                .withGender(GENDER_P1).withBookingRecordRequest(bookingRecordRequest).build();
        passengersRequestList.add(passengersRequest);
        bookingRecordRequest = bookingRecordRequest.cloneBuilder().withPassengersRequest(passengersRequestList).build();

        passengers = PassengerBooking.builder().withFirstname(FIRST_NAME_P1).withLastName(LAST_NAME_P1)
                .withGender(GENDER_P1).withBookingRecord(bookingRecord).build();
        passengersList.add(passengers);
        bookingRecord = bookingRecord.cloneBuilder().withPassengers(passengersList).build();

        passengersResponse = PassengerResponse.builder().withFirstname(FIRST_NAME_P1).withLastName(LAST_NAME_P1)
                .withGender(GENDER_P1).withBookingRecordResponse(bookingRecordResponse).build();
        passengersResponseList.add(passengersResponse);
        bookingRecordResponse = bookingRecordResponse.cloneBuilder().withPassengersResponse(passengersResponseList)
                .build();
    }

    @Test
    public void whenBookBookingRecordRequest_shouldReturn_BookingRecordResponseSaved() throws ApiError{
        when(restTemplate.getForObject(FARE_URL + "/get?flightNumber=" + bookingRecordRequest.getFlightNumber()
                + "&flightDate=" + bookingRecordRequest.getFlightDate(), FareWebResponse.class))
                        .thenReturn(fareWebResponse);

        Optional<InventoryBooking> inventoryOptional = Optional.of(inventory);
        when(iInventoryRepository.findByFlightNumberAndFlightDate(bookingRecordRequest.getFlightNumber(),
                bookingRecordRequest.getFlightDate())).thenReturn(inventoryOptional);

        InventoryBooking inventoryAux = inventory.cloneBuilder()
                .withAvailable(AVAILABLE - bookingRecordRequest.getPassengersRequest().size()).build();
        when(iInventoryRepository.saveAndFlush(inventoryAux)).thenReturn(inventoryAux);

        when(bookingRecordConverter.convert(bookingRecordRequest)).thenReturn(bookingRecord);
        when(iBookingRepository.save(bookingRecord)).thenReturn(bookingRecordMock);
        when(bookingRecordResponseConverter.convert(bookingRecordMock)).thenReturn(bookingRecordResponse);

        assertThat(bookingServiceImpl.saveBookingRecord(bookingRecordRequest), is(bookingRecordResponse));
    }

    @Test
    public void whenIdBookingRecord_shouldReturn_bookingRecordResponse(){
        Optional<BookingRecordBooking> bookingRecordOptional = Optional.of(bookingRecord);
        when(iBookingRepository.findById(anyLong())).thenReturn(bookingRecordOptional);
        when(bookingRecordResponseConverter.convert(bookingRecord)).thenReturn(bookingRecordResponse);

        assertThat(bookingServiceImpl.getBookingRecordById(anyLong()), is(bookingRecordResponse));
    }

    @Test
    public void whenIdBoogingRecordNotExist_shouldReturn_Null(){
        Optional<BookingRecordBooking> bookingRecordOptional = Optional.empty();
        when(iBookingRepository.findById(anyLong())).thenReturn(bookingRecordOptional);
        when(bookingRecordResponseConverter.convert(null)).thenReturn(null);

        assertThat(bookingServiceImpl.getBookingRecordById(anyLong()), is(nullValue()));
    }

    @Test
    public void whenStatusAndIdBookingRecord_shouldReturn_updateBookingRecordResponse(){
        BookingRecordResponse bookingRecordResponseUpdated = bookingRecordResponse.cloneBuilder()
                .withStatus(BookingStatus.CHECKED_IN).build();
        Optional<BookingRecordBooking> bookingRecordOptional = Optional.of(bookingRecord);
        when(iBookingRepository.findById(anyLong())).thenReturn(bookingRecordOptional);
        bookingRecord = bookingRecord.cloneBuilder().withStatus(BookingStatus.CHECKED_IN).build();
        when(bookingRecordResponseConverter.convert(bookingRecord)).thenReturn(bookingRecordResponseUpdated);

        assertThat(bookingServiceImpl.updateStatusBookingRecord(BookingStatus.CHECKED_IN, anyLong()),
                is(bookingRecordResponseUpdated));
    }

    @Test
    public void whenStatudAndIdBoogingRecordNotExist_shouldReturn_Null(){
        Optional<BookingRecordBooking> bookingRecordOptional = Optional.empty();
        when(iBookingRepository.findById(anyLong())).thenReturn(bookingRecordOptional);
        when(bookingRecordResponseConverter.convert(null)).thenReturn(null);

        assertThat(bookingServiceImpl.updateStatusBookingRecord(BookingStatus.CHECKED_IN, anyLong()), is(nullValue()));
    }

}
