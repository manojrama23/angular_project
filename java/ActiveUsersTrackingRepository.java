package com.smart.rct.common.repository;

import org.json.simple.JSONObject;

import com.smart.rct.common.entity.ActiveUsersTrackingEntity;

public interface ActiveUsersTrackingRepository {
	
	boolean savedetail(ActiveUsersTrackingEntity activeUserTracking);
	JSONObject getTrendData(String frequency,int year);
	JSONObject getTrendData(String startDate,String endDate);
	JSONObject getTrendData(String date);
	JSONObject getWeekData(String frequency);

}
