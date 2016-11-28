package com.GiveAPint.service;

import java.util.List;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.GiveAPint.dto.AwaitResponseDTO;
import com.GiveAPint.dto.RequestBloodDTO;

import com.GiveAPint.persistence.mappers.UserMapper;
import com.GiveAPint.persistence.dbdo.LocationDBDO;
import com.GiveAPint.persistence.dbdo.QueryResultDBDO;
import com.GiveAPint.persistence.mappers.AcceptorMapper;
import com.GiveAPint.persistence.mappers.LocationMapper;
import com.GiveAPint.persistence.mappers.RequestMapper;

@Service("RequestForBloodService")
public class RequestForBloodServiceImpl implements RequestForBloodService {

	@Autowired
	private LoginUserService loginUserService;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private RequestMapper requestMapper;
	@Autowired
	private LocationMapper locationMapper;
	@Autowired
	private AcceptorMapper acceptorMapper;

	private static Map<String, List<String>> bloodMappings;

	/**
	 * This function constructs a hash map which stores the eligible blood group
	 * type. We do not create a map for every instance of service, we allocate a
	 * static map which is shared by all instances.
	 * 
	 * @return
	 */
	private static Map<String, List<String>> constructMap() {
		// the values are immutable lists, cannot be changed.
		bloodMappings = new HashMap<>();
		bloodMappings.put("AB+", Arrays.asList("A+", "A-", "AB+", "AB-", "O+", "O-", "B+", "B-"));
		bloodMappings.put("AB-", Arrays.asList("A-", "AB-", "O-", "B-"));
		bloodMappings.put("A+", Arrays.asList("A+", "A-", "O+", "O-"));
		bloodMappings.put("A-", Arrays.asList("A-", "O-"));
		bloodMappings.put("B+", Arrays.asList("O+", "O-", "B+", "B-"));
		bloodMappings.put("B-", Arrays.asList("O-", "B-"));
		bloodMappings.put("O+", Arrays.asList("O+", "O-"));
		bloodMappings.put("O-", Arrays.asList("O-"));
		return bloodMappings;
	}

	private List<String> getDonorsForBloodType(String bloodType) {
		if (bloodMappings == null) {
			bloodMappings = constructMap();
		}
		return bloodMappings.get(bloodType);
	}

	@Override
	public RequestBloodDTO knnQuery(RequestBloodDTO request) {
		// TODO need to validate the token first. //DONE
		System.out.println("came into the service class, knnquery");
		System.out.println("User id: " + request.getUserId() + "token value " + request.getToken());
		try {
			String userName = userMapper.getUserName(request.getUserId());
			if (loginUserService.validateToken(userName, request.getToken())) {
				// get the list of blood types which can be in the donation list
				// for this request.
				request.setBloodDonationTypes(getDonorsForBloodType(request.getBloodGroup()));
				System.out.println("Got the required bloodtypes");
				LocationDBDO userCurrentLocation = locationMapper.getUserLocation(request.getUserId());
				request.setLongCoord(userCurrentLocation.getLongCoord());
				request.setLatCoord(userCurrentLocation.getLatCoord());
				System.out.println("Current date received:" + request.getCurrentDate());
				System.out.println(
						"The location of the user passed:" + request.getLongCoord() + " " + request.getLatCoord());
				List<QueryResultDBDO> results = requestMapper.getKNearestNeighbors(request);
				request.setQueryResult(results);
				request.setTotalNumber(results.size());
				// Before inserting also, include the number of returned users
				// from the query.
				Integer status = requestMapper.insertRequest(request);
				System.out.println("The return value from the query which inserts the request:" + status);
				System.out.println("Newly inserted request Id is:" + request.getRequestId());
				System.out.println("The size of the results are:" + results.size());
				for (QueryResultDBDO result : results) {
					System.out.println("User:" + result.getResultantUserId() + "and his blood group is:"
							+ result.getBloodGroup() + " " + "at a distance:" + result.getDistance());
				}
				// Need to set the resultant requestID to the DTO so that it can
				// be used by client for further
				// request and to get the info regarding it.
			} else {
				System.out.println("token is not valid");
				request.setError("Token do not match with the one in the database, please verify");
				return request;
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setError(e.getCause().getMessage());
			return request;
		}
		request.setError("");
		return request;
	}

	@Override
	public RequestBloodDTO rangeQuery(RequestBloodDTO request) {
		System.out.println("came into the service class, rangeQuery");
		System.out.println("User id: " + request.getUserId() + "token value " + request.getToken());
		try {
			String userName = userMapper.getUserName(request.getUserId());
			if (loginUserService.validateToken(userName, request.getToken())) {
				// get the list of blood types which can be in the donation list
				// for this request.
				request.setBloodDonationTypes(getDonorsForBloodType(request.getBloodGroup()));
				System.out.println("Got the required bloodtypes");
				LocationDBDO userCurrentLocation = locationMapper.getUserLocation(request.getUserId());
				request.setLongCoord(userCurrentLocation.getLongCoord());
				request.setLatCoord(userCurrentLocation.getLatCoord());
				System.out.println("Current date and timestamp received:" + request.getCurrentDate() + " "+ request.getTimeStamp());
				System.out.println(
						"The location of the user passed:" + request.getLongCoord() + " " + request.getLatCoord());
				List<QueryResultDBDO> results = requestMapper.rangeQuery(request);
				request.setQueryResult(results);
				request.setTotalNumber(results.size());
				// Before inserting also, include the number of returned users
				// from the query.
				Integer status = requestMapper.insertRequest(request);
				System.out.println("The return value from the query which inserts the request:" + status);
				System.out.println("Newly inserted request Id is:" + request.getRequestId());
				System.out.println("The size of the results are:" + results.size());
				for (QueryResultDBDO result : results) {
					System.out.println("User:" + result.getResultantUserId() + "and his blood group is:"
							+ result.getBloodGroup() + " " + "at a distance:" + result.getDistance());
				}
				/**
				 * catch(Exception e) { System.out.println(
				 * "Some exception occurred");
				 * request.setError(e.getLocalizedMessage()); }
				 **/
				// Need to set the resultant requestID to the DTO so that it can
				// be used by client for further
				// request and to get the info regarding it.
			} else {
				System.out.println("token is not valid");
				request.setError("Token do not match with the one in the database, please verify");
				return request;
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setError(e.getCause().getMessage());
			return request;
		}
		request.setError("");
		return request;
	}
	
	/**
	 * Saves data into the responders table. Only thing to check, if result size is 0.
	 * @param request
	 * @return
	 */
	public RequestBloodDTO saveIntoResponders(RequestBloodDTO request)
	{
		if (request.getQueryResult().isEmpty() == false) {
			List<QueryResultDBDO> queryResults = request.getQueryResult();
			AwaitResponseDTO awaitResponse = new AwaitResponseDTO();
			awaitResponse.setAwaitRequestId(request.getRequestId());
			awaitResponse.setAwaitRequestorId(request.getUserId());
			for (QueryResultDBDO result : queryResults) {
				awaitResponse.setAwaitResponderId(result.getResultantUserId());
				try {
					acceptorMapper.insertAwaitResponse(awaitResponse);
				} catch (Exception e) {
					request.setError(e.getMessage());
					System.out.println("Error occurred while inserting into awaitresponses\n\t\t\t" + e.getMessage());
					break;
				}
			}
		}
		
		return request;
	}
	
}
