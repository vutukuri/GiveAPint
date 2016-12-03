package com.GiveAPint.persistence.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.GiveAPint.dto.AcceptorDTO;
import com.GiveAPint.dto.AwaitResponseDTO;
import com.GiveAPint.persistence.dbdo.AwaitResultDBDO;
import com.GiveAPint.persistence.dbdo.UserRequestsDBDO;

public interface AcceptorMapper {
	
	/**
	 * Updates the request status and the number of users responded.
	 * The query updates the status only if the user responds positively.
	 * @param requestId to which user responded.
	 * @return number of rows affected.
	 */
	public int updateRequestStatus(@Param("requestId") int requestId, @Param("response") String response);
	
	
	@Insert("INSERT INTO acceptors(\"requestid\", \"donorid\") VALUES(#{requestId}, #{userId})")
	public int insertAcceptor(AcceptorDTO acceptor);
	
	
	@Insert("INSERT INTO awaitresponse(\"awaitreqid\",\"requestorid\",\"responderid\") VALUES(#{awaitRequestId},#{awaitRequestorId},#{awaitResponderId})")
	public int insertAwaitResponse(AwaitResponseDTO response);
	
	@Delete("DELETE FROM awaitresponse WHERE \"awaitreqid\"=#{requestId} AND  \"responderid\"=#{userId}")
	public int deleteAwaitResponse(AcceptorDTO toDelResponse);

	@Select("SELECT \"awaitreqid\" as requestId, \"requestorid\" FROM awaitresponse WHERE \"responderid\"=#{responderId}")
	public List<AwaitResultDBDO> getAwaitIds(int responderId);
	
	@Select("SELECT \"requestedbloodgroup\" as requestedBG, \"emergencylevel\" as emerLevel FROM requests WHERE \"requestid\"=#{requestId}")
	public AwaitResultDBDO getBgEmer(int requestId);
	
	@Select("SELECT \"firstname\" as requestorFName,\"lastname\" as requestorLName FROM loginusers WHERE \"userid\"=#{requestorId}")
	public AwaitResultDBDO getNames(int requestorId);
	
	@Select("SELECT (ST_Distance(a.\"lastlocation\",b.\"lastlocation\")/1610) as distance FROM userstatus as a, userstatus as b WHERE a.\"userid\"=#{requestorId} and b.\"userid\"=#{responderId}")
	public AwaitResultDBDO getDistance(@Param("requestorId") int requestorId, @Param("responderId") int responderId);
	
	/**
	 * Fetches all the requests made by a user with the given id.
	 * @param userId
	 * @return
	 */
	
	@Select("SELECT \"requestid\" as requestId, \"requeststatus\" as status, (extract(epoch from \"requesttimestamp\") * 1000) as timestamp, \"emergencylevel\" as emergencyLevel, \"requestedbloodgroup\" as requestedBloodType FROM requests where \"userid\" =#{userId} ORDER BY \"requesttimestamp\" DESC")
	public List<UserRequestsDBDO> getAllRequests(@Param("userId") int userId);

}
