package com.drbotro.bk.repository.dao.v2;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.drbotro.bk.repository.model.v2.InventoryBookingV2;

@Repository(value = "iInventoryRepositoryV2")
public interface IInventoryRepository extends JpaRepository<InventoryBookingV2, Long>{
    Optional<InventoryBookingV2> findByFlightNumberAndFlightDate(String flightNumber, String flightDate);
}
