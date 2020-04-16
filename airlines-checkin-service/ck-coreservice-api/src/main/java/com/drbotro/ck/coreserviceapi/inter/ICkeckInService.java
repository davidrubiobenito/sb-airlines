package com.drbotro.ck.coreserviceapi.inter;

import com.drbotro.ck.coreserviceapi.data.CheckInRecordRequest;
import com.drbotro.ck.coreserviceapi.data.CheckInRecordResponse;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface ICkeckInService{

    CheckInRecordResponse checkin(CheckInRecordRequest ckeckInRecordRequest) throws JsonProcessingException;

    CheckInRecordResponse getCheckInRecord(long id);
}
