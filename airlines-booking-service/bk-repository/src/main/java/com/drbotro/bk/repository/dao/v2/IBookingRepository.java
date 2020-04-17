package com.drbotro.bk.repository.dao.v2;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.drbotro.bk.repository.model.v2.BookingRecordBookingV2;

@Repository(value = "iBookingRepositoryV2")
public interface IBookingRepository extends JpaRepository<BookingRecordBookingV2, Long>{
}
