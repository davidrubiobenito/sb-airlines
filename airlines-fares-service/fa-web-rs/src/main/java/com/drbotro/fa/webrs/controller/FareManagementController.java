package com.drbotro.fa.webrs.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.drbotro.fa.common.exception.EntityFareConflictException;
import com.drbotro.fa.common.exception.EntityFareNotFoundException;
import com.drbotro.fa.coreserviceapi.inter.IFareService;
import com.drbotro.fa.webapi.converter.request.FareWebRequestIntoFareRequestConverter;
import com.drbotro.fa.webapi.converter.response.FareResponseIntoFareWebResponseConverter;
import com.drbotro.fa.webapi.converter.response.generate.FareListWebResponseIntoGenericResponseFareListWebResponseConverter;
import com.drbotro.fa.webapi.request.FareWebRequest;
import com.drbotro.fa.webapi.response.FareWebResponse;
import com.drbotro.fa.webapi.response.GenericResponseFareWebResponse;
import com.drbotro.fa.webapi.version.Version;

@RestController
@RequestMapping("/api" + Version.V1 + "/management/fares")
public class FareManagementController{

    private static final Logger logger = LoggerFactory.getLogger(FareManagementController.class);

    @Autowired
    private IFareService iFaresService;

    @Autowired
    private FareWebRequestIntoFareRequestConverter fareRequestConverter;
    @Autowired
    private FareResponseIntoFareWebResponseConverter fareWebResponseConverter;
    @Autowired
    private FareListWebResponseIntoGenericResponseFareListWebResponseConverter genericResponseFareListWebResponseConverter;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ADMINTRAINEE')")
    public ResponseEntity<GenericResponseFareWebResponse> findAllFare(){

        List<FareWebResponse> fareListWebResponse = iFaresService.findAllFare().stream()
                .map(fwr -> fareWebResponseConverter.convert(fwr)).collect(Collectors.toList());

        return ResponseEntity.ok(genericResponseFareListWebResponseConverter.convert(fareListWebResponse));
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('FARE_WRITE')")
    public ResponseEntity<GenericResponseFareWebResponse> createFare(@Valid
    @RequestBody
    final FareWebRequest fareWebRequest) throws EntityFareConflictException{
        FareWebResponse fareWebResponse = fareWebResponseConverter
                .convert(iFaresService.saveFare(fareRequestConverter.convert(fareWebRequest)));

        return ResponseEntity.ok(genericResponseFareListWebResponseConverter.convert(Arrays.asList(fareWebResponse)));
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('FARE_WRITE')")
    public ResponseEntity<GenericResponseFareWebResponse> updateFare(@Valid
    @RequestBody
    final FareWebRequest fareWebRequest) throws EntityFareNotFoundException{
        FareWebResponse fareWebResponse = fareWebResponseConverter
                .convert(iFaresService.updateFare(fareRequestConverter.convert(fareWebRequest)));

        return ResponseEntity.ok(genericResponseFareListWebResponseConverter.convert(Arrays.asList(fareWebResponse)));
    }

}
