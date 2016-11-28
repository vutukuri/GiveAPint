package com.GiveAPint.dto;

import java.util.List;

import com.GiveAPint.persistence.dbdo.AwaitResultDBDO;

public class AwaitResultDTO {
	String token;
	int responderId;
	List<AwaitResultDBDO> resultList;
	String error = "";
	
	public AwaitResultDTO() {
		
	}
	
	/**
	 * @return the responderId
	 */
	public int getResponderId() {
		return this.responderId;
	}

	/**
	 * @param responderId the responderId to set
	 */
	public void setResponderId(int responderId) {
		this.responderId = responderId;
	}

	/**
	 * @return the resultList
	 */
	public List<AwaitResultDBDO> getResultList() {
		return this.resultList;
	}
	
	/**
	 * @param resultList the resultList to set
	 */
	public void setResultList(List<AwaitResultDBDO> resultList) {
		this.resultList = resultList;
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
	
}
