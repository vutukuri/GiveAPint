package com.GiveAPint.persistence.dbdo;


/**
 * This class is responsible for mapping the range and Knn query results.
 * This DBDO is sent to Notification sending server which makes use of this resultantUserId's.s
 * @author mamanoha
 *
 */
public class QueryResultDBDO {
	
	private int resultantUserId;
	private String bloodGroup;
	private double distance;
	private long times;
	
	public QueryResultDBDO()
	{
		
	}

	public int getResultantUserId()
	{
		return this.resultantUserId;
	}
	
	public void setResultantUserId(int resultantUserId)
	{
		this.resultantUserId = resultantUserId;
	}

	public String getBloodGroup()
	{
		return this.bloodGroup;
	}
	
	public void setBloodGroup(String bloodGroup)
	{
		this.bloodGroup = bloodGroup;
	}
	
	public double getDistance()
	{
		return this.distance;
	}
	
	public void setDistance(double distance)
	{
		this.distance = distance;
	}
	
	public long getTimes()
	{
		return this.times;
	}
	
	public void setTimes(long times)
	{
		this.times = times;
	}
	
}
