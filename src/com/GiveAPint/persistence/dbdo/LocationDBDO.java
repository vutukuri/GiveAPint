package com.GiveAPint.persistence.dbdo;

import com.GiveAPint.dto.LocationDTO;

public class LocationDBDO {

	private Integer userid;
	private Double latCoord;
	private Double longCoord;
	
	public LocationDBDO()
	{
		
	}
	public LocationDBDO(Integer userid, double latCoord, double longCoord){
		this.userid = userid;
		this.latCoord = latCoord;
		this.longCoord = longCoord;
	}
	public LocationDBDO(LocationDTO location) {
		this.userid = location.getUserid();
		this.latCoord = location.getLatCoord();
		this.longCoord = location.getLongCoord();
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

}
