package com.tcs.toolultimate.service.employee;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.tcs.toolultimate.vo.Employee;

@Service("EmployeeService")
public class EmployeeService {

	@Autowired
	MongoTemplate mongoTemplate;
	
	public void saveEmployee(Employee emp){
		List<Employee> findAll = mongoTemplate.findAll(Employee.class);
		System.out.println(findAll.get(0).getUsername());
	}
}
