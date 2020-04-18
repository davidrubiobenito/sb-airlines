package com.drbotro.fa.coreserviceapi.inter;

import java.util.List;

import com.drbotro.fa.common.exception.EntityConflictException;
import com.drbotro.fa.coreserviceapi.data.request.FareRequest;
import com.drbotro.fa.coreserviceapi.data.response.FareResponse;

public interface IFareService{
    List<FareResponse> getAllFare();

    FareResponse getFare(String flightNumber, String flightDate);

    FareResponse createFare(FareRequest fareRequest) throws EntityConflictException;
}
