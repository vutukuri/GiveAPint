package com.GiveAPint.dto;

import java.util.List;

import com.GiveAPint.persistence.dbdo.UserRequestsDBDO;

/**
 * Maps the request info along with the error field. This class maps the data
 * that is sent to the user.
 * 
 * @author mamanoha
 *
 */
public class UserRequestsDTO {
	
	private List<UserRequestsDBDO> requests;
	private int userId;
	private String error;
	
	public UserRequestsDTO()
	{
		
	}

	public List<UserRequestsDBDO> getRequests()
	{
		return this.requests;
	}

	public void setRequests(List<UserRequestsDBDO> requests)
	{
		this.requests = requests;
	}

	public int getUserId()
	{
		return this.userId;
	}

	public void setUserId(int userId)
	{
		this.userId = userId;
	}

	public String getError() {
		return this.error;
	}

	public void setError(String error)
	{
		this.error = error;
	}

}
