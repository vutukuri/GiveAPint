package com.GiveAPint.persistence.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;

import com.GiveAPint.dto.NotificationDetailsDTO;
import com.GiveAPint.dto.RequestBloodDTO;
import com.GiveAPint.persistence.dbdo.QueryResultDBDO;

/**
 * This mapper class is responsible for implementing the spatial queries required.
 * Some of the methods for better implementation are included in the xml file.
 * @author mamanoha
 *
 */
public interface RequestMapper {

	@Insert("INSERT INTO \"requests\" (\"requestid\", \"userid\", \"requeststatus\", \"requesttimestamp\", \"emergencylevel\", \"requestedbloodgroup\", \"totalnumber\", \"respondednumber\") VALUES (#{requestId}, #{userId}, #{status}, #{timeStamp}, #{emergencyLevel}, #{bloodGroup}, #{totalNumber}, #{respondedNumber})")
	@SelectKey(statement = "SELECT nextVal('requests_requestid_seq')", keyProperty = "requestId", before = true, resultType = int.class)
	public Integer insertRequest(RequestBloodDTO request);
	
	@Select("SELECT max(\"requestid\") FROM \"requests\" WHERE \"userid\" = #{userId} ")
	public int getRequestId(int userId);
	
	public List<QueryResultDBDO> getKNearestNeighbors(RequestBloodDTO request);
	
	public List<QueryResultDBDO> rangeQuery(RequestBloodDTO request);
	
	@Select("SELECT (\"userid\") from requests WHERE \"requestid\" = #{requestId}")
	public int getRequesterId(@Param("requestId") int requestId);
	
	
	
	/**
	 * Returns list of('username', 'userid') for the give list of userid's in the arguments
	 * @param listOfUsers
	 * @return
	 */
	public List<NotificationDetailsDTO> getUserNamesForUserIds(@Param("listOfUsers")
												List<QueryResultDBDO> listOfUsers);
	
	
	public List<NotificationDetailsDTO> getRegIdsForUsers(@Param("listOfUsers") List<NotificationDetailsDTO> listOfUsers);
}
