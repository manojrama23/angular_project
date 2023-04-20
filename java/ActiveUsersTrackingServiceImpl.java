package com.smart.rct.common.serviceImpl;

import java.util.Date;
import java.util.Map;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smart.rct.common.dto.ActiveUsersRequestDto;
import com.smart.rct.common.entity.ActiveUsersTrackingEntity;
import com.smart.rct.common.repository.ActiveUsersTrackingRepository;
import com.smart.rct.common.service.ActiveUsersTrackingService;
import com.smart.rct.constants.Constants;
import com.smart.rct.util.DateUtil;

@Service
public class ActiveUsersTrackingServiceImpl implements ActiveUsersTrackingService {

final static Logger logger = LoggerFactory.getLogger(ActiveUsersTrackingServiceImpl.class);

@Autowired
ActiveUsersTrackingRepository activeUserTrackingRepo;

@Autowired
DateUtil dateUtil;

/**
* This api will save AuditTrailDetails
*
* @param auditTrailEntity
* @return boolean
*/
@Override
public boolean savedetail(ActiveUsersTrackingEntity activeTracking) {
boolean status = false;
try {
status = activeUserTrackingRepo.savedetail(activeTracking);
} catch (Exception e) {
logger.error("Exception in ActiveUsersTrackingServiceImpl.savedetail(): " + ExceptionUtils.getFullStackTrace(e));
}
return status;
}

/**
* This api will save AuditTrailDetails
*
* @param auditTrailEntity
* @return boolean
*/
@Override
public JSONObject getTrendData(ActiveUsersRequestDto request) {
try {
switch(request.getFrequency()) {
case Constants.TODAY:
return activeUserTrackingRepo.getTrendData(DateUtil.dateToString(new Date(), "yyyy-MM-dd"));
case Constants.YESTERDAY:
return activeUserTrackingRepo.getTrendData(DateUtil.dateToString(DateUtil.getPreviousDate(24), "yyyy-MM-dd"));
case Constants.THIS_WEEK:
return activeUserTrackingRepo.getTrendData(request.getFrequency(), DateUtil.getCurrentWeek());
case Constants.LAST_WEEK:
return activeUserTrackingRepo.getTrendData(request.getFrequency(), DateUtil.getPreviousWeek());
case Constants.THIS_MONTH:
return activeUserTrackingRepo.getTrendData(request.getFrequency(), DateUtil.getCurrentMonth());
case Constants.LAST_MONTH:
return activeUserTrackingRepo.getTrendData(request.getFrequency(), DateUtil.getPreviousMonth());
case Constants.THIS_YEAR:
activeUserTrackingRepo.getTrendData(request.getFrequency(), DateUtil.getCurrentYear());
return null;
case Constants.LAST_YEAR:
//activeUserTrackingRepo.getTrendData(request.getFrequency(), dateUtil.getCurrentYear());
return null;
case Constants.CUSTOM:
return null;

}
} catch (Exception e) {
logger.error("Exception in AuditTrailServiceImpl.savedetail(): " + ExceptionUtils.getFullStackTrace(e));
}
return null;
}

}