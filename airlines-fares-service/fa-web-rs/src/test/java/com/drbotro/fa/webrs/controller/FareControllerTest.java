package com.drbotro.fa.webrs.controller;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.drbotro.fa.coreserviceapi.data.response.FareResponse;
import com.drbotro.fa.coreserviceapi.inter.IFareService;
import com.drbotro.fa.webapi.converter.response.FareResponseIntoFareWebResponseConverter;
import com.drbotro.fa.webapi.converter.response.generate.FareListWebResponseIntoGenericResponseFareListWebResponseConverter;
import com.drbotro.fa.webapi.response.FareWebResponse;
import com.drbotro.fa.webapi.response.GenericResponseFareWebResponse;
import com.drbotro.fa.webapi.version.Version;
import com.drbotro.fa.webrs.exception.CustomExceptionHandler;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = WebRSApplication.class)
//@SpringBootTest
//@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FareControllerTest{

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

    private static final FareResponse FARE_RESPONSE = FareResponse.builder().withFareResponseId(FARE_ID)
            .withFlightNumber(FLIGHT_NUMBER).withFlightDate(FLIGHT_DATE).withFare(FARE).withCurrency(CURRENCY).build();

    private static final FareWebResponse FARE_WEB_RESPONSE = FareWebResponse.builder().withId(FARE_ID)
            .withFlightNumber(FLIGHT_NUMBER).withFlightDate(FLIGHT_DATE).withFare(FARE).withCurrency(CURRENCY).build();

    private static final GenericResponseFareWebResponse GENERIC_WEB_RESPONSE_OK = GenericResponseFareWebResponse
            .builder().withStatus(STATUS_OK).withData(Arrays.asList(FARE_WEB_RESPONSE)).withError(null).build();

    private MockMvc mockMvc;

    @Mock
    private IFareService iFareService;

    @Mock
    private FareResponseIntoFareWebResponseConverter fareWebResponseConverter;
    @Mock
    private FareListWebResponseIntoGenericResponseFareListWebResponseConverter genericResponseFareListWebResponseConverter;

    @InjectMocks
    private FareController fareController;

    @Autowired
    private WebApplicationContext wac;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        //this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
        mockMvc = MockMvcBuilders.standaloneSetup(fareController).setControllerAdvice(CustomExceptionHandler.class)
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
        result.andExpect(jsonPath("$.status").value(STATUS_OK));
        result.andExpect(jsonPath("$.data", hasSize(1)));
    }

}
