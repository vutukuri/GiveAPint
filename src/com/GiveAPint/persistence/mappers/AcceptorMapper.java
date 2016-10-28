package com.GiveAPint.persistence.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

import com.GiveAPint.dto.AcceptorDTO;
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
	
	/**
	 * Fetches all the requests made by a user with the given id.
	 * @param userId
	 * @return
	 */
	public List<UserRequestsDBDO> getAllRequests(@Param("userId") int userId);

}
