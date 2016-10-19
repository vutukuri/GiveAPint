package com.GiveAPint.persistence.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.GiveAPint.persistence.dbdo.LocationDBDO;

/**
 * This interface maps sql queries with the corresponding functions. The output
 * from the queries are mapped onto LocationDBDO
 * 
 * @author abhi
 *
 */

public interface LocationMapper {

	/**
	 * This function is responsible for getting all the locations [userid,
	 * bloodgroup], and mapping to LocationDBDO
	 * 
	 * @return list of LocationDBDO
	 */
	@Select("SELECT ST_Y(\"lastlocation\"::geometry) as latCoord, ST_X(\"lastlocation\"::geometry) as longCoord, \"bloodgroup\", \"userid\" FROM \"userstatus\"")
	public List<LocationDBDO> getAllLocations();
}
