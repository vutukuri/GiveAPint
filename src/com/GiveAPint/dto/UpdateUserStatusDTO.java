package com.GiveAPint.dto;

import java.util.Date;

/**
 * This DTO class is used when the user updates his/her healthStatus and/or lastDonatedDate. 
 * The field "token" is used to validate the user.
 * @author vutukuri
 *
 */

public class UpdateUserStatusDTO {

	private int userid;
	private Date lastDonatedDate;
	private String healthStatus;
	private String token;
	private String error;
	
	public UpdateUserStatusDTO()
	{
		
	}
	
	public UpdateUserStatusDTO(int userid, Date lastDonatedDate, String healthStatus, String token){
		this.userid = userid;
		this.lastDonatedDate = lastDonatedDate;
		this.healthStatus = healthStatus;
		this.token = token;
	}

	/**
	 * @return the userid
	 */
	public int getUserid() {
		return this.userid;
	}

	/**
	 * @param userid the userid to set
	 */
	public void setUserid(int userid) {
		this.userid = userid;
	}

	/**
	 * @return the lastDonatedDate
	 */
	public Date getLastDonatedDate() {
		return this.lastDonatedDate;
	}

	/**
	 * @param lastDonatedDate the lastDonatedDate to set
	 */
	public void setLastDonatedDate(Date lastDonatedDate) {
		this.lastDonatedDate = lastDonatedDate;
	}

	/**
	 * @return the healthStatus
	 */
	public String getHealthStatus() {
		return this.healthStatus;
	}

	/**
	 * @param healthStatus the healthStatus to set
	 */
	public void setHealthStatus(String healthStatus) {
		this.healthStatus = healthStatus;
	}

	/**
	 * @return the token
	 */
	public String getToken() {
		return this.token;
	}

	/**
	 * @param token the token to set
	 */
	public void setToken(String token) {
		this.token = token;
	}

	/**
	 * @return the error
	 */
	public String getError() {
		return this.error;
	}

	/**
	 * @param error the error to set
	 */
	public void setError(String error) {
		this.error = error;
	}
	
}
