package com.drbotro.bk.repository.dao.v2;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.drbotro.bk.repository.model.v2.PassengerBookingV2;

@Repository(value = "iPassengerRepositoryV2")
public interface IPassengerRepository extends JpaRepository<PassengerBookingV2, Long>{
}
