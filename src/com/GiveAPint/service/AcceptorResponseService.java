package com.GiveAPint.service;

import com.GiveAPint.dto.AcceptorDTO;
import com.GiveAPint.dto.AwaitResultDTO;
import com.GiveAPint.dto.RequestInfoDTO;
import com.GiveAPint.dto.UserRequestsDTO;

public interface AcceptorResponseService {
	
	/**
	 * Validates the token and then updates the status of the request and number
	 * of responded users. Inserts the userId along with the requestId in the
	 * acceptors table.
	 * 
	 * @param acceptor
	 *            AcceptorDTO which contains the required info.
	 * @return AcceptorDTO, sets error field in case of any exception encountered or token is invalid. 
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
	
	/**
	 * This method fetches all the accepted users for a given request along with some other essential
	 * information which helps the requester to take a decision on contacting a particular donor.
	 * @param requesId of the request
	 * @param userId of the requester
	 * @param token which is used to validate the requester
	 * @return RequestInfoDTO which contains the list of accepted users along with their information.
	 */
	public RequestInfoDTO getRequestInformation(int requesId, int userId, String token);
	
	/*
	 * Removes responded users from the awaitresponses table. 
	 */
	public AcceptorDTO removeFromResponders(AcceptorDTO responder);
	
	/*
	 * Fetches all the requests, and their details that are awaiting responder's response.
	 */
	public AwaitResultDTO fetchAwaitList(AwaitResultDTO responder);
}
