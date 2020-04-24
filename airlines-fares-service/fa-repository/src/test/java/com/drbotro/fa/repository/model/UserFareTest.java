package com.drbotro.fa.repository.model;

import static com.drbotro.fa.common.security.ApplicationFarePermission.FARE_READ;
import static com.drbotro.fa.common.security.ApplicationFarePermission.FARE_WRITE;
import static com.drbotro.fa.common.security.ApplicationFareRole.ADMIN;
import static com.drbotro.fa.common.security.ApplicationFareRole.ANONYMOUS;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.nullValue;

import java.util.Set;

import org.junit.Test;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.drbotro.fa.common.model.test.AbstractModelBeanTest;
import com.google.common.collect.Sets;

public class UserFareTest extends AbstractModelBeanTest<UserFare>{

    private static final long PERMISSION_ID = 1L;
    private static final PermissionFare PERMISSION_READ = PermissionFare.builder()
            .withPermissionFareIdId(PERMISSION_ID).withPermissionName(FARE_READ.name()).build();
    private static final PermissionFare PERMISSION_WRITE = PermissionFare.builder()
            .withPermissionFareIdId(PERMISSION_ID).withPermissionName(FARE_WRITE.name()).build();

    private static final long ROLE_ID = 1L;
    private static final Long ROLE_ID_OTHER = 2L;

    private static final RoleFare ROLE_ADMIN = RoleFare.builder().withRoleId(ROLE_ID).withRoleName(ADMIN.name())
            .withPermission(Sets.newHashSet(PERMISSION_READ, PERMISSION_WRITE)).build();
    private static final RoleFare ROLE_ANONYMOUS = RoleFare.builder().withRoleId(ROLE_ID_OTHER)
            .withRoleName(ANONYMOUS.name()).withPermission(Sets.newHashSet(PERMISSION_READ)).build();

    private static final Long USER_ID = 1L;
    private static final String PASSWORD = "password";
    private static final String USERNAME = "annasmith";
    private static final Long USER_ID_OTHER = 2L;
    private static final String USERNAME_OTHER = "linda";

    @Override
    public void initEntities(){
        entityA1 = UserFare.builder().withUserFareId(USER_ID).withUsername(USERNAME).withPassword(PASSWORD)
                .withRoles(Sets.newHashSet(ROLE_ADMIN)).withIsAccountNonExpired(true).withIsAccountNonLocked(true)
                .withIsCredentialsNonExpired(true).withIsEnabled(true).build();
        entityA2 = UserFare.builder().withUserFareId(USER_ID).withUsername(USERNAME).withPassword(PASSWORD)
                .withRoles(Sets.newHashSet(ROLE_ADMIN)).withIsAccountNonExpired(true).withIsAccountNonLocked(true)
                .withIsCredentialsNonExpired(true).withIsEnabled(true).build();
        entityB = UserFare.builder().withUserFareId(USER_ID_OTHER).withUsername(USERNAME_OTHER).withPassword(PASSWORD)
                .withRoles(Sets.newHashSet(ROLE_ANONYMOUS)).withIsAccountNonExpired(true).withIsAccountNonLocked(true)
                .withIsCredentialsNonExpired(true).withIsEnabled(true).build();

    }

    @Test
    public void getAuthoritiesRole(){

        UserFare userfare = UserFare.builder().withUserFareId(USER_ID).withUsername(USERNAME).withPassword(PASSWORD)
                .withRoles(Sets.newHashSet(ROLE_ADMIN)).withIsAccountNonExpired(true).withIsAccountNonLocked(true)
                .withIsCredentialsNonExpired(true).withIsEnabled(true).build();

        Set<SimpleGrantedAuthority> authoritiesExpected = Sets.newHashSet();
        authoritiesExpected.add(new SimpleGrantedAuthority(FARE_READ.name()));
        authoritiesExpected.add(new SimpleGrantedAuthority(FARE_WRITE.name()));
        authoritiesExpected.add(new SimpleGrantedAuthority("ROLE_" + ADMIN.name()));

        assertThat(userfare.getAuthorities(), not(nullValue()));
        assertThat(userfare.getAuthorities(), is(authoritiesExpected));

    }

}
