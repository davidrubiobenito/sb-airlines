package com.drbotro.fa.repository.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.drbotro.fa.repository.model.UserFare;

public interface IUserFareRepository extends JpaRepository<UserFare, Long>{

    /* @Query(value = "SELECT  U.user_id, U.username, U.password, U.is_account_non_expired, U.is_account_non_locked, U.is_credentials_non_expired, U.is_enabled, "
            + " R.role_id, R.role_name, " + " P.permission_id, P.permission_name " + " FROM user_fare U"
            + " JOIN user_role_join_fare URJ ON U.user_id = URJ.user_id"
            + " JOIN role_fare R ON URJ.role_id = R.role_id"
            + " JOIN role_permission_join_fare RPJ ON R.role_id = RPJ.role_id"
            + " JOIN permission_fare P ON RPJ.permission_id = P.permission_id"
            + " WHERE U.username =  :username", nativeQuery = true) */
    @Query(value = "SELECT u.userFareId, u.username, u.password, u.isAccountNonExpired, u.isAccountNonLocked, u.isCredentialsNonExpired, u.isEnabled, "
            + " r.roleFareId, r.roleName, " + " p.permissionFareId, p.permissionName "
            + " FROM UserFare u LEFT JOIN u.roles r LEFT JOIN r.permissions p WHERE u.username= :username")
    List<Object[]> findByUsername(@Param("username") String username);

}
