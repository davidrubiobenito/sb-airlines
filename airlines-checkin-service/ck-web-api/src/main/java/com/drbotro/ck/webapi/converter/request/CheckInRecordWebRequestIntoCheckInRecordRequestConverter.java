package com.drbotro.ck.webapi.converter.request;

import org.springframework.stereotype.Component;

import com.drbotro.ck.common.converter.Converter;
import com.drbotro.ck.coreserviceapi.data.CheckInRecordRequest;
import com.drbotro.ck.webapi.request.CheckInRecordWebRequest;

@Component
public class CheckInRecordWebRequestIntoCheckInRecordRequestConverter
        implements Converter<CheckInRecordWebRequest, CheckInRecordRequest>{

    @Override
    public CheckInRecordRequest convert(final CheckInRecordWebRequest checkInRecordWebRequest){
        if(checkInRecordWebRequest == null){
            return null;
        }
        return CheckInRecordRequest.builder().withLastName(checkInRecordWebRequest.getLastName())
                .withFirstName(checkInRecordWebRequest.getFirstName())
                .withSeatNumber(checkInRecordWebRequest.getSeatNumber())
                .withCheckInTime(checkInRecordWebRequest.getCheckInTime())
                .withFlightNumber(checkInRecordWebRequest.getFlightNumber())
                .withFlightDate(checkInRecordWebRequest.getFlightDate())
                .withBookingId(checkInRecordWebRequest.getBookingId()).build();
    }

}
