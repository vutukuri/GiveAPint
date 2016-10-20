package com.GiveAPint.service;

import java.util.List;

import com.GiveAPint.persistence.dbdo.LocationDBDO;

public interface UserLocationFetchService {

	/**
	 * This is to fetch all the user locations from the database. These are used
	 * to plot on the google map.
	 * 
	 * @param None
	 * @return list of LocationDBDO
	 */
	public List<LocationDBDO> getAllLocations();

}
