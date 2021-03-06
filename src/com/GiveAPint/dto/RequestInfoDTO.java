package com.GiveAPint.dto;

import java.util.List;

import com.GiveAPint.persistence.dbdo.DonorDBDO;

/**
 * This class is used to map the data that we send to the client when requested for information 
 * regarding a request. It contains the list of accepted users for a request and also their essential
 * information required.
 * 
 * @author mamanoha
 *
 */
public class RequestInfoDTO {

	private int requestId;
	private int requesterId;
	private int totalNumber;
	private int respondedNumber;
	//List of accepted users along with their info which is useful to judge the donor.
	private List<DonorDBDO> acceptedUsers;
	//Message will be set if there are no accepted users at the moment.
	private String message;
	private String error;

	public int getRequestId()
	{
		return this.requestId;
	}

	public void setRequestId(int requestId)
	{
		this.requestId = requestId;
	}

	public int getRequesterId()
	{
		return this.requesterId;
	}

	public void setRequesterId(int requesterId)
	{
		this.requesterId = requesterId;
	}

	public int getTotalNumber()
	{
		return this.totalNumber;
	}

	public void setTotalNumber(int totalNumber)
	{
		this.totalNumber = totalNumber;
	}

	public int getRespondedNumber()
	{
		return this.respondedNumber;
	}

	public void setRespondedNumber(int respondedNumber)
	{
		this.respondedNumber = respondedNumber;
	}

	public List<DonorDBDO> getAcceptedUsers()
	{
		return this.acceptedUsers;
	}

	public void setAcceptedUsers(List<DonorDBDO> acceptedUsers)
	{
		this.acceptedUsers = acceptedUsers;
	}

	public String getError() 
	{
		return this.error;
	}

	public void setError(String error) 
	{
		this.error = error;
	}

	public String getMessage() 
	{
		return this.message;
	}

	public void setMessage(String message)
	{
		this.message = message;
	}

}
