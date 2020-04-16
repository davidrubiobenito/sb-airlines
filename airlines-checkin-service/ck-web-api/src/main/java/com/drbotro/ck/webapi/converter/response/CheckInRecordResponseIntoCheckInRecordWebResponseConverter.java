package com.drbotro.ck.webapi.converter.response;

import org.springframework.stereotype.Component;

import com.drbotro.ck.common.converter.Converter;
import com.drbotro.ck.coreserviceapi.data.CheckInRecordResponse;
import com.drbotro.ck.webapi.response.CheckInRecordWebResponse;

@Component
public class CheckInRecordResponseIntoCheckInRecordWebResponseConverter
        implements Converter<CheckInRecordResponse, CheckInRecordWebResponse>{

    @Override
    public CheckInRecordWebResponse convert(final CheckInRecordResponse checkInRecordResponse){
        if(checkInRecordResponse == null){
            return null;
        }
        return CheckInRecordWebResponse.builder().withId(checkInRecordResponse.getId())
                .withLastName(checkInRecordResponse.getLastName()).withFirstName(checkInRecordResponse.getFirstName())
                .withSeatNumber(checkInRecordResponse.getSeatNumber())
                .withCheckInTime(checkInRecordResponse.getCheckInTime())
                .withFlightNumber(checkInRecordResponse.getFlightNumber())
                .withFlightDate(checkInRecordResponse.getFlightDate())
                .withBookingId(checkInRecordResponse.getBookingId()).build();
    }

}
