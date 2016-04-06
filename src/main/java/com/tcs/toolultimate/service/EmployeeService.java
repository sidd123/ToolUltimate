package com.tcs.toolultimate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.tcs.toolultimate.config.Constants;
import com.tcs.toolultimate.vo.Employee;
import com.tcs.toolultimate.vo.UserLogin;

@Service("EmployeeService")
public class EmployeeService {

	@Autowired
	MongoTemplate mongoTemplate;

	public void saveEmployee(Employee emp) {
		mongoTemplate.save(emp);
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
				.where(Constants.COLUMN_NAME_USERNAME)
				.is(userLogin.getUsername())
				.andOperator(
						Criteria.where(Constants.COLUMN_NAME_PASSWORD).is(
								userLogin.getPassword())));
		List<Employee> employees = mongoTemplate.find(query, Employee.class);
		return employees;
	}
}
