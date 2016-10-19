package com.GiveAPint.dto;

import java.util.Date;

import org.postgis.Point;

/**
 * USERDTO encapsulates the data corresponding to a user while he is registering
 * for the first time. This data is spanned across two database tables.
 * 
 * @author mamanoha
 *
 */
public class UserDTO {

	private String firstName;
	private String lastName;
	private int userID;
	private String passcode;
	private String userName;
	private String phone;
	private Date dob;
	private String gender;
	private String healthStatus;
	private Date nextAvailabeDate;
	private Point lastLocation;
	private String bloodGroup;
	private String error;

	public UserDTO() 
	{
	}
	
	public UserDTO(String firstName, String lastName, String passcode, String userName, String phone, Date dob,
			String gender, String healthStatus, Date nextAvaiableDate, Point lastLocation, String bloodGroup)
	{
		this.firstName = firstName;
		this.lastName = lastName;
		this.passcode = passcode;
		this.userName = userName;
		this.phone = phone;
		this.dob = dob;
		this.gender = gender;
		this.healthStatus = healthStatus;
		this.nextAvailabeDate = nextAvaiableDate;
		this.lastLocation = lastLocation;
		this.bloodGroup = bloodGroup;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getUserID() {
		return this.userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getPasscode() {
		return this.passcode;
	}

	public void setPasscode(String passcode) {
		this.passcode = passcode;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getDob() {
		return this.dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getHealthStatus() {
		return this.healthStatus;
	}

	public void setHealthStatus(String healthStatus) {
		this.healthStatus = healthStatus;
	}

	public Date getNextAvailabeDate() {
		return this.nextAvailabeDate;
	}

	public void setNextAvailabeDate(Date nextAvailabeDate) {
		this.nextAvailabeDate = nextAvailabeDate;
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
	
	public String getError() {
		return this.error;
	}

	public void setError(String error) {
		this.error = error;
	}
}
