package com.tcs.toolultimate.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tcs.toolultimate.service.EmployeeService;
import com.tcs.toolultimate.vo.Employee;
import com.tcs.toolultimate.vo.UserLogin;

@Controller
public class UserController {
    
	@Autowired
	EmployeeService employeeService;
	
	@RequestMapping(value="/")
	public ModelAndView test(HttpServletResponse response) throws IOException{
		return new ModelAndView("index");
	}
	
	@RequestMapping(value="/login",method=RequestMethod.POST)	
	public @ResponseBody Map doLogin(@RequestBody UserLogin user) throws IOException{
		
		System.out.println("test");
		Map result = new HashMap();
		result.put("status", "success");
		result.put("name", user.getUsername());
		result.put("email", "test@gmail.com");
		employeeService.saveEmployee(new Employee());
		return result;
	}
	
	
	@RequestMapping(value="/addEmployee")
	public ModelAndView addEmployee() throws IOException{
		return new ModelAndView("emloyeeform");
	}
}
