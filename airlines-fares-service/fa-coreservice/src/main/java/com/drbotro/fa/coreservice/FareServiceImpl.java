package com.drbotro.fa.coreservice;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.drbotro.fa.common.exception.EntityConflictException;
import com.drbotro.fa.coreserviceapi.data.converter.request.FareRequestIntoFareConverter;
import com.drbotro.fa.coreserviceapi.data.converter.response.FareIntoFareResponseConverter;
import com.drbotro.fa.coreserviceapi.data.request.FareRequest;
import com.drbotro.fa.coreserviceapi.data.response.FareResponse;
import com.drbotro.fa.coreserviceapi.inter.IFareService;
import com.drbotro.fa.repository.dao.IFareRepository;
import com.drbotro.fa.repository.model.Fare;

@Service
public class FareServiceImpl implements IFareService{

    private static final Logger logger = LoggerFactory.getLogger(FareServiceImpl.class);

    @Autowired
    private FareRequestIntoFareConverter fareConverter;
    @Autowired
    private FareIntoFareResponseConverter fareResponseConverter;

    @Autowired
    private IFareRepository iFareRepository;

    @Override
    public FareResponse getFare(String flightNumber, String flightDate){
        logger.info("Looking for fares flightNumber: {}, flightDate: {} ", flightNumber, flightDate);
        Fare fare = null;
        Optional<Fare> optional = iFareRepository.getFareByFlightNumberAndFlightDate(flightNumber, flightDate);
        if(optional.isPresent()){
            fare = optional.get();
        }
        return fareResponseConverter.convert(fare);
    }

    @Override
    public List<FareResponse> getAllFare(){
        return iFareRepository.findAll().stream().map(f -> fareResponseConverter.convert(f))
                .collect(Collectors.toList());
    }

    @Override
    public FareResponse createFare(FareRequest fareRequest) throws EntityConflictException{
        Optional<Fare> fareOptional = iFareRepository.getFareByFlightNumberAndFlightDate(
                fareRequest.getFlightNumber().trim(), fareRequest.getFlightDate().trim());
        if(fareOptional.isPresent()){
            throw new EntityConflictException("El registro existe en BBDD");
        }
        return fareResponseConverter.convert(iFareRepository.save(fareConverter.convert(fareRequest)));
    }

}
