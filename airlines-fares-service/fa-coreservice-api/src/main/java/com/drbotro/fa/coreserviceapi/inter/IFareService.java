package com.drbotro.fa.coreserviceapi.inter;

import com.drbotro.fa.coreserviceapi.model.Fare;

public interface IFareService{
    Fare getFare(String flightNumber, String flightDate);
}
