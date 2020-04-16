package com.drbotro.ck.webrs.controller;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.drbotro.ck.webapi.request.CheckInRecordWebRequest;
import com.drbotro.ck.webapi.version.Version;
import com.drbotro.ck.webrs.WebRSApplication;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = WebRSApplication.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CheckInControllerTest{

    private static final String GET_CHECK_IN_BY_ID = "/checkin/get/{id}";
    private static final String CREATE_CHECK_IN = "/checkin/create";

    private static final String LAST_NAME = "LAST_NAME";
    private static final String FIRST_NAME = "FIRST_NAME";
    private static final String SEAT_NUMBER = "100A";
    private static final Date CHECK_IN_TIME = new Date();
    private static final String FLIGHT_NUMBER = "AA100";
    private static final String FLIGHT_DATE = "08-ABR-20";
    private static final long BOOKING_ID = 1L;

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    @Before
    public void setup(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void verifyGetCheckIn() throws Exception{
        final String url = Version.V1 + GET_CHECK_IN_BY_ID;

        mockMvc.perform(MockMvcRequestBuilders.get(url, 1L).contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("OK")).andExpect(jsonPath("$.data", hasSize(1))).andDo(print());
    }

    @Test
    public void verifyCheckIn() throws Exception{
        final String url = Version.V1 + CREATE_CHECK_IN;

        CheckInRecordWebRequest checkInRecordWebRequest = CheckInRecordWebRequest.builder().withLastName(LAST_NAME)
                .withFirstName(FIRST_NAME).withSeatNumber(SEAT_NUMBER).withCheckInTime(CHECK_IN_TIME)
                .withFlightNumber(FLIGHT_NUMBER).withFlightDate(FLIGHT_DATE).withBookingId(BOOKING_ID).build();

        String requestJson = requestJson(checkInRecordWebRequest);

        mockMvc.perform(MockMvcRequestBuilders.post(url).contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(requestJson).accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("OK")).andExpect(jsonPath("$.data", hasSize(1))).andDo(print());
    }

    private String requestJson(CheckInRecordWebRequest checkInRecordWebRequest) throws JsonProcessingException{
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        return ow.writeValueAsString(checkInRecordWebRequest);
    }

}
