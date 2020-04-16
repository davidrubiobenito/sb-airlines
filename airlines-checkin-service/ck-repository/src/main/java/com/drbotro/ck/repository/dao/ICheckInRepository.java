package com.drbotro.ck.repository.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.drbotro.ck.coreserviceapi.model.CheckInRecord;

public interface ICheckInRepository extends JpaRepository<CheckInRecord, Long>{

}
