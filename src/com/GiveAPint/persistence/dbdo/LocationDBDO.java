package com.GiveAPint.persistence.dbdo;

public class LocationDBDO {
	
	private double latCoord;
	private double longCoord;
	private String bloodGroup;
	private Integer userid;
	
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

	public String getBloodGroup() {
		return this.bloodGroup;
	}

	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	public Integer getUserid() {
		return this.userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}
}
