package com.GiveAPint.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.GiveAPint.dto.UserDTO;
import com.GiveAPint.persistence.dbdo.UserDBDO;
import com.GiveAPint.persistence.dbdo.UserStatusDBDO;
import com.GiveAPint.persistence.mappers.UserMapper;

/**
 * Implements RegisterUser interface and is responsible to insert the new user and his status into database.
 * And also responsible to call InputValidation on the UserDTO.
 * @author mamanoha
 *
 */
@Service
public class RegisterUserServiceImpl implements RegisterUserService{
	
	@Autowired
	private UserMapper userMapper;

	@Override
	public UserDTO insertUser(UserDTO user) {

		//TODO need to check the userName existed previosly, if yes, we need to send
		//error message saying that user already existed.
		UserDBDO newUser = new UserDBDO(user);
		try{
			userMapper.registerUser(newUser);
			user.setUserID(newUser.getUserId());
			System.out.println("In service class");
			System.out.println("new userid:" +newUser.getUserId());
			user.setError("");
			//user.setUserID(getMaxId());
		}catch ( Exception e ){
			e.printStackTrace();
			user.setError( e.getCause().toString() );
			System.out.println(user.getError());
			return user;
		}
		
		try{
			UserStatusDBDO userStatus = new UserStatusDBDO(user);
			userMapper.registerUserStatus(userStatus);
		}catch ( Exception e ){
			e.printStackTrace();
			//TODO need to insert a delete statement which deletes the row added in the previous column.
			user.setError( e.getCause().toString() );
			userMapper.deleteLoginUsers(user.getUserID());
		}
		
		return user;
		
	}
	
	@Override
	public Integer getMaxId(){
		int maxId = 0;
		try{
			maxId = userMapper.getMaxUserId();
		} catch ( NullPointerException e ){
			e.printStackTrace();
		}
		return Integer.valueOf(maxId);
	}

	
}
