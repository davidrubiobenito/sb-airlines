package com.drbotro.fa.repository.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.drbotro.fa.repository.model.Fare;

public interface IFareRepository extends JpaRepository<Fare, Long>{
    Optional<Fare> findFareByFlightNumberAndFlightDate(final String flightNumber, final String flightDate);
}
