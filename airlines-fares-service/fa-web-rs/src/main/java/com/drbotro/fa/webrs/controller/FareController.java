package com.drbotro.fa.webrs.controller;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.drbotro.fa.coreserviceapi.inter.IFareService;
import com.drbotro.fa.webapi.converter.response.FareResponseIntoFareWebResponseConverter;
import com.drbotro.fa.webapi.converter.response.generate.FareListWebResponseIntoGenericResponseFareListWebResponseConverter;
import com.drbotro.fa.webapi.response.FareWebResponse;
import com.drbotro.fa.webapi.response.GenericResponseFareWebResponse;
import com.drbotro.fa.webapi.version.Version;

@RestController
@RequestMapping("/api" + Version.V1 + "/fares")
public class FareController{

    private static final Logger logger = LoggerFactory.getLogger(FareController.class);

    @Autowired
    private IFareService iFaresService;

    @Autowired
    private FareResponseIntoFareWebResponseConverter fareWebResponseConverter;
    @Autowired
    private FareListWebResponseIntoGenericResponseFareListWebResponseConverter genericResponseFareListWebResponseConverter;

    @GetMapping(path = "/get", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericResponseFareWebResponse> findFareByFlightNumberAndFlightDate(
            @RequestParam(value = "flightNumber")
            final String flightNumber, @RequestParam(value = "flightDate")
            final String flightDate){

        FareWebResponse fareWebResponse = fareWebResponseConverter
                .convert(iFaresService.findFareByFlightNumberAndFlightDate(flightNumber, flightDate));

        return ResponseEntity.ok(genericResponseFareListWebResponseConverter.convert(Arrays.asList(fareWebResponse)));
    }

}
