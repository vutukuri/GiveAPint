package com.GiveAPint.persistence.dbdo;

import java.util.Date;
import org.postgis.Point;

import com.GiveAPint.dto.UserDTO;

public class UserStatusDBDO {
	
	private int userId;
	private String healthStatus;
	private Date nextAvailableDate;
	private Point lastLocation;
	private String bloodGroup;
	
	public UserStatusDBDO(UserDTO user)
	{
		this.userId = user.getUserID();
		this.healthStatus = user.getHealthStatus();
		this.nextAvailableDate = user.getDob();
		this.lastLocation = user.getLastLocation();
		this.bloodGroup = user.getBloodGroup();
	}

	public int getUserId() {
		return this.userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getHealthStatus() {
		return this.healthStatus;
	}
	public void setHealthStatus(String healthStatus) {
		this.healthStatus = healthStatus;
	}
	public Date getNextAvailableDate() {
		return this.nextAvailableDate;
	}
	public void setNextAvailableDate(Date nextAvailableDate) {
		this.nextAvailableDate = nextAvailableDate;
	}
	public Point getLastLocation() {
		return this.lastLocation;
	}
	public void setLastLocation(Point lastLocation) {
		this.lastLocation = lastLocation;
	}
	public String getBloodGroup() {
		return this.bloodGroup;
	}
	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}
}
