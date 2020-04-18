package com.drbotro.fa.webapi.converter.request;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import com.drbotro.fa.coreserviceapi.data.request.FareRequest;
import com.drbotro.fa.webapi.request.FareWebRequest;

public class FareWebRequestIntoFareRequestConverterTest{

    private static final String FLIGHT_NUMBER = "BF100";
    private static final String FLIGHT_DATE = "22-JAN-16";
    private static final BigDecimal FARE = BigDecimal.valueOf(100);
    private static final String CURRENCY = "USD";

    private FareWebRequest fareWebRequest;
    private FareRequest fareRequest;

    @InjectMocks
    private FareWebRequestIntoFareRequestConverter converter;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        fareWebRequest = FareWebRequest.builder().withFlightNumber(FLIGHT_NUMBER).withFlightDate(FLIGHT_DATE)
                .withFare(FARE).withCurrency(CURRENCY).build();
        fareRequest = FareRequest.builder().withFlightNumber(FLIGHT_NUMBER).withFlightDate(FLIGHT_DATE).withFare(FARE)
                .withCurrency(CURRENCY).build();
    }

    @Test
    public void shouldConvertNull(){
        assertThat(converter.convert(null), is(nullValue()));
    }

    @Test
    public void whenFareWebRequest_shouldConvert_FareRequest(){
        assertThat(converter.convert(fareWebRequest), is(fareRequest));
    }

}
