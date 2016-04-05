package com.tcs.toolultimate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.tcs.toolultimate.vo.Employee;
import com.tcs.toolultimate.vo.UserLogin;

@Service("EmployeeService")
public class EmployeeService {

	@Autowired
	MongoTemplate mongoTemplate;

	public void saveEmployee(Employee emp) {
		List<Employee> findAll = mongoTemplate.findAll(Employee.class);
		System.out.println(findAll.get(0).getUsername());
	}

	/**
	 * This method is used to authenticate the user and return user details
	 * 
	 * @param fieldName
	 * @param fieldValue
	 * @return List of user
	 */
	public List<Employee> fetchUserByCredentials(UserLogin userLogin) {
		Query query = new Query();
		query.addCriteria(Criteria
				.where("username")
				.is(userLogin.getUsername())
				.andOperator(
						Criteria.where("password").is(userLogin.getPassword())));
		List<Employee> employees = mongoTemplate.find(query, Employee.class);
		return employees;
	}
}
