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

public class RoleFareTest extends AbstractModelBeanTest<RoleFare>{

    private static final long PERMISSION_ID = 1L;
    private static final PermissionFare PERMISSION_READ = PermissionFare.builder().withPermissionFareIdId(PERMISSION_ID)
            .withPermissionName(FARE_READ.name()).build();
    private static final PermissionFare PERMISSION_WRITE = PermissionFare.builder()
            .withPermissionFareIdId(PERMISSION_ID).withPermissionName(FARE_WRITE.name()).build();

    private static final long ROLE_ID = 1L;
    private static final long ROLE_ID_OTHER = 2L;

    @Override
    public void initEntities(){
        entityA1 = RoleFare.builder().withRoleId(ROLE_ID).withRoleName(ADMIN.name())
                .withPermission(Sets.newHashSet(PERMISSION_READ, PERMISSION_WRITE)).build();
        entityA2 = RoleFare.builder().withRoleId(ROLE_ID).withRoleName(ADMIN.name())
                .withPermission(Sets.newHashSet(PERMISSION_READ, PERMISSION_WRITE)).build();
        entityB = RoleFare.builder().withRoleId(ROLE_ID_OTHER).withRoleName(ANONYMOUS.name())
                .withPermission(Sets.newHashSet(PERMISSION_READ)).build();

    }

    @Test
    public void getGrantedAuthorities(){

        RoleFare roleFare = RoleFare.builder().withRoleId(ROLE_ID).withRoleName(ADMIN.name())
                .withPermission(Sets.newHashSet(PERMISSION_READ, PERMISSION_WRITE)).build();

        Set<SimpleGrantedAuthority> grantedAuthoritiesExpected = Sets.newHashSet();
        grantedAuthoritiesExpected.add(new SimpleGrantedAuthority(FARE_READ.name()));
        grantedAuthoritiesExpected.add(new SimpleGrantedAuthority(FARE_WRITE.name()));
        grantedAuthoritiesExpected.add(new SimpleGrantedAuthority("ROLE_" + ADMIN.name()));

        assertThat(roleFare.getGrantedAuthorities(), not(nullValue()));
        assertThat(roleFare.getGrantedAuthorities(), is(grantedAuthoritiesExpected));

    }

}
