package com.drbotro.fa.webrs.controller;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.drbotro.fa.coreserviceapi.data.request.FareRequest;
import com.drbotro.fa.coreserviceapi.data.response.FareResponse;
import com.drbotro.fa.coreserviceapi.inter.IFareService;
import com.drbotro.fa.webapi.converter.request.FareWebRequestIntoFareRequestConverter;
import com.drbotro.fa.webapi.converter.response.FareResponseIntoFareWebResponseConverter;
import com.drbotro.fa.webapi.converter.response.generate.FareListWebResponseIntoGenericResponseFareListWebResponseConverter;
import com.drbotro.fa.webapi.request.FareWebRequest;
import com.drbotro.fa.webapi.response.FareWebResponse;
import com.drbotro.fa.webapi.response.GenericResponseFareWebResponse;
import com.drbotro.fa.webapi.version.Version;
import com.drbotro.fa.webrs.exception.CustomExceptionHandler;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = FareController.class)
@SpringBootTest
@Import(IntegrationTestConfiguration.class)
public class FareControllerIntegrationTest{

    private static final String API = "/api";
    private static final String FARES = "/fares";
    private static final String GET = "/get";

    private static final String STATUS_OK = "OK";
    private static final String STATUS_KO = "KO";
    private static final Long FARE_ID = 1L;
    private static final String FLIGHT_NUMBER = "BF100";
    private static final String FLIGHT_DATE = "22-JAN-16";
    private static final BigDecimal FARE = BigDecimal.valueOf(100);
    private static final String CURRENCY = "USD";

    private static final FareWebRequest FARE_WEB_REQUEST = FareWebRequest.builder().withFlightNumber(FLIGHT_NUMBER)
            .withFlightDate(FLIGHT_DATE).withFare(FARE).withCurrency(CURRENCY).build();

    private static final FareRequest FARE_REQUEST = FareRequest.builder().withFlightNumber(FLIGHT_NUMBER)
            .withFlightDate(FLIGHT_DATE).withFare(FARE).withCurrency(CURRENCY).build();

    private static final FareResponse FARE_RESPONSE = FareResponse.builder().withFareResponseId(FARE_ID)
            .withFlightNumber(FLIGHT_NUMBER).withFlightDate(FLIGHT_DATE).withFare(FARE).withCurrency(CURRENCY).build();

    private static final FareWebResponse FARE_WEB_RESPONSE = FareWebResponse.builder().withId(FARE_ID)
            .withFlightNumber(FLIGHT_NUMBER).withFlightDate(FLIGHT_DATE).withFare(FARE).withCurrency(CURRENCY).build();

    private static final GenericResponseFareWebResponse GENERIC_WEB_RESPONSE_OK = GenericResponseFareWebResponse
            .builder().withStatus(STATUS_OK).withData(Arrays.asList(FARE_WEB_RESPONSE)).withError(null).build();

    private MockMvc mockMvc;

    @Autowired
    private IFareService iFareService;

    @Autowired
    private FareWebRequestIntoFareRequestConverter fareWebRequestIntoFareRequestConverter;
    @Autowired
    private FareResponseIntoFareWebResponseConverter fareWebResponseConverter;
    @Autowired
    private FareListWebResponseIntoGenericResponseFareListWebResponseConverter genericResponseFareListWebResponseConverter;

    @Autowired
    private FareController fareController;

    @Before
    public void setup(){
        this.mockMvc = MockMvcBuilders.standaloneSetup(fareController).setControllerAdvice(CustomExceptionHandler.class)
                .build();
    }

    @Test
    public void whenFindFareWithFlightNumberAnfFlightDate_shouldReturn_OK() throws Exception{
        final String url = API + Version.V1 + FARES + GET;

        when(iFareService.findFareByFlightNumberAndFlightDate(FLIGHT_NUMBER, FLIGHT_DATE)).thenReturn(FARE_RESPONSE);
        when(fareWebResponseConverter.convert(FARE_RESPONSE)).thenReturn(FARE_WEB_RESPONSE);
        when(genericResponseFareListWebResponseConverter.convert(Arrays.asList(FARE_WEB_RESPONSE)))
                .thenReturn(GENERIC_WEB_RESPONSE_OK);

        final ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get(url)
                .contentType(MediaType.APPLICATION_JSON_VALUE).param("flightNumber", FLIGHT_NUMBER)
                .param("flightDate", FLIGHT_DATE).accept(MediaType.APPLICATION_JSON_VALUE));

        //Then
        result.andExpect(status().isOk());
        result.andExpect(jsonPath("$.status").value("OK"));
        result.andExpect(jsonPath("$.data", hasSize(1)));
        result.andDo(print());
    }

}
