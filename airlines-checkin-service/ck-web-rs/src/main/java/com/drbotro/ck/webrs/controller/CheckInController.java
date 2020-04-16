package com.drbotro.ck.webrs.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.drbotro.ck.common.response.GenericResponse;
import com.drbotro.ck.coreserviceapi.inter.ICkeckInService;
import com.drbotro.ck.webapi.converter.request.CheckInRecordWebRequestIntoCheckInRecordRequestConverter;
import com.drbotro.ck.webapi.converter.response.CheckInRecordResponseIntoCheckInRecordWebResponseConverter;
import com.drbotro.ck.webapi.converter.response.generate.CheckInRecordWebResponseIntoCheckInRecordListGenerateWebResponseConverter;
import com.drbotro.ck.webapi.request.CheckInRecordWebRequest;
import com.drbotro.ck.webapi.response.CheckInRecordWebResponse;
import com.drbotro.ck.webapi.version.Version;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
@RequestMapping(Version.V1 + "/checkin")
public class CheckInController{

    private static final Logger logger = LoggerFactory.getLogger(CheckInController.class);

    @Autowired
    private ICkeckInService iCkeckInService;

    @Autowired
    private CheckInRecordResponseIntoCheckInRecordWebResponseConverter checkInRecordWebResponseConverter;
    @Autowired
    private CheckInRecordWebResponseIntoCheckInRecordListGenerateWebResponseConverter checkInRecordListGenerateWebResponseConverter;

    @Autowired
    private CheckInRecordWebRequestIntoCheckInRecordRequestConverter checkInRecordRequestConverter;

    @GetMapping(path = "/get/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericResponse<CheckInRecordWebResponse>> getCheckIn(@PathVariable(name = "id") long id){
        logger.info("Returning getCheckIn");

        CheckInRecordWebResponse checkInRecordWebResponse = checkInRecordWebResponseConverter
                .convert(iCkeckInService.getCheckInRecord(id));

        return ResponseEntity.ok(checkInRecordListGenerateWebResponseConverter.convert(checkInRecordWebResponse));
    }

    @PostMapping(path = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericResponse<CheckInRecordWebResponse>> checkIn(
            @RequestBody CheckInRecordWebRequest checkInRecordWebRequest) throws JsonProcessingException{

        CheckInRecordWebResponse checkInRecordWebResponse = checkInRecordWebResponseConverter
                .convert(iCkeckInService.checkin(checkInRecordRequestConverter.convert(checkInRecordWebRequest)));

        return ResponseEntity.ok(checkInRecordListGenerateWebResponseConverter.convert(checkInRecordWebResponse));
    }

}
