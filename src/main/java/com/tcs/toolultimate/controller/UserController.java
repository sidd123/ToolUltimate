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

import com.tcs.toolultimate.service.employee.EmployeeService;
import com.tcs.toolultimate.vo.Employee;

@Controller
public class UserController {
    
	@Autowired
	EmployeeService employeeService;
	
	@RequestMapping(value="/")
	public ModelAndView test(HttpServletResponse response) throws IOException{
		return new ModelAndView("index");
	}
	
	@RequestMapping(value="/login",method=RequestMethod.POST)	
	public @ResponseBody Map doLogin(@RequestBody Employee customer) throws IOException{
		
		System.out.println("test");
		Map result = new HashMap();
		result.put("status", "success");
		result.put("name", customer.getUsername());
		result.put("email", "test@gmail.com");
		return result;
	}
	
	
	@RequestMapping(value="/addEmployee")
	public ModelAndView addEmployee() throws IOException{
		return new ModelAndView("emloyeeform");
	}
}
