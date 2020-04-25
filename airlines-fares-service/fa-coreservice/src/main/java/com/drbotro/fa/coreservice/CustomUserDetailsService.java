package com.drbotro.fa.coreservice;

import java.util.ListIterator;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.drbotro.fa.common.exception.CustomUsernameNotFoundException;
import com.drbotro.fa.repository.dao.IRoleFareRepository;
import com.drbotro.fa.repository.dao.IUserFareRepository;
import com.drbotro.fa.repository.model.PermissionFare;
import com.drbotro.fa.repository.model.RoleFare;
import com.drbotro.fa.repository.model.UserFare;
import com.google.common.collect.Sets;

@Service
public class CustomUserDetailsService implements UserDetailsService{

    private static final Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);

    @Autowired
    private IUserFareRepository iUserFareRepository;

    @Autowired
    private IRoleFareRepository iRoleFareRepository;

    @Override
    public UserDetails loadUserByUsername(String username){

        Set<PermissionFare> permissionFareAux = Sets.newHashSet();
        Set<RoleFare> roleFareAux = Sets.newHashSet();
        Set<UserFare> userFareAux = Sets.newHashSet();

        ListIterator<Object[]> iter = iUserFareRepository.findByUsername(username).listIterator();

        if(!iter.hasNext()){
            throw new CustomUsernameNotFoundException("User not found BBDD");
        }

        while(iter.hasNext()){

            Object[] item = iter.next(); // fetch the object

            permissionFareAux.add(PermissionFare.builder().withPermissionFareIdId((Long) item[9])
                    .withPermissionName((String) item[10]).build());

            roleFareAux.add(RoleFare.builder().withRoleId((Long) item[7]).withRoleName((String) item[8]).build());

            userFareAux.add(UserFare.builder().withUserFareId((Long) item[0]).withUsername((String) item[1])
                    .withPassword((String) item[2]).withIsAccountNonExpired((boolean) item[3])
                    .withIsAccountNonLocked((boolean) item[4]).withIsCredentialsNonExpired((boolean) item[5])
                    .withIsEnabled((boolean) item[6]).build());
        }

        roleFareAux = roleFareAux.stream().map(r -> r.cloneBuilder().withPermission(permissionFareAux).build())
                .collect(Collectors.toSet());

        UserDetails userDetails = null;

        for(UserFare user : userFareAux){
            userDetails = user.cloneBuilder().withRoles(roleFareAux).build();
        }

        logger.info("User:  {} ", userDetails);

        return userDetails;
    }

    public UserFare findByUsername(String username){

        Set<PermissionFare> permissionFareAux = Sets.newHashSet();
        Set<RoleFare> roleFareAux = Sets.newHashSet();
        Set<UserFare> userFareAux = Sets.newHashSet();

        try{

            ListIterator<Object[]> iter = iUserFareRepository.findByUsername(username).listIterator();

            if(!iter.hasNext()){
                throw new CustomUsernameNotFoundException("User not found BBDD");
            }

            while(iter.hasNext()){

                Object[] item = iter.next(); // fetch the object

                permissionFareAux.add(PermissionFare.builder().withPermissionFareIdId((Long) item[9])
                        .withPermissionName((String) item[10]).build());

                roleFareAux.add(RoleFare.builder().withRoleId((Long) item[7]).withRoleName((String) item[8]).build());

                userFareAux.add(UserFare.builder().withUserFareId((Long) item[0]).withUsername((String) item[1])
                        .withPassword((String) item[2]).withIsAccountNonExpired((boolean) item[3])
                        .withIsAccountNonLocked((boolean) item[4]).withIsCredentialsNonExpired((boolean) item[5])
                        .withIsEnabled((boolean) item[6]).build());
            }

            roleFareAux = roleFareAux.stream().map(r -> r.cloneBuilder().withPermission(permissionFareAux).build())
                    .collect(Collectors.toSet());

            UserFare userFare = null;

            for(UserFare user : userFareAux){
                userFare = user.cloneBuilder().withRoles(roleFareAux).build();
            }

            logger.info("User:  {} ", userFare);

            return userFare;

        }catch(CustomUsernameNotFoundException ae){
            throw new CustomUsernameNotFoundException("User not found BBDD");
        }

    }

}
