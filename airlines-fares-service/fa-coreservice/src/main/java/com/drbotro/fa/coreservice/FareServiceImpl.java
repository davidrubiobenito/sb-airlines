package com.drbotro.fa.coreservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.drbotro.fa.coreserviceapi.inter.IFareService;
import com.drbotro.fa.coreserviceapi.model.Fare;
import com.drbotro.fa.repository.dao.IFareRepository;

@Service
public class FareServiceImpl implements IFareService{

    private static final Logger logger = LoggerFactory.getLogger(FareServiceImpl.class);

    @Autowired
    private IFareRepository iFareRepository;

    @Override
    public Fare getFare(String flightNumber, String flightDate){
        logger.info("Looking for fares flightNumber " + flightNumber + " flightDate " + flightDate);
        return iFareRepository.getFareByFlightNumberAndFlightDate(flightNumber, flightDate);
    }

}
