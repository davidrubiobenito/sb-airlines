package com.drbotro.ck.coreserviceapi.converter;

import org.springframework.stereotype.Component;

import com.drbotro.ck.common.converter.Converter;
import com.drbotro.ck.coreserviceapi.data.CheckInRecordRequest;
import com.drbotro.ck.coreserviceapi.model.CheckInRecord;

@Component
public class CheckInRecordRequestIntoCheckInRecordConverter implements Converter<CheckInRecordRequest, CheckInRecord>{

    @Override
    public CheckInRecord convert(CheckInRecordRequest checkInRecordRequest){
        if(checkInRecordRequest == null){
            return null;
        }
        return CheckInRecord.builder().withLastName(checkInRecordRequest.getLastName())
                .withFirstName(checkInRecordRequest.getFirstName()).withSeatNumber(checkInRecordRequest.getSeatNumber())
                .withCheckInTime(checkInRecordRequest.getCheckInTime())
                .withFlightNumber(checkInRecordRequest.getFlightNumber())
                .withFlightDate(checkInRecordRequest.getFlightDate()).withBookingId(checkInRecordRequest.getBookingId())
                .build();
    }

}
