package com.drbotro.bk.repository.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.drbotro.bk.repository.model.Inventory;

public interface IInventoryRepository extends JpaRepository<Inventory, Long>{

    Optional<Inventory> findByFlightNumberAndFlightDate(String flightNumber, String flightDate);

}
