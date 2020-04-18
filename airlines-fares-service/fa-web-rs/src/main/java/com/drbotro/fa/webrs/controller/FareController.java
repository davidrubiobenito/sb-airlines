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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.drbotro.fa.common.exception.EntityConflictException;
import com.drbotro.fa.coreserviceapi.inter.IFareService;
import com.drbotro.fa.webapi.converter.request.FareWebRequestIntoFareRequestConverter;
import com.drbotro.fa.webapi.converter.response.FareResponseIntoFareWebResponseConverter;
import com.drbotro.fa.webapi.converter.response.generate.FareListWebResponseIntoGenericResponseFareListWebResponseConverter;
import com.drbotro.fa.webapi.request.FareWebRequest;
import com.drbotro.fa.webapi.response.FareWebResponse;
import com.drbotro.fa.webapi.response.GenericResponseFareWebResponse;
import com.drbotro.fa.webapi.version.Version;

@RestController
@RequestMapping(Version.V1 + "/fares")
public class FareController{

    private static final Logger logger = LoggerFactory.getLogger(FareController.class);

    @Autowired
    private IFareService iFaresService;

    @Autowired
    private FareWebRequestIntoFareRequestConverter fareRequestConverter;
    @Autowired
    private FareResponseIntoFareWebResponseConverter fareWebResponseConverter;
    @Autowired
    private FareListWebResponseIntoGenericResponseFareListWebResponseConverter genericResponseFareListWebResponseConverter;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericResponseFareWebResponse> getAllFare(){

        List<FareWebResponse> fareListWebResponse = iFaresService.getAllFare().stream()
                .map(fwr -> fareWebResponseConverter.convert(fwr)).collect(Collectors.toList());

        return ResponseEntity.ok(genericResponseFareListWebResponseConverter.convert(fareListWebResponse));
    }

    @GetMapping(path = "/get", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericResponseFareWebResponse> getFare(
            @RequestParam(value = "flightNumber") String flightNumber,
            @RequestParam(value = "flightDate") String flightDate){

        FareWebResponse fareWebResponse = fareWebResponseConverter
                .convert(iFaresService.getFare(flightNumber, flightDate));

        return ResponseEntity.ok(genericResponseFareListWebResponseConverter.convert(Arrays.asList(fareWebResponse)));
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericResponseFareWebResponse> getFare(@Valid @RequestBody FareWebRequest fareWebRequest)
            throws EntityConflictException{
        FareWebResponse fareWebResponse = fareWebResponseConverter
                .convert(iFaresService.createFare(fareRequestConverter.convert(fareWebRequest)));

        return ResponseEntity.ok(genericResponseFareListWebResponseConverter.convert(Arrays.asList(fareWebResponse)));
    }

}
