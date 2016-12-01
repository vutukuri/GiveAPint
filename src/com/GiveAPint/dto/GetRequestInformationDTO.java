package com.GiveAPint.dto;

public class GetRequestInformationDTO {

	private int requestId;
	private int userId;
	private String token;
	
	public GetRequestInformationDTO()
	{
		
	}

	public int getRequestId() {
		return requestId;
	}

	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	
}
