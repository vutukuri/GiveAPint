package com.GiveAPint.persistence.dbdo;

/**
 * Maps to the "requests" table in the database. All the requests corresponding
 * to a user can be binded into list of this class type.
 * @author mamanoha
 *
 */
public class UserRequestsDBDO {
	
	
	private int requestId;
	private String requestedBloodType;
	private String status;
	private int emergencyLevel;
	//TimeStamp will be returned in the form of milliseconds, and the client can
	//display it in the required format.
	private long timestamp;

	public int getRequestId()
	{
		return this.requestId;
	}

	public void setRequestId(int requestId)
	{
		this.requestId = requestId;
	}

	public String getRequestedBloodType()
	{
		return this.requestedBloodType;
	}

	public void setRequestedBloodType(String requestedBloodType)
	{
		this.requestedBloodType = requestedBloodType;
	}

	public String getStatus()
	{
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getEmergencyLevel()
	{
		return this.emergencyLevel;
	}

	public void setEmergencyLevel(int emergencyLevel)
	{
		this.emergencyLevel = emergencyLevel;
	}

	public long getTimestamp()
	{
		return this.timestamp;
	}

	public void setTimestamp(long timestamp)
	{
		this.timestamp = timestamp;
	}

}
