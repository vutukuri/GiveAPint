package com.GiveAPint.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.GiveAPint.dto.AcceptorDTO;
import com.GiveAPint.persistence.mappers.AcceptorMapper;
import com.GiveAPint.persistence.mappers.UserMapper;

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

}
