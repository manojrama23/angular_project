package com.smart.rct.common.repositoryImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.*;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.smart.rct.common.entity.ActiveUsersTrackingEntity;
import com.smart.rct.common.repository.ActiveUsersTrackingRepository;
import com.smart.rct.constants.Constants;
import com.smart.rct.util.CommonUtil;


@Repository
@Transactional
public class ActiveUsersTrackingRepositoryImpl implements ActiveUsersTrackingRepository {

	final static Logger logger = LoggerFactory.getLogger(ActiveUsersTrackingRepositoryImpl.class);

	@PersistenceContext
	EntityManager entityManager;

	@Autowired
	CommonUtil commonUtil;

	/**
	 * This method will saveAuditTrailDetails
	 *
	 * @param auditTrailEntity
	 * @return boolean
	 */
	@Override
	public boolean savedetail(ActiveUsersTrackingEntity activeUsersTracking) {
		boolean status = false;
		try {
			entityManager.persist(activeUsersTracking);
			status = true;
		} catch (Exception e) {
			logger.error("Exception in ActiveUsersTrackingRepositoryImpl.savedetail(): "
					+ ExceptionUtils.getFullStackTrace(e));
		} finally {
			entityManager.flush();
			entityManager.clear();
		}
		return status;
	}

	/**
	 * This method will return getAuditDetails
	 *
	 * @param page
	 * @param count
	 * @return auditTrailEntity
	 */
	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public JSONObject getTrendData(String frequency, int year) {

		JSONObject objMap = new JSONObject();

		try {
			switch (frequency) {
			case Constants.THIS_MONTH:
				getTrendData(frequency, year);
				break;
			case Constants.LAST_MONTH:
				getTrendData(frequency, year);
				break;
			case Constants.THIS_YEAR:
				getTrendData(frequency, year);
				break;
			}
		} catch (Exception e) {
			logger.error(
					"Exception in ActiveUsersTrackingRepositoryImpl.getTrendData(): " + ExceptionUtils.getFullStackTrace(e));
		}
		return objMap;
	}

	/**
	 * This method will return getAuditDetails
	 *
	 * @param page
	 * @param count
	 * @return auditTrailEntity
	 */
	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public JSONObject getTrendData(String startDate, String endDate) {

		JSONObject objMap = new JSONObject();
		List<Object[]> data = null;
		try {
			Query query = entityManager.createNativeQuery(
					"SELECT DATE_FORMAT(`timestamp`, '%Y-%m-%d %H:%i') AS `interval`, active_users, active_sessions FROM `active_users_tracking` WHERE DATE(`timestamp`) >=:startDate and DATE(`timestamp`) <=:endDate");
			query.setParameter("startDate", startDate);
			query.setParameter("endDate", endDate);
			data = query.getResultList();
			List<Object> activeSessions = new ArrayList<>();
			List<Object> activeUsers = new ArrayList<>();
			List<Object> dArr = new ArrayList<>();
			for (Object[] obj : data) {
				dArr.add(obj[0]);
				activeSessions.add(obj[2]);
				activeUsers.add(obj[1]);
			}
			objMap.put("dates", dArr);
			objMap.put("activeSessions", activeSessions);
			objMap.put("activeUsers", activeUsers);

		} catch (Exception e) {
			logger.error(
					"Exception in ActiveUsersTrackingRepositoryImpl.getTrendData(): " + ExceptionUtils.getFullStackTrace(e));
		}
		return objMap;
	}

	/**
	 * This method will return getAuditDetails
	 *
	 * @param page
	 * @param count
	 * @return auditTrailEntity
	 */
	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public JSONObject getTrendData(String date) {

		JSONObject objMap = new JSONObject();
		List<Object[]> data = null;
		try {
			Query query = entityManager.createNativeQuery(
					"SELECT DATE_FORMAT(`timestamp`, '%H:%i') AS `interval`, active_users, active_sessions FROM `active_users_tracking` WHERE DATE(`timestamp`) =:date");
			query.setParameter("date", date);
			data = query.getResultList();
			List<Object> activeSessions = new ArrayList<>();
			List<Object> activeUsers = new ArrayList<>();
			List<Object> dArr = new ArrayList<>();
			for (Object[] obj : data) {
				dArr.add(obj[0]);
				activeSessions.add(obj[2]);
				activeUsers.add(obj[1]);
			}
			objMap.put("dates", dArr);
			objMap.put("activeSessions", activeSessions);
			objMap.put("activeUsers", activeUsers);
			System.out.println(data);
		} catch (Exception e) {
			logger.error(
					"Exception in ActiveUsersTrackingRepositoryImpl.getTrendData(): " + ExceptionUtils.getFullStackTrace(e));
		}
		return objMap;
	}

	/**
	 * This method will return getAuditDetails
	 *
	 * @param page
	 * @param count
	 * @return auditTrailEntity
	 */
	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public JSONObject getWeekData(String frequency) {

		JSONObject objMap = new JSONObject();
		List<Object[]> data = null;
		Query query = null;
		try {
			if (frequency.equalsIgnoreCase(Constants.THIS_WEEK)) {
				query = entityManager.createNativeQuery("SELECT \n"
						+ "  DATE(timestamp) AS day, active_users, active_sessions, timestamp, \n"
						+ "  YEARWEEK(timestamp, 1) AS week\n" + "FROM \n" + "  active_users_tracking\n" + "WHERE \n"
						+ "  YEARWEEK(timestamp, 1) = YEARWEEK(CURDATE(), 1)\n" + "ORDER BY \n" + "  week, day");

				data = query.getResultList();
				List<Object> activeSessions = new ArrayList<>();
				List<Object> activeUsers = new ArrayList<>();
				List<Object> dArr = new ArrayList<>();
				for (Object[] obj : data) {
					dArr.add(obj[0]);
					activeSessions.add(obj[2]);
					activeUsers.add(obj[1]);
				}
				objMap.put("dates", dArr);
				objMap.put("activeSessions", activeSessions);
				objMap.put("activeUsers", activeUsers);
				System.out.println(data);
			} else if (frequency.equalsIgnoreCase(Constants.LAST_WEEK)) {
				query = entityManager.createNativeQuery("SELECT \n"
						+ "  DATE(timestamp) AS day, active_users, active_sessions, timestamp, \n"
						+ "  YEARWEEK(timestamp, 1) AS week\n" + "FROM \n" + "  active_users_tracking\n" + "WHERE \n"
						+ "  YEARWEEK(timestamp, 1) = YEARWEEK(DATE_SUB(CURDATE(), INTERVAL 1 WEEK), 1)\n"
						+ "ORDER BY \n" + "  week, day");

				data = query.getResultList();
				List<Object> activeSessions = new ArrayList<>();
				List<Object> activeUsers = new ArrayList<>();
				List<Object> dArr = new ArrayList<>();
				for (Object[] obj : data) {
					dArr.add(obj[0]);
					activeSessions.add(obj[2]);
					activeUsers.add(obj[1]);
				}
				objMap.put("dates", dArr);
				objMap.put("activeSessions", activeSessions);
				objMap.put("activeUsers", activeUsers);
				System.out.println(data);
			} else if (frequency.equalsIgnoreCase(Constants.THIS_MONTH)) {
				query = entityManager.createNativeQuery("SELECT \n" + "  DATE(timestamp) AS day,\n"
						+ " timestamp, active_users,active_sessions\n" + "FROM \n" + "  active_users_tracking\n"
						+ "WHERE \n" + "  MONTH(timestamp) = MONTH(CURRENT_DATE)\n" + "ORDER BY \n" + "  day");

				data = query.getResultList();
				List<Object> activeSessions = new ArrayList<>();
				List<Object> activeUsers = new ArrayList<>();
				List<Object> dArr = new ArrayList<>();
				for (Object[] obj : data) {
					dArr.add(obj[0]);
					activeSessions.add(obj[2]);
					activeUsers.add(obj[1]);
				}
				objMap.put("dates", dArr);
				objMap.put("activeSessions", activeSessions);
				objMap.put("activeUsers", activeUsers);
				System.out.println(data);
			} else if (frequency.equalsIgnoreCase(Constants.LAST_MONTH)) {
				query = entityManager.createNativeQuery(
						"SELECT \n" + "  DATE(timestamp) AS day, timestamp, active_users, active_sessions\n" + "FROM \n"
								+ "  active_users_tracking\n" + "WHERE \n"
								+ "  MONTH(timestamp) = MONTH(DATE_SUB(CURRENT_DATE, INTERVAL 1 MONTH)) \n"
								+ "  AND YEAR(timestamp) = YEAR(DATE_SUB(CURRENT_DATE, INTERVAL 1 MONTH))\n"
								+ "ORDER BY\n" + "  day");

				data = query.getResultList();
				List<Object> activeSessions = new ArrayList<>();
				List<Object> activeUsers = new ArrayList<>();
				List<Object> dArr = new ArrayList<>();
				for (Object[] obj : data) {
					dArr.add(obj[0]);
					activeSessions.add(obj[2]);
					activeUsers.add(obj[1]);
				}
				objMap.put("dates", dArr);
				objMap.put("activeSessions", activeSessions);
				objMap.put("activeUsers", activeUsers);
				System.out.println(data);
			}
		} catch (Exception e) {
			logger.error(
					"Exception in ActiveUsersTrackingRepositoryImpl.getTrendData(): " + ExceptionUtils.getFullStackTrace(e));
		}
		return objMap;
	}

//TODO
	//custom queries and year queries
	
	
// public List<Object[]> getData(String frequency, int year) {
// List<Object[]> data = null;
// try {
// if (frequency.equalsIgnoreCase(Constants.YEARLY)) {
// Query query = entityManager.createNativeQuery("SELECT ROUND(AVG(active_users)) ,Round(AVG(active_sessions)), MONTH(timestamp) FROM active_users_tracking WHERE YEAR(timestamp) ="
// + " :year GROUP BY MONTH(timestamp)");
// query.setParameter("year", year);
// data = query.getResultList();
// System.out.println(data);
// } else if (frequency.equalsIgnoreCase(Constants.MONTH)) {
// Query query = entityManager.createNativeQuery("SELECT ROUND(AVG(active_users)) ,Round(AVG(active_sessions)), DAY(timestamp) FROM active_users_tracking WHERE MONTH(timestamp) ="
// + " :month GROUP BY DAY(timestamp)");
// query.setParameter("month", year);
// data = query.getResultList();
// System.out.println(data);
// }
//
// } catch (Exception e) {
// logger.info("Exception in ActiveUsersTrackingRepositoryImpl.getData(): " + ExceptionUtils.getFullStackTrace(e));
// } finally {
// entityManager.flush();
// entityManager.clear();
// }
// return data;
// }

	/*public List<Object[]> getData(String frequency, int year) {
		List<Object[]> data = null;
		try {
			if (frequency.equalsIgnoreCase(Constants.YEARLY)) {
				Query query = entityManager.createNativeQuery(
						"SELECT ROUND(AVG(active_users)) ,Round(AVG(active_sessions)), MONTH(timestamp) FROM active_users_tracking WHERE YEAR(timestamp) ="
								+ " :year GROUP BY MONTH(timestamp)");
				query.setParameter("year", year);
				data = query.getResultList();
				System.out.println(data);
			} else if (frequency.equalsIgnoreCase(Constants.MONTH)) {
				Query query = entityManager.createNativeQuery(
						"SELECT ROUND(AVG(active_users)) ,Round(AVG(active_sessions)), DAY(timestamp) FROM active_users_tracking WHERE MONTH(timestamp) ="
								+ " :month GROUP BY DAY(timestamp)");
				query.setParameter("month", year);
				data = query.getResultList();
				System.out.println(data);
			}

		} catch (Exception e) {
			logger.info(
					"Exception in ActiveUsersTrackingRepositoryImpl.getData(): " + ExceptionUtils.getFullStackTrace(e));
		} finally {
			entityManager.flush();
			entityManager.clear();
		}
		return data;
	}*/
}