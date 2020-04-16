package com.drbotro.bk.repository.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.drbotro.bk.repository.model.InventoryBooking;

public interface IInventoryRepository extends JpaRepository<InventoryBooking, Long>{

    Optional<InventoryBooking> findByFlightNumberAndFlightDate(String flightNumber, String flightDate);

}
