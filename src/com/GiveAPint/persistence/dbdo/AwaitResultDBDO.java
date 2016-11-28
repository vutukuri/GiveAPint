package com.GiveAPint.persistence.dbdo;

public class AwaitResultDBDO {
	String requestorFName;
	String requestorLName;
	String requestedBG;
	int emerLevel;
	double distance;
	int requestId;
	int requestorId;
	
	public AwaitResultDBDO() {
		
	}
	
	public AwaitResultDBDO(int requestId, int requestorId) {
		this.requestId = requestId;
		this.requestorId = requestorId;
	}
	
	public AwaitResultDBDO(String requestorFName, String requestorLName) {
		this.requestorFName = requestorFName;
		this.requestorLName = requestorLName;
	}

	public AwaitResultDBDO(String requestedBG, int emerLevel) {
		this.requestedBG = requestedBG;
		this.emerLevel = emerLevel;
	}

	public AwaitResultDBDO(double distance) {
		this.distance = distance;
	}

	/**
	 * @return the requestorFName
	 */
	public String getRequestorFName() {
		return this.requestorFName;
	}

	/**
	 * @param requestorFName the requestorFName to set
	 */
	public void setRequestorFName(String requestorFName) {
		this.requestorFName = requestorFName;
	}

	/**
	 * @return the requestorLName
	 */
	public String getRequestorLName() {
		return this.requestorLName;
	}

	/**
	 * @param requestorLName the requestorLName to set
	 */
	public void setRequestorLName(String requestorLName) {
		this.requestorLName = requestorLName;
	}

	/**
	 * @return the requestedBG
	 */
	public String getRequestedBG() {
		return this.requestedBG;
	}

	/**
	 * @param requestedBG the requestedBG to set
	 */
	public void setRequestedBG(String requestedBG) {
		this.requestedBG = requestedBG;
	}

	/**
	 * @return the emerLevel
	 */
	public int getEmerLevel() {
		return this.emerLevel;
	}

	/**
	 * @param emerLevel the emerLevel to set
	 */
	public void setEmerLevel(int emerLevel) {
		this.emerLevel = emerLevel;
	}

	/**
	 * @return the distance
	 */
	public double getDistance() {
		return this.distance;
	}

	/**
	 * @param distance the distance to set
	 */
	public void setDistance(double distance) {
		this.distance = distance;
	}

	/**
	 * @return the requestId
	 */
	public int getRequestId() {
		return this.requestId;
	}

	/**
	 * @param requestId the requestId to set
	 */
	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}

	/**
	 * @return the requestorId
	 */
	public int getRequestorId() {
		return this.requestorId;
	}

	/**
	 * @param requestorId the requestorId to set
	 */
	public void setRequestorId(int requestorId) {
		this.requestorId = requestorId;
	}
	
}
