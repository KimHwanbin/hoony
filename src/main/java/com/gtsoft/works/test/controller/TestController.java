package com.gtsoft.works.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gtsoft.works.test.dto.TestDTO;
import com.gtsoft.works.test.service.TestService;

import lombok.extern.slf4j.Slf4j;


@Controller
@Slf4j
public class TestController {

	@Autowired
	TestService testService;
	
	@RequestMapping("/")
	public String test(@ModelAttribute("params") TestDTO params, Model model ) {
		
		model.addAllAttributes(testService.selectGtUser(params));
		
		return "contents/index";
	}
}
