package com.drbotro.fa.webapi.converter.response.generate;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.Arrays;

import org.junit.Test;

import com.drbotro.fa.webapi.response.FareWebResponse;
import com.drbotro.fa.webapi.response.GenericResponseFareWebResponse;

public class FareWebResponseIntoGenericResponseFareListWebResponseConverterTest{

    private static final String STATUS_OK = "OK";
    private static final String FLIGTH_NUMBER = "BF100";
    private static final String FLIGHT_DATE = "22-JAN-16";
    private static final String FARE = "100";

    private static final FareWebResponse FARE_WEB_RESPONSE = FareWebResponse.builder().withFlightNumber(FLIGTH_NUMBER)
            .withFlightDate(FLIGHT_DATE).withFare(FARE).build();

    public static final GenericResponseFareWebResponse GENERIC_FARE_WEB_RESPONSE = GenericResponseFareWebResponse
            .builder().withStatus(STATUS_OK).withData(Arrays.asList(FARE_WEB_RESPONSE)).withError(null).build();

    private final FareWebResponseIntoGenericResponseFareListWebResponseConverter converter = new FareWebResponseIntoGenericResponseFareListWebResponseConverter();

    @Test
    public void shouldConvertNull(){
        assertThat(converter.convert(null), is(nullValue()));
    }

    @Test
    public void whenFlightWebResponse_shouldConvert_FlightGenerateWebResponse(){
        assertThat(converter.convert(FARE_WEB_RESPONSE), is(GENERIC_FARE_WEB_RESPONSE));
    }

}
