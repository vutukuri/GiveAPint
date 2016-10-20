package com.GiveAPint.service;

import com.GiveAPint.dto.LoginUserDTO;

public interface LoginUserService {

	/**
	 * Validates the user when he tries to login, generates or updates the
	 * existing token with a new token. When the userName does not match with
	 * the passcode, it does not set the token value which can be used as one of
	 * the error check.
	 * 
	 * @param user
	 * @return LoginUserDTO with token value set.
	 */
	public LoginUserDTO validateUser(LoginUserDTO user);

	/**
	 * Every time when a session is established, the authenticity can be checked
	 * using the token sent in every request.
	 * 
	 * @param userId
	 *            of the user.
	 * @param token
	 *            corresponding to the userId.
	 * @return true if token matches with the userId, else return false.
	 */
	public boolean validateToken(String userName, String token);
}
