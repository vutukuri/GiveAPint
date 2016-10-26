package com.GiveAPint.service;

<<<<<<< Updated upstream
import java.util.List;
=======
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
>>>>>>> Stashed changes

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.GiveAPint.dto.RequestBloodDTO;
<<<<<<< Updated upstream
import com.GiveAPint.persistence.dbdo.ResultDBDO;
import com.GiveAPint.persistence.mappers.GeoQueryMapper;
import com.GiveAPint.persistence.mappers.UserMapper;

@Service("RequestForBloodService")
public class RequestForBloodServiceImpl implements RequestForBloodService {

	@Autowired
	private UserMapper userMapper;
	@Autowired
	private LoginUserService loginService;
	@Autowired
	private GeoQueryMapper queryMapper;
=======
import com.GiveAPint.persistence.dbdo.LocationDBDO;
import com.GiveAPint.persistence.dbdo.QueryResultDBDO;
import com.GiveAPint.persistence.mappers.LocationMapper;
import com.GiveAPint.persistence.mappers.RequestMapper;
import com.GiveAPint.persistence.mappers.UserMapper;

@Service("RequestForBloodService")
public class RequestForBloodServiceImpl implements RequestForBloodService{
	
	@Autowired
	private LoginUserService loginUserService;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private RequestMapper requestMapper;
	@Autowired
	private LocationMapper locationMapper;
	
	private static Map<String, List<String>> bloodMappings;
	
	
	/**
	 * This function constructs a hash map which stores the eligible blood group type.
	 * We do not create a map for every instance of service, we allocate a static map which is shared
	 * by all instances.
	 * @return
	 */
	private static Map<String, List<String>> constructMap()
	{
		//the values are immutable lists, cannot be changed.
		bloodMappings = new HashMap<>();
		bloodMappings.put("AB+", Arrays.asList("A+", "A-", "AB+", "AB-", "O+", "O-", "B+", "B-"));
		bloodMappings.put("AB-", Arrays.asList("A-","AB-","O-","B-"));
		bloodMappings.put("A+", Arrays.asList("A+", "A-","O+", "O-"));
		bloodMappings.put("A-", Arrays.asList("A-","O-"));
		bloodMappings.put("B+", Arrays.asList("O+", "O-", "B+", "B-"));
		bloodMappings.put("B-", Arrays.asList("O-", "B-"));
		bloodMappings.put("O+", Arrays.asList("O+", "O-"));
		bloodMappings.put("O-", Arrays.asList("O-"));
		return bloodMappings;
	}
>>>>>>> Stashed changes

	private List<String> getDonorsForBloodType(String bloodType)
	{
		if( bloodMappings == null )
		{
			bloodMappings = constructMap();
		}		
		return bloodMappings.get(bloodType);
	}
	
	@Override
	public RequestBloodDTO knnQuery(RequestBloodDTO request) {
		//TODO need to validate the token first. //DONE
		System.out.println("came into the service class");
		System.out.println("User id: "+ request.getUserId() + "token value " + request.getToken());
		String userName = userMapper.getUserName(request.getUserId());
		if( loginUserService.validateToken(userName, request.getToken()) )
		{
			//TODO need to check for error if the list of bloodDonationtypes returned is null.
			//get the list of blood types which can be in the donation list for this request.
			request.setBloodDonationTypes(getDonorsForBloodType(request.getBloodGroup()));
			

				System.out.println("Got the required bloodtypes");
				Integer status = requestMapper.insertRequest(request);
				System.out.println("The return value from the query which inserts the request:"+status);
				System.out.println("Newly inserted request Id is:" +request.getRequestId());
				//TODO need to get the location of the corresponding userId.
				LocationDBDO userCurrentLocation = locationMapper.getUserLocation(request.getUserId());
				request.setLongCoord(userCurrentLocation.getLongCoord());
				request.setLatCoord(userCurrentLocation.getLatCoord());
				List<QueryResultDBDO> results = requestMapper.getKNearestNeighbors(request);
				request.setQueryResult(results);
				System.out.println("The result from the knearest queries are:");
				for(QueryResultDBDO result : results )
				{
					System.out.println("User:" +result.getResultantUserId() + "and his blood group is:" +result.getBloodGroup());
				}
			/**catch(Exception  e)
			{
				System.out.println("Some exception occurred");
				request.setError(e.getLocalizedMessage());
			}**/
			//Need to set the resultant requestID to the DTO so that it can be used by client for further
			//request and to get the info regarding it.
		}
		else
		{
			System.out.println("token is not valid");
			request.setError("Token do not match with the one in the database, please verify");
		}
		
		return request;
	}

	@Override
	public RequestBloodDTO rangeQuery(RequestBloodDTO request) {
		// TODO Auto-generated method stub
		try {
			String userName = userMapper.getUserName(request.getUserId());
			if (loginService.validateToken(userName, request.getToken()) == false) {
				request.setError("Token not matched!");
				return request;
			}
		} catch (Exception e) {
			request.setError(e.getCause().toString());
			return request;
		}

		try {
			queryMapper.insertRequest(request);
		} catch (Exception e) {
			request.setError(e.getCause().toString());
			return request;
		}
		Integer requestId;
		try {
			requestId = queryMapper.getRequestId(request.getUserId());
			request.setRequestId(requestId);
		} catch (Exception e) {
			request.setError(e.getCause().toString());
			return request;
		}
		
		try {
			List<ResultDBDO> resultList = queryMapper.rangeQuery(request);
			request.setResultList(resultList);
		} catch (Exception e) {
			request.setError(e.getCause().toString());
		}

		return request;
	}

}
