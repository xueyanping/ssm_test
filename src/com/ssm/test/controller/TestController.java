package com.ssm.test.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ssm.test.service.TestService;

@Controller
public class TestController {

	@Autowired
	private TestService testService;
	
	@RequestMapping("/index")
	public String index() {
		System.out.println("index 执行");
		String str = testService.index();
		return str;
	}
	
	@RequestMapping("/back")
	public String back() {
		System.out.println("back 执行");
		return "index";
	}
	
	@RequestMapping("/outPut")
	public String outPut(HttpServletResponse response) {
		System.out.println("导出 执行");
		String str = testService.outPut(response);
		return str;
	}
}
