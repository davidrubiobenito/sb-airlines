package com.drbotro.ck.coreservice;

import java.util.Date;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.drbotro.ck.coreserviceapi.converter.CheckInRecordIntoCheckInRecordResponseConverter;
import com.drbotro.ck.coreserviceapi.converter.CheckInRecordRequestIntoCheckInRecordConverter;
import com.drbotro.ck.coreserviceapi.data.CheckInRecordRequest;
import com.drbotro.ck.coreserviceapi.data.CheckInRecordResponse;
import com.drbotro.ck.coreserviceapi.inter.ICkeckInService;
import com.drbotro.ck.coreserviceapi.model.CheckInRecord;
import com.drbotro.ck.repository.dao.ICheckInRepository;
import com.fasterxml.jackson.core.JsonProcessingException;

@Service
public class CheckInServiceImpl implements ICkeckInService{

    private static final Logger logger = LoggerFactory.getLogger(CheckInServiceImpl.class);

    @Autowired
    private ICheckInRepository iCheckInRepository;

    @Autowired
    private CheckInRecordRequestIntoCheckInRecordConverter checkInRecordConverter;
    @Autowired
    private CheckInRecordIntoCheckInRecordResponseConverter checkInRecordResponseConverter;

    @Override
    public CheckInRecordResponse checkin(CheckInRecordRequest ckeckInRecordRequest) throws JsonProcessingException{
        ckeckInRecordRequest.cloneBuilder().withCheckInTime(new Date()).build();
        logger.info("Saving checkin: {}", ckeckInRecordRequest);
        return checkInRecordResponseConverter
                .convert(iCheckInRepository.save(checkInRecordConverter.convert(ckeckInRecordRequest)));
    }

    @Override
    public CheckInRecordResponse getCheckInRecord(long id){
        CheckInRecord checkInRecord = null;
        Optional<CheckInRecord> optional = iCheckInRepository.findById(id);
        if(optional.isPresent()){
            checkInRecord = optional.get();
        }
        return checkInRecordResponseConverter.convert(checkInRecord);
    }

}
