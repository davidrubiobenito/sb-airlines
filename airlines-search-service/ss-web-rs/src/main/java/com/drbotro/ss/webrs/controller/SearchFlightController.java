package com.drbotro.ss.webrs.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.drbotro.ss.common.response.GenericResponse;
import com.drbotro.ss.coreserviceapi.inter.ISeachFlightService;
import com.drbotro.ss.webapi.converter.request.SearchQueryWebRequestIntoSearchQueryRequestConverter;
import com.drbotro.ss.webapi.converter.response.FlightListIntoFlightListWebResponseConverter;
import com.drbotro.ss.webapi.converter.response.generate.FlightListWebResponseIntoFlightListGenerateWebResponseConverter;
import com.drbotro.ss.webapi.request.SearchQueryWebRequest;
import com.drbotro.ss.webapi.response.FlightWebResponse;
import com.drbotro.ss.webapi.version.Version;

@RestController
@RequestMapping(Version.V1 + "/search")
public class SearchFlightController{

    private static final Logger logger = LoggerFactory.getLogger(SearchFlightController.class);

    @Autowired
    private ISeachFlightService searchFlightService;
    @Autowired
    private FlightListIntoFlightListWebResponseConverter flightWebResponseListConverter;
    @Autowired
    private FlightListWebResponseIntoFlightListGenerateWebResponseConverter flightListGenerateWebResponseConverter;
    @Autowired
    private SearchQueryWebRequestIntoSearchQueryRequestConverter searchQueryRequestConverter;

    @GetMapping(path = "/get", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericResponse<FlightWebResponse>> search(
            @RequestBody SearchQueryWebRequest searchQueryWebRequest){
        logger.info("Returning search");
        List<FlightWebResponse> flightWebResponse = flightWebResponseListConverter
                .convert(searchFlightService.search(searchQueryRequestConverter.convert(searchQueryWebRequest)));
        return ResponseEntity.ok(flightListGenerateWebResponseConverter.convert(flightWebResponse));
    }

}
