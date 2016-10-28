package com.GiveAPint.dto;

/**
 * AcceptorDTO is used to map the data when user responds to the request sent
 * through notification. His response is stored in the "response" field.
 * 
 * @author mamanoha
 *
 */
public class AcceptorDTO {
	
	private int requestId;
	private int userId;
	private String token;
	private String response;
	private String error;
	
	public AcceptorDTO(int requestId, int userId, String token, String response)
	{
		this.requestId = requestId;
		this.userId = userId;
		this.token = token;
		this.response = response;
	}

	public int getRequestId()
	{
		return this.requestId;
	}

	public void setRequestId(int requestId)
	{
		this.requestId = requestId;
	}

	public int getUserId()
	{
		return this.userId;
	}

	public void setUserId(int userId)
	{
		this.userId = userId;
	}

	public String getToken()
	{
		return this.token;
	}

	public void setToken(String token)
	{
		this.token = token;
	}

	public String getResponse()
	{
		return this.response;
	}

	public void setResponse(String response)
	{
		this.response = response;
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
