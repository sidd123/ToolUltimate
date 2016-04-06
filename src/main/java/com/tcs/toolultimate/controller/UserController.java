package com.tcs.toolultimate.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tcs.toolultimate.config.Constants;
import com.tcs.toolultimate.service.EmployeeService;
import com.tcs.toolultimate.vo.Employee;
import com.tcs.toolultimate.vo.UserLogin;

@Controller
@PropertySource({"classpath:app.properties"})
public class UserController {
    
	@Autowired
	EmployeeService employeeService;
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Value("${toolultimate.default.password.appender}")
	private String defaultPasswordappender;
	
	
	@RequestMapping(value="/")
	public ModelAndView test(HttpServletResponse response) throws IOException{
		return new ModelAndView("index");
	}
	
	@RequestMapping(value="/login",method=RequestMethod.POST)	
	public @ResponseBody Map<String, Object> doLogin(@RequestBody UserLogin user) throws IOException{
		logger.debug("username: " + user.getUsername());
		logger.debug("password: " + user.getPassword());
		List<Employee> fetchUserByCredentials = employeeService.fetchUserByCredentials(user);
		Map<String, Object> fetchedUserDetails = new HashMap<String, Object>();
		fetchedUserDetails.put(Constants.STATUS, fetchUserByCredentials.size() == 0 ? Constants.SUCCESS : Constants.FAIL);
		fetchedUserDetails.put(Constants.DETAILS, fetchUserByCredentials);
		logger.debug("total records found: " + fetchUserByCredentials.size());
		return fetchedUserDetails;
	}
	
	
	@RequestMapping(value="/saveemployee",method=RequestMethod.POST)	
	public @ResponseBody Map saveEmployee(@RequestBody Employee empl) throws IOException{
		empl.setUsername(empl.getEmployeeId());
		empl.setPassword(getDefaultPassword(empl.getEmployeeId()));
		employeeService.saveEmployee(empl);

		Map result = new HashMap();
		result.put("status", "success");
		return result;
	}
	
	private String getDefaultPassword(String EmployeeId){
		StringBuilder password = new StringBuilder(EmployeeId);
		password.append(defaultPasswordappender);
		return password.toString();
	}
	

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
		return new PropertySourcesPlaceholderConfigurer();
	}
}
