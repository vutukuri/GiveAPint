package com.GiveAPint.persistence.mappers;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

import com.GiveAPint.dto.AcceptorDTO;

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

}
