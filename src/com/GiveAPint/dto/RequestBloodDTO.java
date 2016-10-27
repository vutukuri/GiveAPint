package com.GiveAPint.dto;

import java.sql.Timestamp;
import java.util.List;

import com.GiveAPint.persistence.dbdo.ResultDBDO;

import java.util.Date;

import com.GiveAPint.persistence.dbdo.QueryResultDBDO;

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
	private int requestId;
	private Timestamp timeStamp;
	private int emergencyLevel;
	private String bloodGroup;
	private String queryType;
	private int kVal;
	private double rangeVal;
	private String token;
	//TODO Updated upstream
	//private List<ResultDBDO> resultList;
	private List<QueryResultDBDO> queryResult;
	private List<String> bloodDonationTypes;
	private Date currentDate;
	private double longCoord;
	private double latCoord;
	private String error;

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
	// This date is used as an metric to compare the timestamps in order to
	// retrieve the users who are eligible to donate.
	public RequestBloodDTO()
	{
		this.currentDate = new Date();
	}

	public int getUserId()
	{
		return this.userId;
	}

	public void setUserId(int userId)
	{
		this.userId = userId;
	}

	public String getStatus()
	{
		return this.status;
	}

	public void setStatus(String status)
	{
		this.status = status;
	}

	public int getRequestId()
	{
		return this.requestId;
	}

	public void setRequestId(int requestId)
	{
		this.requestId = requestId;
	}

	public Timestamp getTimeStamp()
	{
		return this.timeStamp;
	}

	public void setTimeStamp(Timestamp timeStamp)
	{
		this.timeStamp = timeStamp;
	}

	public int getEmergencyLevel() {
		return this.emergencyLevel;
	}

	public void setEmergencyLevel(int emergencyLevel)
	{
		this.emergencyLevel = emergencyLevel;
	}

	public String getBloodGroup()
	{
		return this.bloodGroup;
	}

	public void setBloodGroup(String bloodGroup)
	{
		this.bloodGroup = bloodGroup;
	}

	public String getQueryType()
	{
		return this.queryType;
	}

	public void setQueryType(String queryType)
	{
		this.queryType = queryType;
	}

	public int getkVal()
	{
		return this.kVal;
	}

	public void setkVal(int kVal)
	{
		this.kVal = kVal;
	}

	public double getRangeVal()
	{
		return this.rangeVal;
	}

	public void setRangeVal(double rangeVal)
	{
		this.rangeVal = rangeVal;
	}

	public String getToken()
	{
		return this.token;
	}

	public void setToken(String token)
	{
		this.token = token;
	}
	
	public List<QueryResultDBDO> getQueryResult()
	{
		return this.queryResult;
	}

	public void setQueryResult(List<QueryResultDBDO> queryResult)
	{
		this.queryResult = queryResult;
	}
	
	public List<String> getBloodDonationTypes()
	{
		return this.bloodDonationTypes;
	}

	public void setBloodDonationTypes(List<String> bloodDonationTypes)
	{
		this.bloodDonationTypes = bloodDonationTypes;
	}
	
	public Date getCurrentDate()
	{
		return this.currentDate;
	}
	
	public double getLongCoord()
	{
		return this.longCoord;
	}

	public void setLongCoord(double longCoord)
	{
		this.longCoord = longCoord;
	}

	public double getLatCoord()
	{
		return this.latCoord;
	}

	public void setLatCoord(double latCoord)
	{
		this.latCoord = latCoord;
	}
	
	public String getError()
	{
		return this.error;
	}

	public void setError(String error)
	{
		this.error = error;
	}

	/**public List<ResultDBDO> getResultList() {
		return this.resultList;
	}

	public void setResultList(List<ResultDBDO> resultList) {
		this.resultList = resultList;
	}*/

}
