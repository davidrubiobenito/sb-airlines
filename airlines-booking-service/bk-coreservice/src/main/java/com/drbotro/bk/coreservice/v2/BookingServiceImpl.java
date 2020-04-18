package com.drbotro.bk.coreservice.v2;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.drbotro.bk.common.exception.ApiError;
import com.drbotro.bk.coreserviceapi.converter.v2.BookingRecordIntoBookingRecordResponseConverter;
import com.drbotro.bk.coreserviceapi.converter.v2.BookingRecordRequestIntoBookingRecordConverter;
import com.drbotro.bk.coreserviceapi.data.BookingStatus;
import com.drbotro.bk.coreserviceapi.data.request.v2.BookingRecordRequest;
import com.drbotro.bk.coreserviceapi.data.response.v2.BookingRecordResponse;
import com.drbotro.bk.coreserviceapi.data.response.v2.FareWebResponse;
import com.drbotro.bk.coreserviceapi.data.response.v2.GenericResponseFareWebResponse;
import com.drbotro.bk.coreserviceapi.inter.v2.IBookingService;
import com.drbotro.bk.repository.dao.v2.IBookingRepository;
import com.drbotro.bk.repository.dao.v2.IInventoryRepository;
import com.drbotro.bk.repository.dao.v2.IPassengerRepository;
import com.drbotro.bk.repository.model.v2.BookingRecordBookingV2;
import com.drbotro.bk.repository.model.v2.InventoryBookingV2;

@Service(value = "BookingServiceImplV2")
public class BookingServiceImpl implements IBookingService{

    private static final Logger logger = LoggerFactory.getLogger(BookingServiceImpl.class);
    private static final String FARE_URL = "http://localhost:8080/v1";

    @Autowired
    private RestTemplate template;

    @Autowired
    private IBookingRepository iBookingRepository;
    @Autowired
    private IInventoryRepository iInventoryRepository;
    @Autowired
    private IPassengerRepository iPassengerRepository;

    @Autowired
    private BookingRecordRequestIntoBookingRecordConverter bookingRecordConverter;
    @Autowired
    private BookingRecordIntoBookingRecordResponseConverter bookingRecordResponseConverter;

    @Override
    public BookingRecordResponse saveBookingRecord(BookingRecordRequest bookingRecordRequest) throws ApiError{

        logger.info("calling fares to get fare");
        //call fares to get fare
        GenericResponseFareWebResponse genericResponseFareWebResponse = template
                .getForObject(FARE_URL + "/fares/get?flightNumber=" + bookingRecordRequest.getFlightNumber()
                        + "&flightDate=" + bookingRecordRequest.getFlightDate(), GenericResponseFareWebResponse.class);
        logger.info("Fare genericResponseFareWebResponse: {}", genericResponseFareWebResponse);

        FareWebResponse fareWebResponse = FareWebResponse.builder().build();
        Optional<FareWebResponse> fareWebResponseOptional = genericResponseFareWebResponse.getData().stream()
                .findFirst();
        if(fareWebResponseOptional.isPresent()){
            fareWebResponse = fareWebResponse.cloneBuilder().withFare(fareWebResponseOptional.get().getFare())
                    .withFlightNumber(fareWebResponseOptional.get().getFlightNumber())
                    .withFlightDate(fareWebResponseOptional.get().getFlightDate()).build();

        }

        //check fare
        if(!bookingRecordRequest.getFare().equals(fareWebResponse.getFare()))
            throw ApiError.builder().withError(204).withDescription("fare is tampered").build();

        //check inventory
        Optional<InventoryBookingV2> inventoryOptional = iInventoryRepository.findByFlightNumberAndFlightDate(
                bookingRecordRequest.getFlightNumber(), bookingRecordRequest.getFlightDate());

        if(inventoryOptional.isPresent()){
            if(!inventoryOptional.get().isAvailable(bookingRecordRequest.getPassengersRequest().size())){
                throw ApiError.builder().withError(204).withDescription("No more seats avaialble").build();
            }else{
                logger.info("successfully checked inventory: {}", inventoryOptional.get());
                logger.info("calling inventory to update inventory");
                //update inventory
                InventoryBookingV2 inventoryAux = inventoryOptional.get().cloneBuilder().withAvailable(
                        inventoryOptional.get().getAvailable() - bookingRecordRequest.getPassengersRequest().size())
                        .build();
                iInventoryRepository.saveAndFlush(inventoryAux);
                logger.info("sucessfully updated inventory: {}", inventoryAux);
            }
        }

        //save booking
        BookingRecordRequest bookingRecordRequestAux = BookingRecordRequest.builder()
                .withFlightNumber(bookingRecordRequest.getFlightNumber()).withOrigin(bookingRecordRequest.getOrigin())
                .withDestination(bookingRecordRequest.getDestination())
                .withFlightDate(bookingRecordRequest.getFlightDate())
                .withBookingDate(bookingRecordRequest.getBookingDate()).withFare(bookingRecordRequest.getFare())
                .withStatus(BookingStatus.BOOKING_CONFIRMED).withBookingDate(new Date())
                .withPassengersRequest(bookingRecordRequest.getPassengersRequest()).build();

        logger.info("bookingRecordRequestAux : {}", bookingRecordRequestAux);

        return bookingRecordResponseConverter
                .convert(iBookingRepository.save(bookingRecordConverter.convert(bookingRecordRequestAux)));
    }

    @Override
    public BookingRecordResponse getBookingRecordById(long id){
        BookingRecordBookingV2 bookingRecord = null;
        Optional<BookingRecordBookingV2> optional = iBookingRepository.findById(id);
        if(optional.isPresent()){
            bookingRecord = optional.get();
        }
        return bookingRecordResponseConverter.convert(bookingRecord);
    }

    @Override
    public BookingRecordResponse updateStatusBookingRecord(String status, long id){
        BookingRecordBookingV2 bookingRecord = null;
        Optional<BookingRecordBookingV2> optional = iBookingRepository.findById(id);
        if(optional.isPresent()){
            bookingRecord = optional.get().cloneBuilder().withStatus(status).build();
        }
        return bookingRecordResponseConverter.convert(bookingRecord);
    }

    @Override
    public List<BookingRecordResponse> findAllBookingRecord(){
        List<BookingRecordBookingV2> bookingRecord = iBookingRepository.findAll();
        return bookingRecord.stream().map(br -> bookingRecordResponseConverter.convert(br))
                .collect(Collectors.toList());
    }

    @Override
    public BookingRecordBookingV2 addBooking(BookingRecordBookingV2 bookingRecordBooking){
        return iBookingRepository.save(bookingRecordBooking);
    }

}
