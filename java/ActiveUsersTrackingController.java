package com.smart.rct.common.controller;

import java.sql.Timestamp;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.smart.rct.common.dto.ActiveUsersRequestDto;
//import com.smart.rct.common.entity.ActiveUsersTrackingEntity;
import com.smart.rct.common.service.ActiveUsersTrackingService;
import com.smart.rct.constants.Constants;
import com.smart.rct.util.GlobalStatusMap;

@RestController
public class ActiveUsersTrackingController {

	final static Logger logger = LoggerFactory.getLogger(ActiveUsersTrackingController.class);

	@Autowired
	ActiveUsersTrackingService activeUsersTrackingService;

	/**
	 * This method will give the Active User details
	 *
	 * @param activeUserDetails
	 * @return JSONObject
	 */
	/*@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = Constants.UPDATE_ACTIVE_USERS, method = RequestMethod.GET)
	public String addActiveUsers() {
		try {
			ActiveUsersTracking activeUsersTracking = new ActiveUsersTracking();
			activeUsersTracking.setActiveUsers(GlobalStatusMap.loginUsersDetails.size());
			activeUsersTracking.setActiveSessions(GlobalStatusMap.socketSessionUser.size());
			activeUsersTracking.setTimestamp(new Timestamp(System.currentTimeMillis());
			activeUsersTrackingService.savedetail(activeUsersTracking);
		} catch (Exception e) {
			logger.error("Exception in ActiveUsersTracking.addActiveUsers(): " + ExceptionUtils.getFullStackTrace(e));
		}
		return null;
	}*/
	/**

		 * This method will give the Audit Trail details

		 * 

		 * @param auditListDetails

		 * @return JSONObject

		 */

		@SuppressWarnings({ "unchecked", "rawtypes" })

		@RequestMapping(value = Constants.GET_TREND_DATA, method = RequestMethod.POST)
		
		public JSONObject getTrendData(@RequestBody ActiveUsersRequestDto request){
			JSONObject response=activeUsersTrackingService.getTrendData(request);
			JSONObject obj=new JSONObject(response);
			return obj;

		}
		
		/*public String getTrendData(@RequestBody ActiveUsersRequestDto request) {

		activeUsersTrackingService.getTrendData(request);

			return null;


		}*/


}