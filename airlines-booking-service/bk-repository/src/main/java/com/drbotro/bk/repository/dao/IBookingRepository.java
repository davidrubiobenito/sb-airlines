package com.drbotro.bk.repository.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.drbotro.bk.repository.model.BookingRecord;

public interface IBookingRepository extends JpaRepository<BookingRecord, Long>{

}
