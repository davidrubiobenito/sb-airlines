package com.drbotro.fa.repository.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.drbotro.fa.coreserviceapi.model.Fare;

public interface IFareRepository extends JpaRepository<Fare, Long>{
    Fare getFareByFlightNumberAndFlightDate(String flightNumber, String flightDate);
}
