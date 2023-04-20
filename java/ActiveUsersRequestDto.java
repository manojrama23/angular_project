package com.smart.rct.common.dto;

import java.util.Date;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.smart.rct.common.entity.AuditTrailEntity;
import com.smart.rct.common.models.AuditTrailModel;
import com.smart.rct.constants.Constants;
import com.smart.rct.premigration.repository.FileUploadRepository;
import com.smart.rct.util.CommonUtil;

@Component
public class ActiveUsersRequestDto {

final static Logger logger = LoggerFactory.getLogger(ActiveUsersRequestDto.class);

private String frequency;

public String getStartDate() {
return startDate;
}

public void setStartDate(String startDate) {
this.startDate = startDate;
}

public String getEndDate() {
return endDate;
}

public void setEndDate(String endDate) {
this.endDate = endDate;
}

private String startDate;

private String endDate;

public String getFrequency() {
return frequency;
}

public void setFrequency(String frequency) {
this.frequency = frequency;
}

}