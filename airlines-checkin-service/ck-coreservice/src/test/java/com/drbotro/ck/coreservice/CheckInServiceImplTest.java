package com.drbotro.ck.coreservice;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.drbotro.ck.coreserviceapi.converter.CheckInRecordIntoCheckInRecordResponseConverter;
import com.drbotro.ck.coreserviceapi.converter.CheckInRecordRequestIntoCheckInRecordConverter;
import com.drbotro.ck.coreserviceapi.data.CheckInRecordRequest;
import com.drbotro.ck.coreserviceapi.data.CheckInRecordResponse;
import com.drbotro.ck.coreserviceapi.model.CheckInRecord;
import com.drbotro.ck.repository.dao.ICheckInRepository;
import com.fasterxml.jackson.core.JsonProcessingException;

public class CheckInServiceImplTest{

    private static final String LAST_NAME = "Franc";
    private static final String FIRST_NAME = "Gean";
    private static final String SEAT_NUMBER = "28A";
    private static final Date CHECK_IN_TIME = new Date();
    private static final String FLIGHT_NUMBER = "BF101";
    private static final String FLIGHT_DATE = "22-JAN-16";
    private static final Long BOOKING_ID = 1L;

    private CheckInRecordRequest checkInRecordRequest;
    private CheckInRecord checkInRecord;
    private CheckInRecordResponse checkInRecordResponse;

    @Mock
    private CheckInRecord checkInRecordMock;
    @Mock
    private CheckInRecordRequestIntoCheckInRecordConverter checkInRecordConverter;
    @Mock
    private CheckInRecordIntoCheckInRecordResponseConverter checkInRecordResponseConverter;
    @Mock
    private ICheckInRepository iCheckInRepository;

    @InjectMocks
    private CheckInServiceImpl checkInServiceImpl;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);

        checkInRecordRequest = CheckInRecordRequest.builder().withLastName(LAST_NAME).withFirstName(FIRST_NAME)
                .withSeatNumber(SEAT_NUMBER).withCheckInTime(CHECK_IN_TIME).withFlightNumber(FLIGHT_NUMBER)
                .withFlightDate(FLIGHT_DATE).withBookingId(BOOKING_ID).build();

        checkInRecord = CheckInRecord.builder().withLastName(LAST_NAME).withFirstName(FIRST_NAME)
                .withSeatNumber(SEAT_NUMBER).withCheckInTime(CHECK_IN_TIME).withFlightNumber(FLIGHT_NUMBER)
                .withFlightDate(FLIGHT_DATE).withBookingId(BOOKING_ID).build();

        checkInRecordResponse = CheckInRecordResponse.builder().withLastName(LAST_NAME).withFirstName(FIRST_NAME)
                .withSeatNumber(SEAT_NUMBER).withCheckInTime(CHECK_IN_TIME).withFlightNumber(FLIGHT_NUMBER)
                .withFlightDate(FLIGHT_DATE).withBookingId(BOOKING_ID).build();
    }

    @Test
    public void whenSaveCheckRecord_shouldReturn_CheckInRecordSaved() throws JsonProcessingException{
        when(checkInRecordConverter.convert(checkInRecordRequest)).thenReturn(checkInRecord);
        when(iCheckInRepository.save(checkInRecord)).thenReturn(checkInRecordMock);
        when(checkInRecordResponseConverter.convert(checkInRecordMock)).thenReturn(checkInRecordResponse);

        assertThat(checkInServiceImpl.checkin(checkInRecordRequest), is(checkInRecordResponse));
    }

    @Test
    public void whenIdCheckInRecord_shouldReturn_checkInRecordResponse(){
        Optional<CheckInRecord> checkinRecordOptional = Optional.of(checkInRecord);
        when(iCheckInRepository.findById(anyLong())).thenReturn(checkinRecordOptional);
        when(checkInRecordResponseConverter.convert(checkInRecord)).thenReturn(checkInRecordResponse);

        assertThat(checkInServiceImpl.getCheckInRecord(anyLong()), is(checkInRecordResponse));
    }

    @Test
    public void whenIdCheckInRecordNotExist_shouldReturn_Null(){
        Optional<CheckInRecord> checkinRecordOptional = Optional.empty();
        when(iCheckInRepository.findById(anyLong())).thenReturn(checkinRecordOptional);
        when(checkInRecordResponseConverter.convert(null)).thenReturn(null);

        assertThat(checkInServiceImpl.getCheckInRecord(anyLong()), is(nullValue()));
    }

}
