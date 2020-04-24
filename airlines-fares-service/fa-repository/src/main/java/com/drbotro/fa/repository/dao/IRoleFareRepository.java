package com.drbotro.fa.repository.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.drbotro.fa.repository.model.RoleFare;

public interface IRoleFareRepository extends JpaRepository<RoleFare, Long>{

    @Query(value = "SELECT * FROM role_fare u WHERE u.role_id = :id", nativeQuery = true)
    RoleFare getPorId(@Param("id") Long id);
}
