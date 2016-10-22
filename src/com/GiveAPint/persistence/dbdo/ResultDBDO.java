package com.GiveAPint.persistence.dbdo;

/**
 * This is to have the result of the geo query requested by the user.
 * 
 * @author abhi
 *
 */
public class ResultDBDO {

	private int userId;
	private String bloodGroup;

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getBloodGroup() {
		return this.bloodGroup;
	}

	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

}
