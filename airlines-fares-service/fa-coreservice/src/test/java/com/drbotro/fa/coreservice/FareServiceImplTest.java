package com.drbotro.fa.coreservice;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.drbotro.fa.coreserviceapi.model.Fare;
import com.drbotro.fa.repository.dao.IFareRepository;

public class FareServiceImplTest{

    private static final String FLIGTH_NUMBER = "BF100";
    private static final String FLIGHT_DATE = "22-JAN-16";
    private static final String FARE = "100";

    @Mock
    private IFareRepository iFareRespository;

    @InjectMocks
    private FareServiceImpl fareServiceImpl;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldReturnFare(){
        Fare fare = Fare.builder().withFlightNumber(FLIGTH_NUMBER).withFlightDate(FLIGHT_DATE).withFare(FARE).build();
        when(iFareRespository.getFareByFlightNumberAndFlightDate(FLIGTH_NUMBER, FLIGHT_DATE)).thenReturn(fare);

        assertThat(fareServiceImpl.getFare(FLIGTH_NUMBER, FLIGHT_DATE), is(fare));
    }

}
