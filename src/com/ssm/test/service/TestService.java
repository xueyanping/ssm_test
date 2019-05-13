package com.ssm.test.service;

import org.springframework.stereotype.Service;

@Service
public class TestService {

	public String index() {
		System.out.println("testService——index 执行");
		return "success";
	}
	
}
