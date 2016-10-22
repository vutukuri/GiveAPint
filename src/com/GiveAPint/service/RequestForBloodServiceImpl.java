package com.GiveAPint.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.GiveAPint.dto.RequestBloodDTO;
import com.GiveAPint.persistence.dbdo.ResultDBDO;
import com.GiveAPint.persistence.mappers.GeoQueryMapper;
import com.GiveAPint.persistence.mappers.UserMapper;

@Service("RequestForBloodService")
public class RequestForBloodServiceImpl implements RequestForBloodService {

	@Autowired
	private UserMapper userMapper;
	@Autowired
	private LoginUserService loginService;
	@Autowired
	private GeoQueryMapper queryMapper;

	@Override
	public RequestBloodDTO knnQuery(RequestBloodDTO request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RequestBloodDTO rangeQuery(RequestBloodDTO request) {
		// TODO Auto-generated method stub
		try {
			String userName = userMapper.getUserName(request.getUserId());
			if (loginService.validateToken(userName, request.getToken()) == false) {
				request.setError("Token not matched!");
				return request;
			}
		} catch (Exception e) {
			request.setError(e.getCause().toString());
			return request;
		}

		try {
			queryMapper.insertRequest(request);
		} catch (Exception e) {
			request.setError(e.getCause().toString());
			return request;
		}
		Integer requestId;
		try {
			requestId = queryMapper.getRequestId(request.getUserId());
			request.setRequestId(requestId);
		} catch (Exception e) {
			request.setError(e.getCause().toString());
			return request;
		}
		
		try {
			List<ResultDBDO> resultList = queryMapper.rangeQuery(request);
			request.setResultList(resultList);
		} catch (Exception e) {
			request.setError(e.getCause().toString());
		}

		return request;
	}

}
