package com.GiveAPint.persistence.dbdo;

/**
 * This class is used for mapping to the "token" database table.
 * Every user, except the new ones, consist of one tuple which maps their userId to token value.
 * @author mamanoha
 *
 */
public class TokenDBDO {
	
	private String userName;
	private String token;

	public TokenDBDO(String userName, String token)
	{
		this.userName = userName;
		this.token = token;
	}

	public String getUserId()
	{
		return this.userName;
	}
	public void setUserId(String userId)
	{
		this.userName = userId;
	}
	public String getToken()
	{
		return this.token;
	}
	public void setToken(String token)
	{
		this.token = token;
	}

}
