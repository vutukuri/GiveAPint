package com.GiveAPint.persistence.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.GiveAPint.dto.RequestBloodDTO;
import com.GiveAPint.persistence.dbdo.ResultDBDO;

public interface GeoQueryMapper {

	@Select(" SELECT \"userid\" as userId, \"bloodgroup\" as bloodGroup " +
	"FROM \"userstatus\", (SELECT \"lastlocation\" as \"requestloc\" FROM \"userstatus\" WHERE \"userid\" = #{userId}) as \"request\" " +
	"WHERE ST_DWithin(\"lastlocation\", \"requestloc\", #{rangeVal}) and not \"userid\" = #{userId} and \"bloodgroup\" = #{bloodGroup}")
	public List<ResultDBDO> rangeQuery(RequestBloodDTO request);

	@Select("SELECT max(\"requestid\") FROM \"requests\" WHERE \"userid\" = #{userId} ")
	public int getRequestId(int userId);
	
	@Insert("INSERT INTO \"requests\" (\"userid\", \"requeststatus\", \"requesttimestamp\", \"emergencylevel\", \"requestedbloodgroup\") VALUES (#{userId}, #{status}, #{timeStamp},#{emergencyLevel},#{bloodGroup})")
	public int insertRequest(RequestBloodDTO request);
}
