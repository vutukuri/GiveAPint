package com.GiveAPint.dto;

/**
 * Used to save the notification registration id corresponding to every user device.
 * This registration id is going to be useful when we target the notifications to certain devices.
 * @author Manu
 *
 */
public class NotificationTokenDTO {

	private String regId;
	private String userName;
	private String error;
	
	public NotificationTokenDTO()
	{	
	}

	public String getRegId()
	{
		return this.regId;
	}

	public void setRegId(String regId)
	{
		this.regId = regId;
	}

	public String getUserName()
	{
		return this.userName;
	}

	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	public String getError()
	{
		return this.error;
	}

	public void setError(String error)
	{
		this.error = error;
	}
	
}
