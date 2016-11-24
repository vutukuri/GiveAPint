package com.GiveAPint.dto;

/**
 * This DTO class is used while an user logins to the application. The field "token" is used
 * to store the randomly generated token when he logins every time.
 * @author mamanoha
 *
 */
public class LoginUserDTO {

	private String userName;
	private String passcode;
	private String token;
	private String error;
	
	public LoginUserDTO()
	{
		
	}
	
	public LoginUserDTO(String userName, String passcode)
	{
		this.userName = userName;
		this.passcode = passcode;
	}

	public String getUserName()
	{
		return this.userName;
	}
	public void setUserName(String userName)
	{
		this.userName = userName;
	}
	public String getPasscode()
	{
		return this.passcode;
	}
	public void setPasscode(String passcode)
	{
		this.passcode = passcode;
	}
	public String getToken()
	{
		return this.token;
	}
	public void setToken(String token)
	{
		this.token = token;
	}
	
	public String getError() {
		return this.error;
	}

	public void setError(String error) {
		this.error = error;
	}
}
