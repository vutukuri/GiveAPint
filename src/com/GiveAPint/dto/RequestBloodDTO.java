package com.GiveAPint.dto;

import java.sql.Timestamp;
import java.util.List;

import com.GiveAPint.persistence.dbdo.ResultDBDO;

/**
 * Stores everything related to a request. [requestor's user id, timestamp,
 * location, queryType, parameters etc]. Furthermore, error, and token fields
 * are included.
 * 
 * @author abhi
 *
 */

public class RequestBloodDTO {

	private int userId;
	private String status;
	private Timestamp timeStamp;
	private int emergencyLevel;
	private String bloodGroup;
	private String queryType;
	private int kVal;
	private double rangeVal;
	private String error;
	private String token;
	private List<ResultDBDO> resultList;
	private Integer requestId;

	public RequestBloodDTO(Integer userId, String status, Timestamp timeStamp, int emergencyLevel, String bloodGroup,
			String queryType, double rangeVal, String token) {
		// TODO Auto-generated constructor stub
		this.userId = userId;
		this.status = status;
		this.timeStamp = timeStamp;
		this.emergencyLevel = emergencyLevel;
		this.bloodGroup = bloodGroup;
		this.queryType = queryType;
		this.rangeVal = rangeVal;
		this.token = token;

	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Timestamp getTimeStamp() {
		return this.timeStamp;
	}

	public void setTimeStamp(Timestamp timeStamp) {
		this.timeStamp = timeStamp;
	}

	public int getEmergencyLevel() {
		return this.emergencyLevel;
	}

	public void setEmergencyLevel(int emergencyLevel) {
		this.emergencyLevel = emergencyLevel;
	}

	public String getBloodGroup() {
		return this.bloodGroup;
	}

	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	public String getQueryType() {
		return this.queryType;
	}

	public void setQueryType(String queryType) {
		this.queryType = queryType;
	}

	public int getkVal() {
		return this.kVal;
	}

	public void setkVal(int kVal) {
		this.kVal = kVal;
	}

	public double getRangeVal() {
		return this.rangeVal;
	}

	public void setRangeVal(double rangeVal) {
		this.rangeVal = rangeVal;
	}

	public String getError() {
		return this.error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getToken() {
		return this.token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public List<ResultDBDO> getResultList() {
		return this.resultList;
	}

	public void setResultList(List<ResultDBDO> resultList) {
		this.resultList = resultList;
	}

	public Integer getRequestId() {
		return this.requestId;
	}

	public void setRequestId(Integer requestId) {
		this.requestId = requestId;
	}

}
