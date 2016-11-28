package com.GiveAPint.service;

import com.GiveAPint.dto.RequestBloodDTO;

public interface RequestForBloodService {

	public RequestBloodDTO knnQuery(RequestBloodDTO request);
	
	public RequestBloodDTO rangeQuery(RequestBloodDTO request);
	
	public RequestBloodDTO saveIntoResponders(RequestBloodDTO request);

}
