package com.drbotro.fa.coreserviceapi.data.converter.response;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import com.drbotro.fa.coreserviceapi.data.response.FareResponse;
import com.drbotro.fa.repository.model.Fare;

public class FareIntoFareResponseConverterTest{

    private static final Long FARE_ID = 1L;
    private static final String FLIGHT_NUMBER = "BF100";
    private static final String FLIGHT_DATE = "22-JAN-16";
    private static final BigDecimal FARE = BigDecimal.valueOf(100);
    private static final String CURRENCY = "USD";

    private Fare fare;
    private FareResponse fareResponse;

    @InjectMocks
    private FareIntoFareResponseConverter converter;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        fare = Fare.builder().withFareId(FARE_ID).withFlightNumber(FLIGHT_NUMBER).withFlightDate(FLIGHT_DATE)
                .withFare(FARE).withCurrency(CURRENCY).build();
        fareResponse = FareResponse.builder().withFareResponseId(FARE_ID).withFlightNumber(FLIGHT_NUMBER)
                .withFlightDate(FLIGHT_DATE).withFare(FARE).withCurrency(CURRENCY).build();
    }

    @Test
    public void shouldConvertNull(){
        assertThat(converter.convert(null), is(nullValue()));
    }

    @Test
    public void whenFare_shouldConvert_FareResponse(){
        assertThat(converter.convert(fare), is(fareResponse));
    }

}
