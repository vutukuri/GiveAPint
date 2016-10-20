package com.GiveAPint.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.GiveAPint.constants.ProjectConstants;
import com.GiveAPint.dto.LocationDTO;
import com.GiveAPint.dto.LoginUserDTO;
import com.GiveAPint.dto.UpdateUserStatusDTO;
import com.GiveAPint.dto.UserDTO;
import com.GiveAPint.persistence.dbdo.LocationDBDO;
import com.GiveAPint.persistence.dbdo.UserDBDO;
import com.GiveAPint.persistence.mappers.UserMapper;
import com.GiveAPint.service.LoginUserService;
import com.GiveAPint.service.RegisterUserService;
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
	private CreateObjects createSampleObjects;
	@Autowired
	private RegisterUserService registerService;	
	@Autowired
	private LoginUserService loginService;
	@Autowired
	private UserLocationFetchService locationService;
	@Autowired
<<<<<<< HEAD
	private UpdateStatusService updateService;
=======
	private UserLocationUpdateService locationUpdateService;
>>>>>>> 02ce4dd3b5263647e86961792a3b81412062e1ea

	/**
	 * Register an user.
	 * 
	 * @return
	 */
	@RequestMapping(value = "/registerUser")
	public ModelAndView registerUser() {
		UserDTO user = createSampleObjects.createUser();
		user = registerService.insertUser(user);
		System.out.println("The userId of newly created user is:" +user.getUserID());
		return new ModelAndView("register");
	}

	/**
	 * Validate the user credentials.
	 * 
	 * @return loginUserDTO to which a token is attached.
	 */
	@RequestMapping(value = "/loginUser")
	public ModelAndView loginUser() {
		LoginUserDTO user = createSampleObjects.createLoginUser();
		user = loginService.validateUser(user);
		System.out.println("Login user status(null if no error):" +user.getError());
		System.out.println("Token that is generated:" +user.getToken());
		return new ModelAndView("login");
	}
	
	@RequestMapping(value = "/validateTokenOfUser")
	public ModelAndView validateToken() {
		
		boolean isValidToken = loginService.validateToken(ProjectConstants.userName, "dd7c16992dhfa");
		System.out.println("Is token a  valid one:" +isValidToken);
		return new ModelAndView("login");
	}

	@RequestMapping(value = "/getAllUsers")
	public ModelAndView getAllUsers() {
		List<UserDBDO> users = userMapper.getAllUsers();
		System.out.println("The number of users as if now:" + users.size());
		for (UserDBDO user : users) {
			// Print the values corresponding to the user.
			// TODO need to print the key value pairs.
			System.out.println("User info:" + user.getFirstName() + " " + user.getLastName() + " " + user.getUserId()
					+ " " + user.getUserName() + " " + user.getPasscode());
		}
		return new ModelAndView("getAllUsers");
	}
	
	@RequestMapping(value = "/getAllLocations")
	public ModelAndView getAllLocations(){
		
		List<LocationDBDO> locations = locationService.getAllLocations();
		if( locations != null ){
			System.out.println("Prints all the locations from the database now. (Lat, Long)");
			for ( LocationDBDO location : locations ){
				
				System.out.println("UserID:" + location.getUserid() + " Lat:" + location.getLatCoord() + " Long:"
											+ location.getLongCoord() + "\n");
				
			}
		}
		return new ModelAndView("getAllLocations");
		
	}
	
<<<<<<< HEAD
	@RequestMapping(value = "/updateStatus")
	public ModelAndView updateStatus(){
		UpdateUserStatusDTO user = createSampleObjects.updateStatus();
		UpdateUserStatusDTO update = updateService.UpdateUserStatus(user);
		System.out.println("Login user status(null if no error):" + update.getError());
		return new ModelAndView("updateStatus");
=======
	@RequestMapping(value = "/updateLocation")
	public ModelAndView updateLocation(){
		
		LocationDTO newLocation = createSampleObjects.createUpdateLocation();
		newLocation = locationUpdateService.locationUpdate(newLocation);
		if( newLocation.getError() == "" || newLocation.getError() == null ){
			System.out.println("Location Updated!  " + newLocation.getUserid() +" "+newLocation.getLatCoord() + " " + newLocation.getLongCoord());
		}
		else{
			System.out.println("Location Updation Error" + newLocation.getError());
		}
		return new ModelAndView("updateLocation");
>>>>>>> 02ce4dd3b5263647e86961792a3b81412062e1ea
	}

}
