package com.GiveAPint.persistence.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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
	@Select("SELECT \"userid\", ST_Y(\"lastlocation\"::geometry) as latCoord, ST_X(\"lastlocation\"::geometry) as longCoord FROM \"userstatus\"")
	public List<LocationDBDO> getAllLocations();

	/**
	 * This function is responsible for updating the location of the user
	 * corresponding to given userid
	 * 
	 * @param LocationDBDO.
	 * @return number of updated entries.
	 */
	@Update("UPDATE \"userstatus\" SET \"lastlocation\" = ST_SetSRID(ST_MakePoint(#{longCoord}, #{latCoord}), 4326) WHERE \"userid\" = #{userid}")
	public Integer updateLocation(LocationDBDO location);
	
	/**
	 * Retrieves the location of the given user.
	 * @param userId of the user.
	 * @return user's last location in the form of long, lat.
	 */
	@Select("SELECT \"userid\", ST_Y(\"lastlocation\"::geometry) as latCoord, ST_X(\"lastlocation\"::geometry) as longCoord FROM \"userstatus\" WHERE \"userid\" = #{userId}")
	public LocationDBDO getUserLocation(@Param("userId") int userId);
}           
