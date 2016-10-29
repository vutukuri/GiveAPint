package com.GiveAPint.persistence.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.GiveAPint.dto.RequestInfoDTO;
import com.GiveAPint.persistence.dbdo.DonorDBDO;

public interface RequestInfoMapper {

	@Select("SELECT \"donorid\" from acceptors where \"requestid\" = #{requestId}")
	public List<Integer> getAllAcceptors(@Param("requestId") int requestId);

	public List<DonorDBDO> getDonorsInfo(@Param("acceptors") List<Integer> acceptors,
			@Param("userId") int userId);

	public RequestInfoDTO getRequestData(@Param("requestId") int requestId);
}
