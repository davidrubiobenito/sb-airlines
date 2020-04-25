package com.drbotro.fa.webrs.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.drbotro.fa.coreservice.CustomUserDetailsService;
import com.drbotro.fa.repository.model.UserFare;
import com.drbotro.fa.webapi.version.Version;

@RestController
public class WelcomeController{

    private static final Logger logger = LoggerFactory.getLogger(WelcomeController.class);

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @GetMapping(path = "/api" + Version.V1 + "/user", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ADMINTRAINEE')")
    public UserFare user(@RequestParam(value = "username") String username){
        return customUserDetailsService.findByUsername(username);
    }

}
