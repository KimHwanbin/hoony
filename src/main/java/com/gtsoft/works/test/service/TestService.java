package com.gtsoft.works.test.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gtsoft.works.test.dto.TestDTO;
import com.gtsoft.works.test.repository.TestRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class TestService {

	@Autowired
	TestRepository testRepository;

	public HashMap<String, Object> selectGtUser(TestDTO params) {
		
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		
		List<TestDTO> userList = testRepository.selectGtUser(params);
		
		log.info("userList >>> " + userList);
		
		resultMap.put("userList", userList);
		
		return resultMap;
	}
	
}
