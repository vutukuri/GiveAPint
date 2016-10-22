package com.GiveAPint.persistence.dbdo;

/**
 * This class is responsible for mapping the range and Knn query results.
 * This DBDO is sent to Notification sending server which makes use of this resultantUserId's.s
 * @author mamanoha
 *
 */
public class QueryResultDBDO {
	
	private int resultantUserId;
	private int requestId;
	private String bloodGroup;

	public int getResultantUserId()
	{
		return this.resultantUserId;
	}
	public void setResultantUserId(int resultantUserId)
	{
		this.resultantUserId = resultantUserId;
	}
	public int getRequestId()
	{
		return this.requestId;
	}
	public void setRequestId(int requestId)
	{
		this.requestId = requestId;
	}
	public String getBloodGroup()
	{
		return this.bloodGroup;
	}
	public void setBloodGroup(String bloodGroup)
	{
		this.bloodGroup = bloodGroup;
	}
	
}
