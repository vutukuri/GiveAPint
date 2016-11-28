package com.GiveAPint.dto;

/*
 * Object for AwaitResponse table
 */
public class AwaitResponseDTO {
	int awaitRequestId;
	String token;
	String error = "";
	int awaitRequestorId;
	int awaitResponderId;
	
	public AwaitResponseDTO() {
		
	}
	
	public AwaitResponseDTO(int awaitRequestId, int awaitRequestorId, int awaitResponderId) {
		this.awaitRequestId = awaitRequestId;
		this.awaitRequestorId = awaitRequestorId;
		this.awaitResponderId = awaitResponderId;
	}
	
	/**
	 * @return the awaitRequestId
	 */
	public int getAwaitRequestId() {
		return this.awaitRequestId;
	}
	/**
	 * @param awaitRequestId the awaitRequestId to set
	 */
	public void setAwaitRequestId(int awaitRequestId) {
		this.awaitRequestId = awaitRequestId;
	}
	/**
	 * @return the token
	 */
	public String getToken() {
		return this.token;
	}
	/**
	 * @param token the token to set
	 */
	public void setToken(String token) {
		this.token = token;
	}
	/**
	 * @return the error
	 */
	public String getError() {
		return this.error;
	}
	/**
	 * @param error the error to set
	 */
	public void setError(String error) {
		this.error = error;
	}
	/**
	 * @return the awaitRequestorId
	 */
	public int getAwaitRequestorId() {
		return this.awaitRequestorId;
	}
	/**
	 * @param awaitRequestorId the awaitRequestorId to set
	 */
	public void setAwaitRequestorId(int awaitRequestorId) {
		this.awaitRequestorId = awaitRequestorId;
	}
	/**
	 * @return the awaitResponderId
	 */
	public int getAwaitResponderId() {
		return this.awaitResponderId;
	}
	/**
	 * @param awaitResponderId the awaitResponderId to set
	 */
	public void setAwaitResponderId(int awaitResponderId) {
		this.awaitResponderId = awaitResponderId;
	}
}
