package com.GiveAPint.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.GiveAPint.dto.AcceptorDTO;
import com.GiveAPint.dto.RequestInfoDTO;
import com.GiveAPint.dto.UserRequestsDTO;
import com.GiveAPint.persistence.dbdo.UserRequestsDBDO;
import com.GiveAPint.persistence.mappers.AcceptorMapper;
import com.GiveAPint.persistence.mappers.UserMapper;

/**
 * This class is responsible to update the request status, acceptor list
 * corresponding to a request and also fetches the appropriate info pertained to
 * the request.
 * 
 * @author mamanoha
 *
 */
@Service("AcceptorResponseService")
public class AcceptorResponseServiceImpl implements AcceptorResponseService {
	
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private LoginUserService loginUserService;
	@Autowired
	private AcceptorMapper acceptorMapper;

	@Override
	public AcceptorDTO saveUserResponse(AcceptorDTO acceptor) {
		// TODO Auto-generated method stub
		String userName = userMapper.getUserName(acceptor.getUserId());
		if( loginUserService.validateToken(userName, acceptor.getToken()) )
		{
			int updateStatus = acceptorMapper.updateRequestStatus(acceptor.getRequestId(), acceptor.getResponse());
			System.out.println("update statement returned with status:" +updateStatus);
			if( acceptor.getResponse().equals("Accept"))
			{
				int insertStatus = acceptorMapper.insertAcceptor(acceptor);
				System.out.println("Inserting donor returned with status:" +insertStatus);
			}
			else 
			{
				System.out.println("User do not want to donate the blood for this request:" + acceptor.getResponse());
			}
			//2) update the request status and the number of respondedusers in requests table.
			//3) insert a tuple if the response is "Accept" or else just ignore.
			//4) Return the DTO with error message set, if any.
		}
		else
		{
			acceptor.setError("Token corresponding to user is not valid, please verify.");
		}
		return acceptor;
	}

	@Override
	public UserRequestsDTO getUserRequests(int userId, String token) {
		// TODO Auto-generated method stub
		String userName = userMapper.getUserName(userId);
		UserRequestsDTO requests = new UserRequestsDTO();
		requests.setUserId(userId);
		if( loginUserService.validateToken(userName, token) )
		{
			List<UserRequestsDBDO> results = acceptorMapper.getAllRequests(userId);
			requests.setRequests(results);
			System.out.println("The requests which are made by the user:" +userId);
			for(UserRequestsDBDO request : results )
			{
				System.out.println("Request id:" +request.getRequestId() + ", timestamp" + request.getTimestamp());
			}
		}
		else
		{
			requests.setError("Token Invalid, please verify.");
		}
		return requests;
	}

	@Override
	public RequestInfoDTO getRequestInformation(int requesId, int userId, String token) {
		// TODO Auto-generated method stub
		return null;
	}

}
