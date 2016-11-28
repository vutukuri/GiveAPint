package com.GiveAPint.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.GiveAPint.dto.AcceptorDTO;
import com.GiveAPint.dto.RequestInfoDTO;
import com.GiveAPint.dto.UserRequestsDTO;
import com.GiveAPint.persistence.dbdo.DonorDBDO;
import com.GiveAPint.persistence.dbdo.UserRequestsDBDO;
import com.GiveAPint.persistence.mappers.AcceptorMapper;
import com.GiveAPint.persistence.mappers.RequestInfoMapper;
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
	@Autowired
	private RequestInfoMapper requestInfoMapper;

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
		UserRequestsDTO requests = new UserRequestsDTO();
		try
		{
			String userName = userMapper.getUserName(userId);
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
				return requests;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			requests.setError(e.getCause().getMessage());
			return requests;
		}
		requests.setError("");
		return requests;
	}

	@Override
	public RequestInfoDTO getRequestInformation(int requestId, int userId, String token) {
		String userName = userMapper.getUserName(userId);
		RequestInfoDTO result = new RequestInfoDTO();
		try {
			if (loginUserService.validateToken(userName, token)) {
				result = requestInfoMapper.getRequestData(requestId);
				System.out.println("Basic request data:" + result.getRequestId() + " numb:" + result.getTotalNumber()
						+ " resp:" + result.getRespondedNumber() + " requesterid: " + result.getRequesterId());
				List<Integer> acceptors = requestInfoMapper.getAllAcceptors(requestId);
				if (acceptors.size() == 0) {
					// If there are no users who accepted the request.
					// We just set the message and return the DTO. This message
					// can be used to confirm
					// the same.
					result.setMessage(
							"There are no users who accepted the requested," + " Please check back in some time");
					result.setError("");
					return result;
				}
				System.out.println("Got the list of acceptors, size:" + acceptors.size());
				List<DonorDBDO> donors = requestInfoMapper.getDonorsInfo(acceptors, userId);
				for (DonorDBDO donor : donors) {
					System.out.println("Donor Info: userId: " + donor.getUserId() + " firstName: "
							+ donor.getFirstName() + "distance: " + donor.getDistanceInMiles() + " phonenumber:"
							+ donor.getPhoneNumber() + "age " + donor.getAge() + "\n");
				}
				result.setAcceptedUsers(donors);

			} else {
				result.setError("Token not valid, please verify.");
				return result;
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(e.getCause().getMessage());
			return result;
		}
		result.setError("");
		return result;
	}
	
	/**
	 * Delete the corresponding ('requestId', 'responderId') tuple ,responder from the responder table as
	 * he responded to the request.
	 * @param responder
	 * @return
	 */
	public AcceptorDTO removeFromResponders(AcceptorDTO responder)
	{
		return responder;
	}

}
