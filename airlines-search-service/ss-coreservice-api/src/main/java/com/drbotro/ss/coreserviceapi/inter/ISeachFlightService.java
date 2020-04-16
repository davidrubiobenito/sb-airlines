package com.drbotro.ss.coreserviceapi.inter;

import java.util.List;

import com.drbotro.ss.coreserviceapi.data.SearchQueryRequest;
import com.drbotro.ss.coreserviceapi.model.Flight;

public interface ISeachFlightService{

    List<Flight> search(SearchQueryRequest query);

    void updateInventory(String flightNumber, String flightDate, int inventory);

}
