package com.drbotro.ss.coreservice;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.drbotro.ss.coreserviceapi.data.SearchQueryRequest;
import com.drbotro.ss.coreserviceapi.model.Fares;
import com.drbotro.ss.coreserviceapi.model.Flight;
import com.drbotro.ss.coreserviceapi.model.Inventory;
import com.drbotro.ss.repository.dao.IFlightRepository;

public class SearchFlightServiceImplTest{

    private static final String ORIGIN = "SEA";
    private static final String DESTINATION = "SFO";
    private static final String FLIGHT_DATE = "22-JAN-16";
    private static final String FLIGTH_NUMBER = "BF100";
    private static final Fares FARES = Fares.builder().withFare("100").withCurrency("USD").build();
    private static final Inventory INVENTORY = Inventory.builder().withCount(100).build();
    private static final Inventory OTHER_INVENTORY = Inventory.builder().withCount(-1).build();
    private static final SearchQueryRequest SEARCH_QUERY_REQUEST = SearchQueryRequest.builder().withOrigin(ORIGIN)
            .withDestination(DESTINATION).withFlightDate(FLIGHT_DATE).build();

    @Mock
    private Inventory inventory;
    @Mock
    private Flight flight;
    @Mock
    private IFlightRepository iFlightRepository;

    @InjectMocks
    private SearchFlightServiceImpl searchFlightServiceImpl;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldReturnListFlight(){
        List<Flight> flights = Arrays
                .asList(Flight.builder().withFlightNumber(FLIGTH_NUMBER).withOrigin(ORIGIN).withDestination(DESTINATION)
                        .withFlightDate(FLIGHT_DATE).withFares(FARES).withInventory(INVENTORY).build());

        when(iFlightRepository.findByOriginAndDestinationAndFlightDate(SEARCH_QUERY_REQUEST.getOrigin(),
                SEARCH_QUERY_REQUEST.getDestination(), SEARCH_QUERY_REQUEST.getFlightDate())).thenReturn(flights);

        assertThat(searchFlightServiceImpl.search(SEARCH_QUERY_REQUEST), is(flights));
    }

    @Test
    public void shouldReturnListEmpty(){
        List<Flight> flights = Arrays
                .asList(Flight.builder().withFlightNumber(FLIGTH_NUMBER).withOrigin(ORIGIN).withDestination(DESTINATION)
                        .withFlightDate(FLIGHT_DATE).withFares(FARES).withInventory(OTHER_INVENTORY).build());

        when(iFlightRepository.findByOriginAndDestinationAndFlightDate(SEARCH_QUERY_REQUEST.getOrigin(),
                SEARCH_QUERY_REQUEST.getDestination(), SEARCH_QUERY_REQUEST.getFlightDate())).thenReturn(flights);

        assertThat(searchFlightServiceImpl.search(SEARCH_QUERY_REQUEST), is(new ArrayList<>()));
    }

}
