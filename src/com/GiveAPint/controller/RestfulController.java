package com.GiveAPint.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.GiveAPint.constants.ProjectConstants;
import com.GiveAPint.dto.AcceptorDTO;
import com.GiveAPint.dto.AwaitResultDTO;
import com.GiveAPint.dto.LocationDTO;
import com.GiveAPint.dto.LoginUserDTO;
import com.GiveAPint.dto.NotificationTokenDTO;
import com.GiveAPint.dto.RequestBloodDTO;
import com.GiveAPint.dto.RequestInfoDTO;
import com.GiveAPint.dto.UpdateUserStatusDTO;
import com.GiveAPint.dto.UserDTO;
import com.GiveAPint.dto.UserRequestsDTO;
import com.GiveAPint.persistence.dbdo.LocationDBDO;
import com.GiveAPint.persistence.dbdo.QueryResultDBDO;
import com.GiveAPint.persistence.dbdo.UserDBDO;
import com.GiveAPint.persistence.mappers.NotificationMapper;
import com.GiveAPint.persistence.mappers.UserMapper;
import com.GiveAPint.service.AcceptorResponseService;
import com.GiveAPint.service.LoginUserService;
import com.GiveAPint.service.RegisterUserService;
import com.GiveAPint.service.RequestForBloodService;
import com.GiveAPint.service.UpdateStatusService;
import com.GiveAPint.service.UserLocationFetchService;
import com.GiveAPint.service.UserLocationUpdateService;
import com.GiveAPint.testdata.CreateObjects;

/**
 * This controller class handles all the incoming requests from the client and
 * triggers appropriate business logic. This do not contain any business logic.
 * 
 * @author mamanoha
 *
 */

@Controller(value = "/GiveAPint")
public class RestfulController {

	@Autowired
	private UserMapper userMapper;
	@Autowired
	private NotificationMapper notificationMapper;
	@Autowired
	private CreateObjects createSampleObjects;
	@Autowired
	private RegisterUserService registerService;
	@Autowired
	private LoginUserService loginService;
	@Autowired
	private UserLocationFetchService locationService;
	@Autowired
	private UpdateStatusService updateService;
	@Autowired
	private UserLocationUpdateService locationUpdateService;
	@Autowired
	private RequestForBloodService requestBloodService;
	@Autowired
	private AcceptorResponseService acceptorResponseService;

	/**
	 * Register an user.
	 * 
	 * @return
	 */
	@RequestMapping(value = "/registerUser", headers = "Accept=application/json", method = RequestMethod.GET)
	public @ResponseBody UserDTO registerUser(@ModelAttribute UserDTO user) {
		// UserDTO user = createSampleObjects.createUser();
		System.out.println("Successfully triggered the controller method.");
		user = registerService.insertUser(user);
		System.out.println("The userId of newly created user is:" + user.getUserID());
		return user;
	}

	/**
	 * Validate the user credentials.
	 * 
	 * @return loginUserDTO to which a token is attached.
	 */
	@RequestMapping(value = "/loginUser", headers = "Accept=application/json", method = RequestMethod.GET)
	public @ResponseBody LoginUserDTO loginUser(@ModelAttribute LoginUserDTO user) {
		// LoginUserDTO user = createSampleObjects.createLoginUser();
		user = loginService.validateUser(user);
		System.out.println("Login user status(null if no error):" + user.getError());
		System.out.println("Token that is generated:" + user.getToken());
		return user;
	}

	@RequestMapping(value = "/validateTokenOfUser")
	public ModelAndView validateToken() {

		boolean isValidToken = loginService.validateToken(ProjectConstants.userName, "dd7c16992dhfa");
		System.out.println("Is token a  valid one:" + isValidToken);
		return new ModelAndView("login");
	}

	@RequestMapping(value = "/getAllUsers", headers = "Accept=application/json", method = RequestMethod.GET)
	public @ResponseBody List<UserDBDO> getAllUsers() {
		List<UserDBDO> users = userMapper.getAllUsers();
		System.out.println("The number of users as of now:" + users.size());
		for (UserDBDO user : users) {
			// Print the values corresponding to the user.
			// TODO need to print the key value pairs.
			System.out.println("User info:  " + user.getFirstName() + "  " + user.getLastName() + "  "
					+ user.getUserId() + "  " + user.getUserName() + "  " + user.getPasscode());
		}
		return users;
	}

	@RequestMapping(value = "/getAllLocations", headers = "Accept=application/json", method = RequestMethod.GET)
	public @ResponseBody List<LocationDBDO> getAllLocations() {

		List<LocationDBDO> locations = locationService.getAllLocations();
		if (locations != null) {
			System.out.println("Prints all the locations from the database now. (Lat, Long)");
			for (LocationDBDO location : locations) {

				System.out.println("UserID:" + location.getUserid() + " Lat:" + location.getLatCoord() + " Long:"
						+ location.getLongCoord() + "\n");

			}

		}
		return locations;

	}

	@RequestMapping(value = "/updateStatus")
	public ModelAndView updateStatus() {
		UpdateUserStatusDTO user = createSampleObjects.updateStatus();
		UpdateUserStatusDTO update = updateService.UpdateUserStatus(user);
		System.out.println("Updation status(null if no error):" + update.getError());
		return new ModelAndView("updateStatus");
	}

	@RequestMapping(value = "/updateLocation", headers = "Accept=application/json", method = RequestMethod.GET)
	public @ResponseBody LocationDTO updateLocation(@ModelAttribute LocationDTO newLocation) {

		// LocationDTO newLocation = createSampleObjects.createUpdateLocation();
		try {
			newLocation = locationUpdateService.locationUpdate(newLocation);
			if (newLocation.getError() == "" || newLocation.getError() == null) {
				System.out.println("Location Updated!  " + newLocation.getUserid() + " " + newLocation.getLatCoord()
						+ " " + newLocation.getLongCoord());
			} else {
				System.out.println("Location Updation Error" + newLocation.getError());
			}
		} catch (Exception e) {
			System.out.println("Exception occured while mapping the map values to DTO in controller");
			e.printStackTrace();
		}
		return newLocation;
	}

	@RequestMapping(value = "/requestBlood", headers = "Accept=application/json", method = RequestMethod.GET)
	public @ResponseBody RequestBloodDTO requestForDonation(@ModelAttribute RequestBloodDTO request) {
		// The argument should be "KnnQuery" or "RangeQuery"
		// RequestBloodDTO request =
		// createSampleObjects.createRequest("RangeQuery");
		try {
			if (request.getQueryType().equals("RangeQuery")) {
				request = requestBloodService.rangeQuery(request);
				if (request.getError() == "" || request.getError() == null) {
					System.out.println("Range Query results for the request Id: " + request.getRequestId()
							+ "\n, for the bloodGroup: " + request.getBloodGroup() + "\n, and for the userId: "
							+ request.getUserId() + " are");
					for (QueryResultDBDO result : request.getQueryResult()) {
						System.out.println(
								"UserId: " + result.getResultantUserId() + ", BloodGroup: " + result.getBloodGroup());
					}
				} else {
					System.out.println("Performing range query resulted in an error:: please check for the details::   "
							+ request.getError());
				}
			} else if (request.getQueryType().equals("KnnQuery")) {
				request = requestBloodService.knnQuery(request);
				System.out.println("Succesfully executed knn");
				// TODO Print the result set here
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setError(e.getCause().getMessage());
		}
		// Check if error is set.
		// No error 'error.equals("")' - Insert results into new table.
		// Error - return
		if (request.getError().equals("") == true) {
			request = requestBloodService.saveIntoResponders(request);
		}
		
		return request;
	}

	@RequestMapping(value = "/respondToRequest", headers = "Accept=application/json", method = RequestMethod.GET)
	public @ResponseBody AcceptorDTO respondToRequest(@ModelAttribute AcceptorDTO acceptor) {
		// AcceptorDTO acceptor = createSampleObjects.createAcceptor();
		try {
			System.out.println("Controller for responding triggered: received credentials are: " +acceptor.getRequestId()+ 
					" " + acceptor.getResponse() + " " + acceptor.getToken() + " "+ acceptor.getUserId());
			acceptor = acceptorResponseService.saveUserResponse(acceptor);
			if (acceptor.getError() == null || acceptor.getError().equals(""))
			{
				System.out.println("Acceptor response saved succesfully.");
				acceptor = acceptorResponseService.removeFromResponders(acceptor);
				if (acceptor.getError() != null && acceptor.getError().equals("") == false) 
				{
					System.out.println("Error occurred while removing entry from awaitresponse table");
					return acceptor;
				} 
				else 
				{
					System.out.println("Corresponding entry removed successfully from awaitresponse table");
				}

			}
			else
			{
				System.out.println("Error occurred while saving the user response");
				return acceptor;
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			acceptor.setError(e.getCause().getMessage());
			return acceptor;
		}
		// Make a call to acceptorService.removeFromResponders, add any
		// additional test cases.
		acceptor.setError("");
		return acceptor;
	}

	@RequestMapping(value = "/getRequestsForUser", method = RequestMethod.GET)
	public @ResponseBody UserRequestsDTO getRequestsMadeByUser(@RequestParam("userId") int userId,
			@RequestParam("token") String token) {
		UserRequestsDTO result = new UserRequestsDTO();
		try
		{
			System.out.println("Token received for the requests data: "+token);
			result = acceptorResponseService.getUserRequests(userId, token);
			System.out.println("Returned Succesfully from the service class with all the requests list:");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			result.setError(e.getCause().getMessage());
		}
		return result;
	}

	/**
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getRequestInfo", headers = "Accept=application/json", method = RequestMethod.GET)
	public @ResponseBody RequestInfoDTO getRequestInformation(@RequestParam("requestId") int requestId,
			@RequestParam("userId") int userId, @RequestParam("token") String token)
	{
		// This would be a GET call with requestid, userid, and token as
		// arguments.
		// requestId, userId, token
		RequestInfoDTO requestInfo = new RequestInfoDTO();
		try
		{
		requestInfo = acceptorResponseService.getRequestInformation(requestId, userId, token);
		System.out.println("Service call returned succesfully fetching the requestInfo");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			requestInfo.setError(e.getCause().getMessage());
			return requestInfo;
		}
		return requestInfo;
	}

	@RequestMapping(value = "/registerNotificationToken", headers = "Accept=application/json", method = RequestMethod.GET)
	public @ResponseBody NotificationTokenDTO registerNotificationToken(
			@ModelAttribute NotificationTokenDTO userRegDTO) {
		System.out.println("Came inside the controller method");
		// see if there is already a tuple existing of the user, if yes update,
		// else insert.
		int status = 0;
		try {
			String currentRegId = notificationMapper.getNotificationToken(userRegDTO.getUserName());
			if (currentRegId == null || currentRegId.equals("")) {
				System.out.println("Inserting notification token for the first time");
				status = notificationMapper.insertNotificationToken(userRegDTO.getUserName(), userRegDTO.getRegId());
			} else {
				System.out.println("Updating the notifications token for the current user");
				status = notificationMapper.updateNotificationToken(userRegDTO.getUserName(), userRegDTO.getRegId());

			}
			if (status == 0) {
				userRegDTO.setError("Insert/update operation not successful");
				return userRegDTO;
			}
		} catch (Exception e) {
			e.printStackTrace();
			userRegDTO.setError(e.getCause().getMessage());
			System.out.println("Exception Occurred while updating the notification token" + "for the user: "
					+ userRegDTO.getUserName());
			return userRegDTO;

		}
		userRegDTO.setError("");
		return userRegDTO;

	}
	
	@RequestMapping(value = "/generateAwaitResults", headers = "Accept=application/json", method = RequestMethod.GET)
	public @ResponseBody AwaitResultDTO generateAwaitResults ( @RequestParam("responderId") int responderId,
			@RequestParam("token") String token ) {
		
		AwaitResultDTO result = new AwaitResultDTO();
		result.setResponderId(responderId);
		result.setToken(token);
		//Service Call which should return DTO
		
		result = acceptorResponseService.fetchAwaitList(result);
		if(result == null){
			System.out.println("I am null, save me");
			return result;
		}
		if (result.getError().equals("") == false) {
			System.out.println("Error while fetching response awaiting requests list::\n\t\t\t" + result.getError());
		}
		
		return result;
	}
	
	

}
