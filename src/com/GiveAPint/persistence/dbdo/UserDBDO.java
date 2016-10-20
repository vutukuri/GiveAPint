package com.GiveAPint.persistence.dbdo;

import java.util.Date;

import com.GiveAPint.dto.UserDTO;

/**
 * This class maps to the "loginusers" table in the data base. Used by the
 * mapper classes and it's fields are accessed while performing CRUD operations.
 * 
 * @author mamanoha
 *
 */
public class UserDBDO {

	private String firstName;
	private String lastName;
	private int userId;
	private String passcode;
	private String userName;
	private String phone;
	private Date dob;
	private String gender;
	private String error;

	public UserDBDO(){
		
	}
		
	/**
	 * Sets everything except userId which is returned only after inserting the user into the database.
	 * @param user
	 */
	public UserDBDO(UserDTO user)
	{
		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
		this.passcode = user.getPasscode();
		this.userName = user.getUserName();
		this.phone = user.getPhone();
		this.dob = user.getDob();
		this.gender = user.getGender();
	}
	public String getFirstName() {
		return firstName;
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
	public int getUserId() {
		return this.userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getPasscode() {
		return passcode;
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
	public String getError() {
		return this.error;
	}
	public void setError(String error) {
		this.error = error;
	}

	
}
