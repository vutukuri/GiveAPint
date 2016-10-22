package com.GiveAPint.persistence.dbdo;

import java.util.List;

import com.GiveAPint.dto.RequestBloodDTO;

/**
 * This consists of the geo query result, along with the corresponding
 * requestId, and the request [to examine if the error field is set or not]
 * 
 * @author abhi
 *
 */
public class QueryResultSetDBDO {
	private List<ResultDBDO> rawOutput;
	private Integer requestId;
	private RequestBloodDTO request;

	public List<ResultDBDO> getRawOutput() {
		return this.rawOutput;
	}

	public void setRawOutput(List<ResultDBDO> rawOutput) {
		this.rawOutput = rawOutput;
	}

	public int getRequestId() {
		return this.requestId;
	}

	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}

	public RequestBloodDTO getRequest() {
		return this.request;
	}

	public void setRequest(RequestBloodDTO request) {
		this.request = request;
	}

}
