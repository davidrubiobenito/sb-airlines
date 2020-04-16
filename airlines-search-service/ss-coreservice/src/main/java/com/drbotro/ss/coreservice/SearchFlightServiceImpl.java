package com.drbotro.ss.coreservice;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.drbotro.ss.coreserviceapi.data.SearchQueryRequest;
import com.drbotro.ss.coreserviceapi.inter.ISeachFlightService;
import com.drbotro.ss.coreserviceapi.model.Flight;
import com.drbotro.ss.coreserviceapi.model.Inventory;
import com.drbotro.ss.repository.dao.IFlightRepository;

@Service
public class SearchFlightServiceImpl implements ISeachFlightService{

    private static final Logger logger = LoggerFactory.getLogger(SearchFlightServiceImpl.class);

    @Autowired
    private IFlightRepository flightRepository;

    @Override
    public List<Flight> search(SearchQueryRequest query){

        List<Flight> flights = flightRepository.findByOriginAndDestinationAndFlightDate(query.getOrigin(),
                query.getDestination(), query.getFlightDate());

        List<Flight> searchResult = new ArrayList<>();
        searchResult.addAll(flights);

        flights.forEach(flight -> {
            flight.getFares();
            int inv = flight.getInventory().getCount();
            if(inv < 0){
                searchResult.remove(flight);
            }
        });

        return searchResult;

    }

    @Override
    public void updateInventory(String flightNumber, String flightDate, int inventory){
        logger.info("Updating inventory for flight " + flightNumber + " innventory " + inventory);

        Flight flight = flightRepository.findByFlightNumberAndFlightDate(flightNumber, flightDate);
        Inventory inv = flight.getInventory();
        inv.cloneBuilder().withCount(inventory);
        flightRepository.save(flight);
    }

}
