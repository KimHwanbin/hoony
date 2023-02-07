package com.gtsoft.works.test.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.gtsoft.works.test.dto.TestDTO;

@Mapper
public interface TestRepository {

	List<TestDTO> selectGtUser(TestDTO params);

}
