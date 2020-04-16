package com.drbotro.ck.webapi.converter.response.generate;

import java.util.Arrays;

import org.springframework.stereotype.Component;

import com.drbotro.ck.common.converter.Converter;
import com.drbotro.ck.common.response.GenericResponse;
import com.drbotro.ck.webapi.response.CheckInRecordWebResponse;

@Component
public class CheckInRecordWebResponseIntoCheckInRecordListGenerateWebResponseConverter
        implements Converter<CheckInRecordWebResponse, GenericResponse<CheckInRecordWebResponse>>{

    private static final String STATUS_OK = "OK";

    @Override
    public GenericResponse<CheckInRecordWebResponse> convert(final CheckInRecordWebResponse checkInRecordWebResponse){
        if(checkInRecordWebResponse == null){
            return null;
        }
        return GenericResponse.builder().withStatus(STATUS_OK).withData(Arrays.asList(checkInRecordWebResponse))
                .withError(null).build();
    }
}
