package com.drbotro.bk.webapi.converter.response.generate;

import java.util.Arrays;

import org.springframework.stereotype.Component;

import com.drbotro.bk.common.converter.Converter;
import com.drbotro.bk.webapi.response.GenericResponsePassengerWebResponse;
import com.drbotro.bk.webapi.response.PassengerWebResponse;

@Component(value = "passengerWebResponseIntoGerericResponsePassengerListWebResponseConverter")
public class PassengerWebResponseIntoGerericResponsePassengerListWebResponseConverter
        implements Converter<PassengerWebResponse, GenericResponsePassengerWebResponse>{

    private static final String STATUS_OK = "OK";

    @Override
    public GenericResponsePassengerWebResponse convert(final PassengerWebResponse passengerWebResponse){
        if(passengerWebResponse == null){
            return null;
        }
        return GenericResponsePassengerWebResponse.builder().withStatus(STATUS_OK)
                .withData(Arrays.asList(passengerWebResponse)).withError(null).build();
    }
}
