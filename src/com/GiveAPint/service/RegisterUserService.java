package com.GiveAPint.service;

import com.GiveAPint.dto.UserDTO;

public interface RegisterUserService {

	public UserDTO insertUser(UserDTO user);
	public Integer getMaxId();

}
