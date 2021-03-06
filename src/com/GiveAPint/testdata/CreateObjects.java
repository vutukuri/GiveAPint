package com.GiveAPint.testdata;

import org.postgis.Point;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.GiveAPint.constants.ProjectConstants;
import com.GiveAPint.dto.UserDTO;
import com.GiveAPint.persistence.dbdo.QueryResultDBDO;
import com.GiveAPint.service.RegisterUserService;
import com.GiveAPint.dto.AcceptorDTO;
import com.GiveAPint.dto.LocationDTO;
import com.GiveAPint.dto.LoginUserDTO;
import com.GiveAPint.dto.RequestBloodDTO;
import com.GiveAPint.dto.UpdateUserStatusDTO;

/**
 * This class is responsible to create data objects which resembles to the ones
 * which are actually coming from the client. The main purpose of the methods
 * inside it is to ensure that all the logic including the business mappers are
 * working as required.
 * 
 * @author mamanoha
 *
 */
@Component("CreateObjects")
public class CreateObjects {

	@Autowired
	private RegisterUserService regService;
	/**
	 * Create dummy user every time when this method is called from the
	 * controller. All the values corresponding are stored in ProjectConstants class.
	 * 
	 * @return UserDTO with few fields initialized..
	 */
	public UserDTO createUser() 
	{
		// TODO set the fields to a different values before triggering this
		// method.
		UserDTO user = new UserDTO(ProjectConstants.firstName, ProjectConstants.lastName, ProjectConstants.passcode,
				getNewUserName(), ProjectConstants.phone, newDOB(), ProjectConstants.gender,
				ProjectConstants.healthStatus, newNextAvailableDate(), lastLocation(),
				ProjectConstants.bloodGroup);
		return user;
	}
	
	public UpdateUserStatusDTO updateStatus(){
		UpdateUserStatusDTO user = new UpdateUserStatusDTO( 3, newDonatedDate(), ProjectConstants.healthStatus, ProjectConstants.token );
		return user;
	}
	
	public LoginUserDTO createLoginUser()
	{
		//This  creates a new user with credentials set to ones in project constants file.
		//TODO change the values as required.
		LoginUserDTO loginUser = new LoginUserDTO(ProjectConstants.userName, ProjectConstants.passcode);
		return loginUser;
	}
	
	public String getNewUserName(){
		String userNameBase = "sample";
		String userNameExt = "@usc.edu";
		try{
			Integer maxUserId = regService.getMaxId();
			if( maxUserId != null ){
				userNameBase += maxUserId.toString();
			}
		}catch(NullPointerException e){
			e.printStackTrace();
		}
		return userNameBase + userNameExt;
	}
	
	public static Date newDOB()
	{
		Date date = new Date();
		try {
			//text to date.
			date = ProjectConstants.sdf.parse(ProjectConstants.dob);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}
	
	public static Date newNextAvailableDate()
	{
		Date date = new Date();
		try {
			//text to date.
			date = ProjectConstants.sdf.parse(ProjectConstants.nextAvailableDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}
	
	public static Date newDonatedDate()
	{
		Date date = new Date();
		try {
			//text to date.
			date = ProjectConstants.sdf.parse(ProjectConstants.donatedDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}
	
	public static Point lastLocation()
	{
		Point location = new Point();
		location.setX(ProjectConstants.xPoint);
		location.setY(ProjectConstants.yPoint);
		location.setSrid(4326);
		return location;
	}
	
	public LocationDTO createUpdateLocation(){
		LocationDTO newLocation = new LocationDTO(ProjectConstants.sampleUserId, ProjectConstants.xPoint, ProjectConstants.yPoint,
													null, ProjectConstants.token);
		return newLocation;
	}
	
	public RequestBloodDTO createRequest(String queryType)
	{
		RequestBloodDTO request = new RequestBloodDTO(5, ProjectConstants.status, 5,
				ProjectConstants.bloodGroup, queryType, 500000, 3, ProjectConstants.dummyToken);
		request.setTimeStamp(request.getCurrentDate().getTime());
		return request;
		//Need to set timestamp.
	}
	
	public AcceptorDTO createAcceptor()
	{
		//(requestId, userId, token, "Accept or Decline")
		AcceptorDTO acceptor = new AcceptorDTO(32, 5, "dummyTokenForDD", "Accept");
		return acceptor;
	}
	

}
