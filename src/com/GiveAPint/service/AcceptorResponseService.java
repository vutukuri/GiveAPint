package com.GiveAPint.service;

import com.GiveAPint.dto.AcceptorDTO;
import com.GiveAPint.dto.UserRequestsDTO;

public interface AcceptorResponseService {
	
	/**
	 * Validates the token and then updates the status of the request and number
	 * of responded users. Inserts the userId along with the requestId in the
	 * acceptors table.
	 * 
	 * @param acceptor
	 *            AcceptorDTO which contains the required info.
	 * @return AcceptorDTO, sets error field incase of any exception encountered or token is invalid. 
	 */
	public AcceptorDTO saveUserResponse(AcceptorDTO acceptor);
	
	/**
	 * Validates the token and returns all the requests made by a user with the given id.
	 * 
	 * @param userid of the user whose requests to be fetched.
	 * @param token which is used to validate the user who made the request.
	 * @return DTO which contains a list of UserStatusDBDO with the essential info regarding the request.
	 */
	public UserRequestsDTO getUserRequests(int userid, String token);

}
