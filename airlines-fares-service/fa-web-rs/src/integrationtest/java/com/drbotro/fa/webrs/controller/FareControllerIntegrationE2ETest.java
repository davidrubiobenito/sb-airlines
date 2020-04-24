package com.drbotro.fa.webrs.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.collection.IsEmptyCollection.empty;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.drbotro.fa.coreserviceapi.data.response.FareResponse;
import com.drbotro.fa.webapi.request.FareWebRequest;
import com.drbotro.fa.webapi.response.FareWebResponse;
import com.drbotro.fa.webapi.response.GenericResponseFareWebResponse;
import com.drbotro.fa.webapi.version.Version;
import com.drbotro.fa.webrs.WebRSApplication;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = WebRSApplication.class)
@SpringBootTest
public class FareControllerIntegrationE2ETest{

    private static final String FARES = "/fares";
    private static final String GET = "/get";

    private static final String STATUS_OK = "OK";
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

    @Autowired
    private WebApplicationContext wac;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    @Ignore
    public void whenFindFareWithFlightNumberAnfFlightDate_shouldReturn_OK() throws Exception{
        final String url = Version.V1 + FARES + GET;

        final ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get(url)
                .contentType(MediaType.APPLICATION_JSON_VALUE).param("flightNumber", FLIGHT_NUMBER)
                .param("flightDate", FLIGHT_DATE).accept(MediaType.APPLICATION_JSON_VALUE));

        //Then
        result.andExpect(status().isOk());
        result.andExpect(jsonPath("$.status").value("OK"));
        result.andExpect(jsonPath("$.data", hasSize(1)));
        result.andDo(print());
    }

    @Test
    @Ignore
    public void whenFindAllFare_shouldReturn_OK() throws Exception{
        final String url = Version.V1 + FARES;
        mockMvc.perform(MockMvcRequestBuilders.get(url).contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("OK")).andExpect(jsonPath("$.data", not(empty()))).andDo(print());
    }

    @Test
    @Ignore
    public void whenUpdateFare_shouldReturn_OK() throws Exception{
        final String url = Version.V1 + FARES;
        FareWebRequest fareWebRequestUpdate = FareWebRequest.builder().withFlightNumber(FLIGHT_NUMBER)
                .withFlightDate(FLIGHT_DATE).withFare(FARE).withCurrency(CURRENCY).build();

        String requestJson = requestJson(fareWebRequestUpdate);

        mockMvc.perform(MockMvcRequestBuilders.put(url).contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(requestJson).accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("OK")).andExpect(jsonPath("$.data", not(empty())))
                .andExpect(jsonPath("$.data[0].fare", is(100))).andExpect(jsonPath("$.data[0].currency", is("USD")))
                .andExpect(jsonPath("$.error", is(nullValue()))).andDo(print());
    }

    @Test
    @Ignore
    public void whenUpdateFareNotExists_shouldReturn_KO() throws Exception{
        final String url = Version.V1 + FARES;

        FareWebRequest fareWebRequest = FareWebRequest.builder().withFlightNumber("BF000").withFlightDate(FLIGHT_DATE)
                .withFare(FARE).withCurrency(CURRENCY).build();

        String requestJson = requestJson(fareWebRequest);

        mockMvc.perform(MockMvcRequestBuilders.put(url).contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(requestJson).accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isNoContent())
                .andExpect(jsonPath("$.status").value("KO")).andExpect(jsonPath("$.error", is(notNullValue())))
                .andExpect(jsonPath("$.data", is(nullValue()))).andDo(print());
    }

    @Test
    @Ignore
    public void whenSaveFareNotExists_shouldReturn_OK() throws Exception{
        final String url = Version.V1 + FARES;

        FareWebRequest fareWebRequest = FareWebRequest.builder().withFlightNumber("BB000").withFlightDate(FLIGHT_DATE)
                .withFare(FARE).withCurrency(CURRENCY).build();

        String requestJson = requestJson(fareWebRequest);

        mockMvc.perform(MockMvcRequestBuilders.post(url).contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(requestJson).accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("OK")).andExpect(jsonPath("$.data", not(empty())))
                .andExpect(jsonPath("$.data[0].fare", is(100))).andExpect(jsonPath("$.data[0].currency", is("USD")))
                .andExpect(jsonPath("$.error", is(nullValue()))).andDo(print());
    }

    @Test
    @Ignore
    public void whenSaveFareExists_shouldReturn_KO() throws Exception{
        final String url = Version.V1 + FARES;

        FareWebRequest fareWebRequest = FareWebRequest.builder().withFlightNumber(FLIGHT_NUMBER)
                .withFlightDate(FLIGHT_DATE).withFare(FARE).withCurrency(CURRENCY).build();

        String requestJson = requestJson(fareWebRequest);

        mockMvc.perform(MockMvcRequestBuilders.post(url).contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(requestJson).accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isConflict())
                .andExpect(jsonPath("$.status").value("KO")).andExpect(jsonPath("$.error", is(notNullValue())))
                .andExpect(jsonPath("$.data", is(nullValue()))).andDo(print());
    }

    private String requestJson(FareWebRequest fareWebRequest) throws JsonProcessingException{
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        return ow.writeValueAsString(fareWebRequest);
    }

}
