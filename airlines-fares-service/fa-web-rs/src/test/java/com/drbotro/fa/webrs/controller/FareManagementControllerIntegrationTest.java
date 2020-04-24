package com.drbotro.fa.webrs.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.collection.IsEmptyCollection.empty;
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

import com.drbotro.fa.common.exception.EntityFareConflictException;
import com.drbotro.fa.common.exception.EntityFareNotFoundException;
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
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = FareManagementController.class)
@SpringBootTest
@Import(IntegrationTestConfiguration.class)
public class FareManagementControllerIntegrationTest{

    private static final String API = "/api";
    private static final String MANAGEMENT_FARES = "/management/fares";

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
    private FareManagementController fareManagementController;

    @Before
    public void setup(){
        this.mockMvc = MockMvcBuilders.standaloneSetup(fareManagementController)
                .setControllerAdvice(CustomExceptionHandler.class).build();
    }

    @Test
    public void whenFindAllFare_shouldReturn_OK() throws Exception{
        final String url = API + Version.V1 + MANAGEMENT_FARES;

        when(iFareService.findAllFare()).thenReturn(Arrays.asList(FARE_RESPONSE));
        when(fareWebResponseConverter.convert(FARE_RESPONSE)).thenReturn(FARE_WEB_RESPONSE);
        when(genericResponseFareListWebResponseConverter.convert(Arrays.asList(FARE_WEB_RESPONSE)))
                .thenReturn(GENERIC_WEB_RESPONSE_OK);

        final ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get(url)
                .contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON_VALUE));

        // Then
        result.andExpect(status().isOk());
        result.andExpect(jsonPath("$.status").value("OK"));
        result.andExpect(jsonPath("$.data", not(empty())));
        result.andDo(print());
    }

    @Test
    public void whenUpdateFare_shouldReturn_OK() throws Exception{
        final String url = API + Version.V1 + MANAGEMENT_FARES;

        when(fareWebRequestIntoFareRequestConverter.convert(FARE_WEB_REQUEST)).thenReturn(FARE_REQUEST);
        when(iFareService.updateFare(FARE_REQUEST)).thenReturn(FARE_RESPONSE);
        when(fareWebResponseConverter.convert(FARE_RESPONSE)).thenReturn(FARE_WEB_RESPONSE);
        when(genericResponseFareListWebResponseConverter.convert(Arrays.asList(FARE_WEB_RESPONSE)))
                .thenReturn(GENERIC_WEB_RESPONSE_OK);

        String requestJson = requestJson(FARE_WEB_REQUEST);

        final ResultActions result = mockMvc
                .perform(MockMvcRequestBuilders.put(url).contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(requestJson).accept(MediaType.APPLICATION_JSON_VALUE));

        //Then
        result.andExpect(status().isOk());
        result.andExpect(jsonPath("$.status").value("OK"));
        result.andExpect(jsonPath("$.data", not(empty())));
        result.andExpect(jsonPath("$.data[0].fare", is(100)));
        result.andExpect(jsonPath("$.data[0].currency", is("USD")));
        result.andExpect(jsonPath("$.error", is(nullValue())));
        result.andDo(print());
    }

    @Test
    public void whenUpdateFareNotExists_shouldReturn_KO() throws Exception{
        final String url = API + Version.V1 + MANAGEMENT_FARES;

        when(fareWebRequestIntoFareRequestConverter.convert(FARE_WEB_REQUEST)).thenReturn(FARE_REQUEST);
        when(iFareService.updateFare(FARE_REQUEST))
                .thenThrow(new EntityFareNotFoundException("Record Not Exists into BBDD"));

        String requestJson = requestJson(FARE_WEB_REQUEST);

        final ResultActions result = mockMvc
                .perform(MockMvcRequestBuilders.put(url).contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(requestJson).accept(MediaType.APPLICATION_JSON_VALUE));

        // Then
        result.andExpect(status().isNoContent());
        result.andExpect(jsonPath("$.status").value(STATUS_KO));
        result.andExpect(jsonPath("$.error", is(notNullValue())));
        result.andExpect(jsonPath("$.data", is(nullValue())));
        result.andDo(print());
    }

    @Test
    public void whenSaveFareNotExists_shouldReturn_OK() throws Exception{
        final String url = API + Version.V1 + MANAGEMENT_FARES;

        FareWebRequest fareWebRequest = FareWebRequest.builder().withFlightNumber("BB000").withFlightDate(FLIGHT_DATE)
                .withFare(FARE).withCurrency(CURRENCY).build();

        when(fareWebRequestIntoFareRequestConverter.convert(fareWebRequest)).thenReturn(FARE_REQUEST);
        when(iFareService.saveFare(FARE_REQUEST)).thenReturn(FARE_RESPONSE);
        when(fareWebResponseConverter.convert(FARE_RESPONSE)).thenReturn(FARE_WEB_RESPONSE);
        when(genericResponseFareListWebResponseConverter.convert(Arrays.asList(FARE_WEB_RESPONSE)))
                .thenReturn(GENERIC_WEB_RESPONSE_OK);

        String requestJson = requestJson(fareWebRequest);

        final ResultActions result = mockMvc
                .perform(MockMvcRequestBuilders.post(url).contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(requestJson).accept(MediaType.APPLICATION_JSON_VALUE));

        //Then
        result.andExpect(status().isOk());
        result.andExpect(jsonPath("$.status").value(STATUS_OK));
        result.andExpect(jsonPath("$.data", not(empty())));
        result.andExpect(jsonPath("$.data[0].fare", is(100)));
        result.andExpect(jsonPath("$.data[0].currency", is("USD")));
        result.andExpect(jsonPath("$.error", is(nullValue())));
        result.andDo(print());
    }

    @Test
    public void whenSaveFareExists_shouldReturn_KO() throws Exception{
        final String url = API + Version.V1 + MANAGEMENT_FARES;

        when(fareWebRequestIntoFareRequestConverter.convert(FARE_WEB_REQUEST)).thenReturn(FARE_REQUEST);
        when(iFareService.saveFare(FARE_REQUEST))
                .thenThrow(new EntityFareConflictException("El registro existe en BBDD"));

        String requestJson = requestJson(FARE_WEB_REQUEST);

        final ResultActions result = mockMvc
                .perform(MockMvcRequestBuilders.post(url).contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(requestJson).accept(MediaType.APPLICATION_JSON_VALUE));

        // Then
        result.andExpect(status().isConflict());
        result.andExpect(jsonPath("$.status").value(STATUS_KO));
        result.andExpect(jsonPath("$.error", is(notNullValue())));
        result.andExpect(jsonPath("$.data", is(nullValue())));
        result.andDo(print());
    }

    private String requestJson(FareWebRequest fareWebRequest) throws JsonProcessingException{
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        return ow.writeValueAsString(fareWebRequest);
    }

}
