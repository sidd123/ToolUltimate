package com.tcs.toolultimate.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tcs.toolultimate.config.Constants;
import com.tcs.toolultimate.service.AccountService;
import com.tcs.toolultimate.service.EmployeeService;
import com.tcs.toolultimate.vo.Employee;
import com.tcs.toolultimate.vo.Level;
import com.tcs.toolultimate.vo.Origin;
import com.tcs.toolultimate.vo.Role;
import com.tcs.toolultimate.vo.UserLogin;

@Controller
@PropertySource({"classpath:app.properties"})
public class UserController {
    
	@Autowired
	EmployeeService employeeService;
	
	@Autowired
	AccountService accountService;
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Value("${toolultimate.default.password.appender}")
	private String defaultPasswordappender;
	
	
	@RequestMapping(value="/")
	public ModelAndView test(HttpServletResponse response) throws IOException{
		return new ModelAndView("index");
	}
	
	@RequestMapping(value="/login",method=RequestMethod.POST)	
	public @ResponseBody Map<String, Object> doLogin(HttpSession session,@RequestBody UserLogin user) throws IOException{
		logger.debug("username: " + user.getUsername());
		logger.debug("password: " + user.getPassword());
		Map<String, Object> fetchedUserDetails = new HashMap<String, Object>();
		Employee fetchUserByCredentials = null;
		
		if(session.getAttribute("loggedInUser") != null){
			fetchUserByCredentials = (Employee) session.getAttribute("loggedInUser");
		}else {
			fetchUserByCredentials = employeeService.fetchUserByCredentials(user);
			session.setAttribute("loggedInUser", fetchUserByCredentials);			
		}
		

		fetchedUserDetails.put(Constants.STATUS, fetchUserByCredentials != null ? Constants.SUCCESS : Constants.FAIL);
		fetchedUserDetails.put(Constants.DETAILS, fetchUserByCredentials);
		
		
		return fetchedUserDetails;
	}
	
	@RequestMapping(value="/sessionexists")
	public @ResponseBody
	String checkSessionExists(HttpServletRequest request, HttpSession session) throws IOException {
		
		if(session.getAttribute("loggedInUser") != null){
			return "true";
		}else {
			return "false";
		}
	}
	
	
	@RequestMapping(value="/logout")
	public @ResponseBody
	void logout(HttpSession session) throws IOException {
		if(session.getAttribute("loggedInUser") != null){
			session.setAttribute("loggedInUser", null);
		}
		
		session.invalidate();
	}
	
	@RequestMapping(value="/addEmployee")
	public @ResponseBody
	Map addEmployee(HttpSession session) throws IOException {
		Map<String, Object> fetchedUserDetails = new HashMap<String, Object>();
		
		if(session.getAttribute("loggedInUser") != null){
			
			Employee loggedInUser = (Employee)session.getAttribute("loggedInUser");
			
			List<Role> rolesAvaliable = employeeService.getRolesBelow(loggedInUser.getRoleId());
			
			fetchedUserDetails.put(Constants.ROLES_DROPDOWN_VALUES,rolesAvaliable);
			
			fetchedUserDetails.put(Constants.STATUS, Constants.SUCCESS);
		} else {
			fetchedUserDetails.put(Constants.STATUS, Constants.FAIL);
		}
		
		return fetchedUserDetails;
		
	}
	
	

	
	
	@RequestMapping(value="/viewLevels", method=RequestMethod.POST)
	public @ResponseBody
	Map viewLevels(HttpSession session,@RequestBody Role role) throws IOException {
		Map<String, Object> fetchedUserDetails = new HashMap<String, Object>();
		
		if(session.getAttribute("loggedInUser") != null){
			
			List<Level> levels = employeeService.getLevelForSeletedRole(role.getRoleId());
			
			fetchedUserDetails.put(Constants.LEVELS_DROPDOWN_VALUES,levels);
			
			fetchedUserDetails.put(Constants.STATUS, Constants.SUCCESS);
		} else {
			fetchedUserDetails.put(Constants.STATUS, Constants.FAIL);
		}
		
		return fetchedUserDetails;
		
	}
	
	
	@RequestMapping(value="/viewOrigins", method=RequestMethod.POST)
	public @ResponseBody
	Map viewOrigins(HttpSession session,@RequestBody Level level) throws IOException {
		Map<String, Object> fetchedUserDetails = new HashMap<String, Object>();
		
		if(session.getAttribute("loggedInUser") != null){
			Employee loggedInUser = (Employee)session.getAttribute("loggedInUser");
			
			List<Origin> origins = employeeService.getAllOrignis(level.getLevel(), loggedInUser.getUserRoles().getOriginIds(), loggedInUser.getLevel());
			
			fetchedUserDetails.put(Constants.ORIGIN_DROPDOWN_VALUES,origins);
			
			
			if (Constants.LEVEL_VALUE_UMBRELLA.equals(level.getLevel())) {
				fetchedUserDetails.put(Constants.ORIGIN_LABEL_NAME,Constants.ORIGIN_VALUE_UMBRELLA_PROJ);
			}else if(Constants.LEVEL_VALUE_PROJECT.equals(level.getLevel())) {
				fetchedUserDetails.put(Constants.ORIGIN_LABEL_NAME,Constants.ORIGIN_VALUE_PROJ);
			}else if (Constants.LEVEL_VALUE_SUB_PROJECT.equals(level.getLevel())) {
				fetchedUserDetails.put(Constants.ORIGIN_LABEL_NAME,Constants.ORIGIN_VALUE_SUB_PROJ);
			}
			
			
			
			fetchedUserDetails.put(Constants.STATUS, Constants.SUCCESS);
		} else {
			fetchedUserDetails.put(Constants.STATUS, Constants.FAIL);
		}
		
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
	
}
