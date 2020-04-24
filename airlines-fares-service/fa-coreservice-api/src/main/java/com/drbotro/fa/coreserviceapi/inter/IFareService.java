package com.drbotro.fa.coreserviceapi.inter;

import java.util.List;

import com.drbotro.fa.common.exception.EntityFareConflictException;
import com.drbotro.fa.common.exception.EntityFareNotFoundException;
import com.drbotro.fa.coreserviceapi.data.request.FareRequest;
import com.drbotro.fa.coreserviceapi.data.response.FareResponse;

public interface IFareService{

    FareResponse saveFare(final FareRequest fareRequest) throws EntityFareConflictException;

    List<FareResponse> findAllFare();

    FareResponse findFareByFlightNumberAndFlightDate(final String flightNumber, final String flightDate);

    FareResponse updateFare(final FareRequest fareRequest) throws EntityFareNotFoundException;

}
