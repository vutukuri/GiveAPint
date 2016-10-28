package com.GiveAPint.persistence.dbdo;

import java.util.Date;

public class DonorDBDO {

	private String firstName;
	private Date dob;
	private int userId;
	private String bloodGroup;
	private String phoneNumber;
	private double distance;

	public String getFirstName()
	{
		return this.firstName;
	}

	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	public Date getDob()
	{
		return this.dob;
	}

	public void setDob(Date dob)
	{
		this.dob = dob;
	}

	public int getUserId()
	{
		return this.userId;
	}

	public void setUserId(int userId)
	{
		this.userId = userId;
	}

	public String getBloodGroup()
	{
		return this.bloodGroup;
	}

	public void setBloodGroup(String bloodGroup)
	{
		this.bloodGroup = bloodGroup;
	}

	public String getPhoneNumber()
	{
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber)
	{
		this.phoneNumber = phoneNumber;
	}

	public double getDistance()
	{
		return this.distance;
	}

	public void setDistance(double distance)
	{
		this.distance = distance;
	}
	
	
}
