package com.drbotro.ss.webapi.converter.response.generate;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.Arrays;

import org.junit.Test;

import com.drbotro.ss.common.response.GenericResponse;
import com.drbotro.ss.coreserviceapi.model.Fares;
import com.drbotro.ss.coreserviceapi.model.Inventory;
import com.drbotro.ss.webapi.response.FlightWebResponse;

public class FlightListWebResponseIntoFlightListGenerateWebResponseConverterTest{

    private static final String FLIGTH_NUMBER = "BF100";
    private static final String ORIGIN = "SEA";
    private static final String DESTINATION = "SFO";
    private static final String FLIGHT_DATE = "22-JAN-16";
    public static final Fares FARES = Fares.builder().withFare("100").withCurrency("USD").build();
    public static final Inventory INVENTORY = Inventory.builder().withCount(100).build();
    private static final String STATUS_OK = "OK";

    public static final FlightWebResponse FLIGHT_WEB_RESPONSE = FlightWebResponse.builder()
            .withFligthNumber(FLIGTH_NUMBER).withOrigin(ORIGIN).withDestination(DESTINATION).withFlightDate(FLIGHT_DATE)
            .withFares(FARES).withInventory(INVENTORY).build();

    public static final GenericResponse<FlightWebResponse> GENERIC_FLIGHT_WEB_RESPONSE = GenericResponse.builder()
            .withStatus(STATUS_OK).withData(Arrays.asList(FLIGHT_WEB_RESPONSE)).withError(null).build();

    private final FlightListWebResponseIntoFlightListGenerateWebResponseConverter converter = new FlightListWebResponseIntoFlightListGenerateWebResponseConverter();

    @Test
    public void shouldConvertNull(){
        assertThat(converter.convert(null), is(nullValue()));
    }

    @Test
    public void whenListFlightWebResponse_shouldConvert_ListFlightGenerateWebResponse(){
        assertThat(converter.convert(Arrays.asList(FLIGHT_WEB_RESPONSE)), is(GENERIC_FLIGHT_WEB_RESPONSE));
    }

}
