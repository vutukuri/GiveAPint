package com.GiveAPint.service;

import com.GiveAPint.dto.LocationDTO;

public interface UserLocationUpdateService {

	/**
	 * Updates user locations asynchronously. Updates error field of LocationDTO
	 * if something goes wrong.
	 * 
	 * @param LocationDTO
	 * @return void
	 */

	public LocationDTO locationUpdate(LocationDTO newLocation);
}
