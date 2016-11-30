package com.GiveAPint.dto;

public class GetRequestsForUserDTO {
	
	private int userId;
	private String token;
	
	public GetRequestsForUserDTO()
	{
		
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
