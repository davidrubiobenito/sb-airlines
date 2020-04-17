package com.drbotro.bk.webrs.controller.v2;

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
import com.drbotro.bk.coreserviceapi.inter.v2.IBookingService;
import com.drbotro.bk.repository.model.v2.BookingRecordBookingV2;
import com.drbotro.bk.webapi.converter.request.v2.BookingRecordWebRequestIntoBookingRecordRequestConverter;
import com.drbotro.bk.webapi.converter.response.generate.v2.BookingRecordListWebResponseIntoGenericResponseBookingRecordListWebResponseConverter;
import com.drbotro.bk.webapi.converter.response.generate.v2.BookingRecordWebResponseIntoGenericResponseBookingRecordListWebResponseConverter;
import com.drbotro.bk.webapi.converter.response.v2.BookingRecordResponseIntoBookingRecordWebResponseConverter;
import com.drbotro.bk.webapi.request.v2.BookingRecordWebRequest;
import com.drbotro.bk.webapi.response.v2.BookingRecordWebResponse;
import com.drbotro.bk.webapi.response.v2.GenericResponseBookingRecordWebResponse;
import com.drbotro.bk.webapi.version.Version;

@RestController(value = "bookingControllerV2")
@RequestMapping(Version.V2 + "/booking")
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
    public ResponseEntity<GenericResponseBookingRecordWebResponse> addBooking(
            @RequestBody BookingRecordWebRequest bookingRecordWebRequest) throws ErrorException{

        BookingRecordWebResponse bookingWebResponse = bookingRecordWebResponseConverter.convert(
                iBookingService.saveBookingRecord(bookingRecordRequestConverter.convert(bookingRecordWebRequest)));

        return ResponseEntity.ok(genericResponseBookingRecordAsListWebResponseConverter.convert(bookingWebResponse));
    }

    @PostMapping(path = "/addBook", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BookingRecordBookingV2> addBookingConv(
            @RequestBody BookingRecordBookingV2 bookingRecordBooking){

        BookingRecordBookingV2 bookingRecordBookingResponse = iBookingService.addBooking(bookingRecordBooking);

        return ResponseEntity.ok(bookingRecordBookingResponse);
    }

}
