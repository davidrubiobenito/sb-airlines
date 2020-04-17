package com.drbotro.bk.webrs.controller;

import java.util.List;
import java.util.stream.Collectors;

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

import com.drbotro.bk.common.exception.ErrorException;
import com.drbotro.bk.coreserviceapi.inter.IBookingService;
import com.drbotro.bk.repository.model.BookingRecordBooking;
import com.drbotro.bk.webapi.converter.request.BookingRecordWebRequestIntoBookingRecordRequestConverter;
import com.drbotro.bk.webapi.converter.response.BookingRecordResponseIntoBookingRecordWebResponseConverter;
import com.drbotro.bk.webapi.converter.response.generate.BookingRecordListWebResponseIntoGenericResponseBookingRecordListWebResponseConverter;
import com.drbotro.bk.webapi.converter.response.generate.BookingRecordWebResponseIntoGenericResponseBookingRecordListWebResponseConverter;
import com.drbotro.bk.webapi.request.BookingRecordWebRequest;
import com.drbotro.bk.webapi.response.BookingRecordWebResponse;
import com.drbotro.bk.webapi.response.GenericResponseBookingRecordWebResponse;
import com.drbotro.bk.webapi.version.Version;

@RestController(value = "bookingController")
@RequestMapping(Version.V1 + "/booking")
public class BookingController{

    private static final Logger logger = LoggerFactory.getLogger(BookingController.class);

    @Autowired
    private IBookingService iBookingService;

    @Autowired
    private BookingRecordWebRequestIntoBookingRecordRequestConverter bookingRecordRequestConverter;
    @Autowired
    private BookingRecordResponseIntoBookingRecordWebResponseConverter bookingRecordWebResponseConverter;
    @Autowired
    private BookingRecordWebResponseIntoGenericResponseBookingRecordListWebResponseConverter genericResponseBookingRecordAsListWebResponseConverter;
    @Autowired
    private BookingRecordListWebResponseIntoGenericResponseBookingRecordListWebResponseConverter genericResponseBookingRecordListWebResponseConverter;

    @GetMapping(path = "/get/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericResponseBookingRecordWebResponse> getBooking(@PathVariable(name = "id") long id){
        logger.info("Returning getBooking by id: {}", id);

        BookingRecordWebResponse bookingWebResponse = bookingRecordWebResponseConverter
                .convert(iBookingService.getBookingRecordById(id));

        return ResponseEntity.ok(genericResponseBookingRecordAsListWebResponseConverter.convert(bookingWebResponse));
    }

    @GetMapping(path = "/get", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericResponseBookingRecordWebResponse> getAllBooking(){
        List<BookingRecordWebResponse> bookingWebResponse = iBookingService.findAllBookingRecord().stream()
                .map(brr -> bookingRecordWebResponseConverter.convert(brr)).collect(Collectors.toList());

        return ResponseEntity.ok(genericResponseBookingRecordListWebResponseConverter.convert(bookingWebResponse));
    }

    @PostMapping(path = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BookingRecordBooking> addBooking(@RequestBody BookingRecordWebRequest bookingRecordWebRequest)
            throws ErrorException{

        /*
        BookingRecordWebResponse bookingWebResponse = bookingRecordWebResponseConverter.convert(
                iBookingService.saveBookingRecord(bookingRecordRequestConverter.convert(bookingRecordWebRequest)));
        
        return ResponseEntity.ok(genericResponseBookingRecordAsListWebResponseConverter.convert(bookingWebResponse));
        */
        BookingRecordBooking bookingRecordBookingResponse = iBookingService
                .saveBookingRecord(bookingRecordRequestConverter.convert(bookingRecordWebRequest));

        return ResponseEntity.ok(bookingRecordBookingResponse);
    }

    @PostMapping(path = "/addBook", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BookingRecordBooking> addBookingConv(@RequestBody BookingRecordBooking bookingRecordBooking){

        BookingRecordBooking bookingRecordBookingResponse = iBookingService.addBooking(bookingRecordBooking);

        return ResponseEntity.ok(bookingRecordBookingResponse);
    }

}
