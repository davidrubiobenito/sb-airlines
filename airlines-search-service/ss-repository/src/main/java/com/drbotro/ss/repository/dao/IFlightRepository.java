package com.drbotro.ss.repository.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.drbotro.ss.coreserviceapi.model.Flight;

public interface IFlightRepository extends JpaRepository<Flight, Long>{

    List<Flight> findByOriginAndDestinationAndFlightDate(String origin, String destination, String flightDate);

    Flight findByFlightNumberAndFlightDate(String flightNumber, String flightDate);

}
