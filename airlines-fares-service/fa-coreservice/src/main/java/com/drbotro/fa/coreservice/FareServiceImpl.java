package com.drbotro.fa.coreservice;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.drbotro.fa.common.exception.EntityFareConflictException;
import com.drbotro.fa.common.exception.EntityFareNotFoundException;
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
    public FareResponse findFareByFlightNumberAndFlightDate(final String flightNumber, final String flightDate){
        Fare fare = null;
        Optional<Fare> optional = findFare(flightNumber.trim(), flightDate.trim());
        if(optional.isPresent()){
            fare = optional.get();
        }
        return fareResponseConverter.convert(fare);
    }

    @Override
    public List<FareResponse> findAllFare(){
        return iFareRepository.findAll().stream().map(f -> fareResponseConverter.convert(f))
                .collect(Collectors.toList());
    }

    @Override
    public FareResponse saveFare(final FareRequest fareRequest) throws EntityFareConflictException{
        Optional<Fare> fareOptional = findFare(fareRequest.getFlightNumber().trim(),
                fareRequest.getFlightDate().trim());
        if(fareOptional.isPresent()){
            throw new EntityFareConflictException("El registro existe en BBDD");
        }
        return fareResponseConverter.convert(iFareRepository.save(fareConverter.convert(fareRequest)));
    }

    @Override
    public FareResponse updateFare(final FareRequest fareRequest) throws EntityFareNotFoundException{
        Optional<Fare> fareOptional = findFare(fareRequest.getFlightNumber().trim(),
                fareRequest.getFlightDate().trim());
        if(!fareOptional.isPresent()){
            throw new EntityFareNotFoundException("El registro No existe en BBDD");
        }

        return fareResponseConverter.convert(iFareRepository.save(fareOptional.get().cloneBuilder()
                .withFare(fareRequest.getFare()).withCurrency(fareRequest.getCurrency()).build()));
    }

    private Optional<Fare> findFare(final String flightNumber, final String flightDate){
        return iFareRepository.findFareByFlightNumberAndFlightDate(flightNumber, flightDate);
    }

}
