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
		
		// TODO Auto-generated method stub
		
		UserDBDO newUser = new UserDBDO(user);
		try{
			userMapper.registerUser(newUser);
			user.setUserID(getMaxId());
		}catch ( Exception e ){
			user.setError( e.getCause().toString() );
			System.out.println(user.getError());
			user.setUserID(0);
			return user;
		}
		
		try{
			UserStatusDBDO userStatus = new UserStatusDBDO(user);
			userMapper.registerUserStatus(userStatus);
		}catch ( Exception e ){
			user.setError( e.getCause().toString() );
			userMapper.deleteLoginUsers(user.getUserID());
			user.setUserID(0);
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
