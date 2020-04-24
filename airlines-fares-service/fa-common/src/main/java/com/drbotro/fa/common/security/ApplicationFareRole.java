package com.drbotro.fa.common.security;

import static com.drbotro.fa.common.security.ApplicationFarePermission.FARE_READ;
import static com.drbotro.fa.common.security.ApplicationFarePermission.FARE_WRITE;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.google.common.collect.Sets;

public enum ApplicationFareRole{
    ANONYMOUS(Sets.newHashSet()), ADMIN(Sets.newHashSet(FARE_READ, FARE_WRITE)), ADMINTRAINEE(
            Sets.newHashSet(FARE_READ));

    private final Set<ApplicationFarePermission> permissions;

    ApplicationFareRole(Set<ApplicationFarePermission> permissions){
        this.permissions = permissions;
    }

    public Set<ApplicationFarePermission> getPermissions(){
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities(){
        Set<SimpleGrantedAuthority> simpleGrantedAuthorityPermission = getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission())).collect(Collectors.toSet());
        simpleGrantedAuthorityPermission.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return simpleGrantedAuthorityPermission;
    }

}
