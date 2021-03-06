package com.GiveAPint.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.GiveAPint.dto.LocationDTO;
import com.GiveAPint.persistence.dbdo.LocationDBDO;
import com.GiveAPint.persistence.mappers.LocationMapper;
import com.GiveAPint.persistence.mappers.UserMapper;

/**
 * This service is responsible to update the location of every user
 * asynchronously.
 * 
 * @author abhi
 *
 */
@Service("UserLocationUpdateImpl")
public class UserLocationUpdateServiceImpl implements UserLocationUpdateService{

	@Autowired
	LocationMapper locationMapper;
	@Autowired
	UserMapper userMapper;
	@Autowired
	LoginUserService loginService;

	/**
	 * Update the location of the user as per the details provided in the DTO.
	 * Error field reflects the status completion of the process.
	 * 
	 * @param LocationDTO
	 * @return void
	 */

	public LocationDTO locationUpdate(LocationDTO newLocation) {

		System.out.println("Came inside the service to update the location");
		try {
			String userName = userMapper.getUserName(newLocation.getUserid());
			if ( loginService.validateToken(userName, newLocation.getToken()) == false ) {
				newLocation.setError("Token not valid!");
				System.out.println("Token passed is not valid");
				return newLocation;
			}
		} catch (Exception e) {
			System.out.println("Exception occured while getting the token");
			newLocation.setError(e.getCause().toString());
			return newLocation;
		}

		LocationDBDO location = new LocationDBDO(newLocation);
		try {
			locationMapper.updateLocation(location);
		} catch (Exception e) {
			System.out.println("Exception occured while updating the location");
			newLocation.setError(e.getCause().toString());
		}
		System.out.println("Location is updated succesfully in the service");
		newLocation.setError("");
		return newLocation;
	}
}
