package com.drbotro.bk.webrs.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.drbotro.bk.coreserviceapi.inter.IBookingService;
import com.drbotro.bk.webapi.converter.response.BookingRecordResponseIntoBookingRecordWebResponseConverter;
import com.drbotro.bk.webapi.converter.response.generate.BookingRecordWebResponseIntoGenericResponseBookingRecordListWebResponseConverter;
import com.drbotro.bk.webapi.response.BookingRecordWebResponse;
import com.drbotro.bk.webapi.response.GenericResponseBookingRecordWebResponse;
import com.drbotro.bk.webapi.version.Version;

@RestController
@RequestMapping(Version.V1 + "/booking")
public class BookingController{

    private static final Logger logger = LoggerFactory.getLogger(BookingController.class);

    @Autowired
    private IBookingService iBookingService;

    @Autowired
    private BookingRecordResponseIntoBookingRecordWebResponseConverter bookingRecordWebResponseConverter;

    @Autowired
    private BookingRecordWebResponseIntoGenericResponseBookingRecordListWebResponseConverter bookingRecordListGenerateWebResponseConverter;

    @GetMapping(path = "/get/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericResponseBookingRecordWebResponse> getBooking(@PathVariable(name = "id") long id){
        logger.info("Returning getBooking by id: {}", id);

        BookingRecordWebResponse bookingWebResponse = bookingRecordWebResponseConverter
                .convert(iBookingService.getBookingRecordById(id));

        return ResponseEntity.ok(bookingRecordListGenerateWebResponseConverter.convert(bookingWebResponse));
    }

}
