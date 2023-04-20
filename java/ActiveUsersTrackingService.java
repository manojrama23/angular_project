
package com.smart.rct.common.service;

import org.json.simple.JSONObject;

import com.smart.rct.common.dto.ActiveUsersRequestDto;
import com.smart.rct.common.entity.ActiveUsersTrackingEntity;

public interface ActiveUsersTrackingService {

	boolean savedetail(ActiveUsersTrackingEntity activeUserTracking);
	JSONObject getTrendData(ActiveUsersRequestDto request);

}