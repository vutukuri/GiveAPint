/**
 * 
 */
package com.GiveAPint.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.GiveAPint.dto.UpdateUserStatusDTO;
import com.GiveAPint.persistence.mappers.UserMapper;

/**
 * This service is used to update the user healthStatus and lastDonatedDate
 * after validating the user i.e. there should be a token corresponding to the
 * user in the token table.
 * 
 * @author vutukuri
 *
 */

@Service("UpdateStatusService")
public class UpdateStatusServiceImpl implements UpdateStatusService {

	@Autowired
	private UserMapper userMapper;
	@Autowired
	private LoginUserService tokenCheck;

	public UpdateUserStatusDTO UpdateUserStatus(UpdateUserStatusDTO user) {
		String userName = userMapper.getUserName(user.getUserid());

		if (userName == null || userName == "") {
			user.setError("The userid does not exist in the login, Please signup first");
			return user;
		}
		Boolean check = tokenCheck.validateToken(userName, user.getToken());

		if (check == false) {
			user.setError("The token is mismatched, please verify");
			return user;
		}
		Integer update;
		try {
			update = userMapper.updateHealthStatus(user.getUserid(), user.getHealthStatus());
		} catch (Exception e) {
			user.setError(e.getCause().toString());
			return user;
		}
		if (update == null || update < 1) {
			user.setError("Update failed at database");
			return user;
		}
		if (user.getLastDonatedDate() != null) {
			Integer insert;
			try {
				insert = userMapper.insertLastDonatedDate(user.getUserid(), user.getLastDonatedDate());
			} catch (Exception e) {
				user.setError(e.getCause().toString());
				return user;
			}
			if (insert == null || insert < 1) {
				user.setError("Insertion of lastdonation failed at database.");
			}
		}
		return user;
	}
}
