package com.GiveAPint.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.GiveAPint.persistence.dbdo.LocationDBDO;
import com.GiveAPint.persistence.mappers.LocationMapper;

@Service("UserLocationFetchServiceImpl")
public class UserLocationFetchServiceImpl implements UserLocationFetchService{

	@Autowired
	private LocationMapper locationMapper;
	
	/**
	 * This method is responsible to fetch all the user locations from the database. These locations plotted on the google map.
	 */
	@Override
	public List<LocationDBDO> getAllLocations() {
		// TODO Auto-generated method stub
		return locationMapper.getAllLocations();
	}

}
