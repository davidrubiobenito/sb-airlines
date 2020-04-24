package com.drbotro.fa.webrs.controller;

import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import com.drbotro.fa.coreserviceapi.inter.IFareService;
import com.drbotro.fa.webapi.converter.request.FareWebRequestIntoFareRequestConverter;
import com.drbotro.fa.webapi.converter.response.FareResponseIntoFareWebResponseConverter;
import com.drbotro.fa.webapi.converter.response.generate.FareListWebResponseIntoGenericResponseFareListWebResponseConverter;

@TestConfiguration
public class IntegrationTestConfiguration{

    @Bean
    public IFareService iFareService(){
        return Mockito.mock(IFareService.class);
    }

    @Bean
    public FareWebRequestIntoFareRequestConverter fareRequestConverter(){
        return Mockito.mock(FareWebRequestIntoFareRequestConverter.class);
    }

    @Bean
    public FareResponseIntoFareWebResponseConverter fareWebResponseConverter(){
        return Mockito.mock(FareResponseIntoFareWebResponseConverter.class);
    }

    @Bean
    public FareListWebResponseIntoGenericResponseFareListWebResponseConverter genericResponseFareListWebResponseConverter(){
        return Mockito.mock(FareListWebResponseIntoGenericResponseFareListWebResponseConverter.class);
    }

}
