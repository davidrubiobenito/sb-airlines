package com.drbotro.fa.webapi.converter.response;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

import com.drbotro.fa.coreserviceapi.model.Fare;
import com.drbotro.fa.webapi.response.FareWebResponse;

public class FareIntoFareWebResponseConverterTest{

    private static final String FLIGTH_NUMBER = "BF100";
    private static final String FLIGHT_DATE = "22-JAN-16";
    private static final String FARE = "100";

    private static final Fare FARE_RESPONSE = Fare.builder().withFlightNumber(FLIGTH_NUMBER).withFlightDate(FLIGHT_DATE)
            .withFare(FARE).build();
    private static final FareWebResponse FARE_WEB_RESPONSE = FareWebResponse.builder().withFlightNumber(FLIGTH_NUMBER)
            .withFlightDate(FLIGHT_DATE).withFare(FARE).build();

    private final FareIntoFareWebResponseConverter converter = new FareIntoFareWebResponseConverter();

    @Test
    public void shouldConvertNull(){
        assertThat(converter.convert(null), is(nullValue()));
    }

    @Test
    public void whenFare_shouldConvert_FareWebResponse(){
        assertThat(converter.convert(FARE_RESPONSE), is(FARE_WEB_RESPONSE));
    }

}
