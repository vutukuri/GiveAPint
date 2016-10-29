package com.GiveAPint.persistence.dbdo;


public class DonorDBDO {

	private String firstName;
	private int age;
	private int userId;
	private String bloodGroup;
	private String phoneNumber;
	private double distanceInMiles;

	public String getFirstName()
	{
		return this.firstName;
	}

	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	public int getAge()
	{
		return this.age;
	}

	public void setDob(int age)
	{
		this.age = age;
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

	public double getDistanceInMiles()
	{
		return this.distanceInMiles;
	}

	public void setDistanceInMiles(double distance)
	{
		this.distanceInMiles = distance;
	}

	public int getUserId()
	{
		return this.userId;
	}

	public void setUserId(int userId)
	{
		this.userId = userId;
	}
	
	
}
