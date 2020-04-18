package com.drbotro.fa.coreservice;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.drbotro.fa.common.exception.EntityConflictException;
import com.drbotro.fa.coreserviceapi.data.converter.request.FareRequestIntoFareConverter;
import com.drbotro.fa.coreserviceapi.data.converter.response.FareIntoFareResponseConverter;
import com.drbotro.fa.coreserviceapi.data.request.FareRequest;
import com.drbotro.fa.coreserviceapi.data.response.FareResponse;
import com.drbotro.fa.repository.dao.IFareRepository;
import com.drbotro.fa.repository.model.Fare;

public class FareServiceImplTest{

    private static final Long FARE_ID = 1L;
    private static final String FLIGHT_NUMBER = "BF100";
    private static final String FLIGHT_DATE = "22-JAN-16";
    private static final BigDecimal FARE = BigDecimal.valueOf(100);
    private static final String CURRENCY = "USD";

    private FareRequest fareRequest;
    private Fare fare;
    private FareResponse fareResponse;

    @Mock
    private IFareRepository iFareRespository;

    @Mock
    private FareRequestIntoFareConverter fareConverter;
    @Mock
    private FareIntoFareResponseConverter fareResponseConverter;

    @InjectMocks
    private FareServiceImpl fareServiceImpl;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        fareRequest = FareRequest.builder().withFlightNumber(FLIGHT_NUMBER).withFlightDate(FLIGHT_DATE).withFare(FARE)
                .withCurrency(CURRENCY).build();
        fare = Fare.builder().withFareId(FARE_ID).withFlightNumber(FLIGHT_NUMBER).withFlightDate(FLIGHT_DATE)
                .withFare(FARE).withCurrency(CURRENCY).build();
        fareResponse = FareResponse.builder().withFareResponseId(FARE_ID).withFlightNumber(FLIGHT_NUMBER)
                .withFlightDate(FLIGHT_DATE).withFare(FARE).withCurrency(CURRENCY).build();
    }

    @Test
    public void whenFlightNumberAndFlightDate_shouldReturn_FareResponse(){
        Optional<Fare> fareOptional = Optional.of(fare);
        when(iFareRespository.getFareByFlightNumberAndFlightDate(anyString(), anyString())).thenReturn(fareOptional);
        when(fareResponseConverter.convert(fare)).thenReturn(fareResponse);

        assertThat(fareServiceImpl.getFare(FLIGHT_NUMBER, FLIGHT_DATE), is(fareResponse));
    }

    @Test
    public void whenFlightNumberAndFlightDateNotExist_shouldReturn_Null(){
        Optional<Fare> fareOptional = Optional.empty();
        when(iFareRespository.getFareByFlightNumberAndFlightDate(anyString(), anyString())).thenReturn(fareOptional);
        when(fareResponseConverter.convert(null)).thenReturn(null);

        assertThat(fareServiceImpl.getFare(FLIGHT_NUMBER, FLIGHT_DATE), is(nullValue()));
    }

    @Test
    public void whenGetAllFare_shouldReturn_ListFareResponse(){
        when(iFareRespository.findAll()).thenReturn(Arrays.asList(fare));
        when(fareResponseConverter.convert(fare)).thenReturn(fareResponse);

        assertThat(fareServiceImpl.getAllFare(), is(Arrays.asList(fareResponse)));
    }

    @Test
    public void whenSaveFareWithFlightNumberAndFlightDate_shouldReturn_FareResponse() throws EntityConflictException{
        Optional<Fare> fareOptional = Optional.empty();
        when(iFareRespository.getFareByFlightNumberAndFlightDate(anyString(), anyString())).thenReturn(fareOptional);

        when(fareConverter.convert(fareRequest)).thenReturn(fare);
        when(iFareRespository.save(fare)).thenReturn(fare);
        when(fareResponseConverter.convert(fare)).thenReturn(fareResponse);

        assertThat(fareServiceImpl.createFare(fareRequest), is(fareResponse));
    }

    @Test
    public void whenSaveFareWithFlightNumberAndFlightDateExist_shouldReturn_ErrorExceptionThrown(){
        assertThrows(EntityConflictException.class, () -> Optional.ofNullable(fareServiceImpl.createFare(fareRequest))
                .orElseThrow(() -> new EntityConflictException("El registro existe en BBDD")));
    }

}
