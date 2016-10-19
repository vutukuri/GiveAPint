package com.GiveAPint.dto;


/**
 * Used for location updation, and get all locations from the database.
 * 
 * @author abhi
 *
 */

public class LocationDTO {

	private Integer userid;
	private double latCoord;
	private double longCoord;
	private String error;
	private String token;

	public LocationDTO(Integer userid, double latCoord, double longCoord, String error, String token) {
		this.userid = userid;
		this.latCoord = latCoord;
		this.longCoord = longCoord;
		this.error = error;
		this.token = token;
	}

	public Integer getUserid() {
		return this.userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public double getLatCoord() {
		return this.latCoord;
	}

	public void setLatCoord(double latCoord) {
		this.latCoord = latCoord;
	}

	public double getLongCoord() {
		return this.longCoord;
	}

	public void setLongCoord(double longCoord) {
		this.longCoord = longCoord;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
