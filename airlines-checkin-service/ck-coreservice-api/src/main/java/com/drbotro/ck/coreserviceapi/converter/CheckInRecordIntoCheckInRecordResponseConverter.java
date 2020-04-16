package com.drbotro.ck.coreserviceapi.converter;

import org.springframework.stereotype.Component;

import com.drbotro.ck.common.converter.Converter;
import com.drbotro.ck.coreserviceapi.data.CheckInRecordResponse;
import com.drbotro.ck.coreserviceapi.model.CheckInRecord;

@Component
public class CheckInRecordIntoCheckInRecordResponseConverter implements Converter<CheckInRecord, CheckInRecordResponse>{

    @Override
    public CheckInRecordResponse convert(CheckInRecord checkInRecord){
        if(checkInRecord == null){
            return null;
        }
        return CheckInRecordResponse.builder().withId(checkInRecord.getId()).withLastName(checkInRecord.getLastName())
                .withFirstName(checkInRecord.getFirstName()).withSeatNumber(checkInRecord.getSeatNumber())
                .withCheckInTime(checkInRecord.getCheckInTime()).withFlightNumber(checkInRecord.getFlightNumber())
                .withFlightDate(checkInRecord.getFlightDate()).withBookingId(checkInRecord.getBookingId()).build();
    }

}
