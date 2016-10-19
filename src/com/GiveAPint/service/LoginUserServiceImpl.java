package com.GiveAPint.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.GiveAPint.dto.LoginUserDTO;
import com.GiveAPint.persistence.dbdo.TokenDBDO;
import com.GiveAPint.persistence.mappers.TokenMapper;
import com.GiveAPint.persistence.mappers.UserMapper;
import com.GiveAPint.util.TokenRandomGenerator;

/**
 * This class is responsible to validate the user and also the token corresponding to him.
 * @author mamanoha
 *
 */
@Service("LoginUserService")
public class LoginUserServiceImpl implements LoginUserService {

	@Autowired
	private UserMapper userMapper;
	@Autowired
	private TokenMapper tokenMapper;

	@Override
	public LoginUserDTO validateUser(LoginUserDTO user)
	{
		//retrieve the password corresponding to the userName
		String passcode = userMapper.getPasscode(user.getUserName());
		//null, when there is no userName associated with the value sent.
		if( passcode == null || passcode.equals(""))
		{
			//set appropriate error message.
			user.setError("No userName with value '" +user.getUserName()+ "' found, please verify");
			//returns user with token value not set.
			return user;
		}
		else
		{
			//If passcode returned from database matches with the 'passcode' received from the user.
			if( passcode.equals(user.getPasscode()) )
			{
				//Get's the token value associated with the userName, null if there is no entry in tokens table.
		        String oldToken = tokenMapper.getToken(user.getUserName());
		        //generate a new token which is of length 32.
		        TokenRandomGenerator randomGenerator = TokenRandomGenerator.getInstance();
		        String newToken = randomGenerator.getRandomToken();
		        System.out.println("The new token generated is:" +newToken);
		        //create a new tokenDBDO with the values sent.
		        TokenDBDO newTokenDBDO = new TokenDBDO(user.getUserName(), newToken);
		        //update the token only if there is a entry corresponding.
		        if( oldToken != null && ! oldToken.equals("") )
		        {
		        	System.out.println("Old token corresponding to the user:" + oldToken);
		            tokenMapper.updateToken(newToken, user.getUserName());
		        }
		        //insert the new token into the tokens table.
		        else
		        {
		            tokenMapper.insertToken(newTokenDBDO);
		        }
		        //set the newtoken generated to the user.
		        user.setToken(newToken);
		        return user;
			}
			//If passcode doesn't match with the one in the database. Set appropriate error message and return.
			else
			{
				user.setError("Password doesn't match with corresponding user, please verify");
				return user;
			}
		}
	}

	@Override
	public boolean validateToken(String userName, String token)
	{
		String actualToken = tokenMapper.getToken(userName);
		if( actualToken == null || actualToken.equals("") )
		{
			//there is no corresponding entry in the tokens table with this username.
			return false;
		}
		else
		{
			if( actualToken.equals(token) )				
			{
				//correct match
				return true;
			}
			else
			{
				//Does not match with the one in tokens database table.
				return false;
			}
		}
	}

}
