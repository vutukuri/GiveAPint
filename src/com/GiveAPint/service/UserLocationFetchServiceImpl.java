package com.GiveAPint.service;

import java.util.ArrayList;
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
		List<LocationDBDO> returnLocations = new ArrayList<>();
		// TODO Auto-generated method stub
		try{
			System.out.println("Came inside the service to fetch the locations");
			returnLocations = locationMapper.getAllLocations();
		}catch( Exception e ){
			e.printStackTrace();
			System.out.println("Exception occurred while fetching all locations");
			System.out.println(e.getCause());
		}
		return returnLocations;
	}

}
