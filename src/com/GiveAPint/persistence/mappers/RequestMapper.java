package com.GiveAPint.persistence.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.SelectKey;

import com.GiveAPint.dto.RequestBloodDTO;
import com.GiveAPint.persistence.dbdo.QueryResultDBDO;

public interface RequestMapper {

	@Insert("INSERT INTO \"requests\" (\"userid\", \"requeststatus\", \"requesttimestamp\", \"emergencylevel\", \"requestedbloodgroup\") VALUES (#{userId}, #{status}, #{timeStamp}, #{emergencyLevel}, #{bloodGroup})")
	@SelectKey(statement="SELECT nextVal('requestid_seq')", keyProperty="requestId", before = true, resultType=int.class)
	public Integer insertRequest(RequestBloodDTO request);
	
	public List<QueryResultDBDO> getKNearestNeighbors(RequestBloodDTO request);
}
