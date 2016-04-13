package com.tcs.toolultimate.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.AggregationOutput;
import com.mongodb.DBObject;
import com.tcs.toolultimate.config.Constants;
import com.tcs.toolultimate.dao.common.BaseDAO;
import com.tcs.toolultimate.vo.Employee;
import com.tcs.toolultimate.vo.Level;
import com.tcs.toolultimate.vo.Origin;
import com.tcs.toolultimate.vo.Project;
import com.tcs.toolultimate.vo.Role;
import com.tcs.toolultimate.vo.SubProjects;
import com.tcs.toolultimate.vo.UmbrellaProject;
import com.tcs.toolultimate.vo.UserLogin;
import com.tcs.toolultimate.vo.UserRoles;

@Service("EmployeeService")
public class EmployeeService {

	@Autowired
	MongoTemplate mongoTemplate;
	
	
	@Autowired
	BaseDAO baseDAO;

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
	public Employee fetchUserByCredentials(UserLogin userLogin) {
		Query query = new Query();
		query.addCriteria(Criteria
				.where(Constants.COLUMN_NAME_USERNAME)
				.is(userLogin.getUsername())
				.andOperator(
						Criteria.where(Constants.COLUMN_NAME_PASSWORD).is(
								userLogin.getPassword())));
		List<Employee> employees = mongoTemplate.find(query, Employee.class);
		
		
		return mergeEmployeeRecordsforSame(employees);
	}
	
	
	private Employee mergeEmployeeRecordsforSame(List<Employee> employeeRecords) {
		Employee employee = null;

		if (employeeRecords != null && employeeRecords.size() > 0) {
			employee = employeeRecords.get(0);

			UserRoles roles = new UserRoles();
			employee.setUserRoles(roles);

			roles.setRole(employee.getRole());
			roles.setRoleId(employee.getRoleId());
			roles.setLevel(employee.getLevel());

			Set<String> orignIds = new HashSet<String>();

			for (Employee emp : employeeRecords) {
				if (Constants.LEVEL_VALUE_ACOOUNT.equals(roles.getLevel())) {
					orignIds.add(emp.getAccountId());
				} else if (Constants.LEVEL_VALUE_UMBRELLA.equals(roles
						.getLevel())) {
					orignIds.add(emp.getUmbrellaProjectId());
				} else if (Constants.LEVEL_VALUE_PROJECT.equals(roles
						.getLevel())) {
					orignIds.add(emp.getProjectId());
				} else if (Constants.LEVEL_VALUE_SUB_PROJECT.equals(roles
						.getLevel())) {
					orignIds.add(emp.getSubProjectId());
				}
			}
			roles.setOriginIds(orignIds);
		}

		return employee;
	}
	
	public List<Role> getRolesBelow(String myRole) {
		Query query = new Query();
		query.addCriteria(Criteria.where(Constants.COLUMN_NAME_ROLE_ID).gt(myRole));
		List<Role> roles = mongoTemplate.find(query, Role.class);
		return roles;
	}
	
	public List<Level> getLevelForSeletedRole(String selectedRole) {
		Query query = new Query();
		
		String allowedLevel = "";
		
		if (Constants.ROLE_VALUE_AM.equals(selectedRole)) {
			allowedLevel = Constants.LEVEL_VALUE_ACOOUNT;
		} else if (Constants.ROLE_VALUE_PM.equals(selectedRole)) {
			allowedLevel = Constants.LEVEL_VALUE_UMBRELLA;
		} else if (Constants.ROLE_VALUE_PL.equals(selectedRole)) {
			allowedLevel = Constants.LEVEL_VALUE_PROJECT;
		} else if (Constants.ROLE_VALUE_TL.equals(selectedRole)
				|| Constants.ROLE_VALUE_DEV.equals(selectedRole)) {
			allowedLevel = Constants.LEVEL_VALUE_SUB_PROJECT;
		}
		
		query.addCriteria(Criteria.where(Constants.COLUMN_NAME_LEVEL).is(allowedLevel));
		List<Level> levels = mongoTemplate.find(query, Level.class);
		return levels;
	}
	
	
	public List<Origin> getAllOrignis(String selectedlevel,
			Set<String> creatorOrgId, String creatorOrgLevel) {
		List<Origin> origins = null;
		String originFieldName = "";
		ObjectMapper mapper = new ObjectMapper();
		Origin origin = null;
		
		
		List<DBObject> pipeline = baseDAO.getUnwindAggregrateComponent(selectedlevel);
		
		DBObject match = baseDAO.getMatchAggregrateComponent(creatorOrgId,creatorOrgLevel);
		
		pipeline.add(match);
		
		DBObject projection  =baseDAO.getProjectAggregateComponent(selectedlevel);
		
		pipeline.add(projection);
		
		AggregationOutput output = mongoTemplate.getCollection(Constants.ULTIMATEDOCUMENT_STORE_NAME).aggregate(pipeline);
		
		Iterable<DBObject> results = output.results();
		
		if(results.iterator().hasNext()){
			origins = new ArrayList<Origin>();
		}
		
		try {
			for (DBObject record : results) {

				if (Constants.LEVEL_VALUE_UMBRELLA.equals(selectedlevel)) {
					originFieldName = Constants.COLUMN_NAME_UMBRELLA_PROJECTS;
					UmbrellaProject project = mapper.readValue(
							record.get(originFieldName).toString(),
							UmbrellaProject.class);
					origin = new Origin();
					origin.setOriginId(project.getUmbrellaProjectId());
					origin.setOriginName(project.getUmbrellaProjectName());
				} else if (Constants.LEVEL_VALUE_PROJECT.equals(selectedlevel)) {
					originFieldName = Constants.COLUMN_NAME_PROJECTS;
					Project project = mapper.readValue(record.get(originFieldName)
							.toString(), Project.class);
					origin = new Origin();
					origin.setOriginId(project.getProjectId());
					origin.setOriginName(project.getProjectName());

				} else if (Constants.LEVEL_VALUE_SUB_PROJECT.equals(selectedlevel)) {
					originFieldName = Constants.COLUMN_NAME_SUBPROJECTS;
					SubProjects project = mapper.readValue(
							record.get(originFieldName).toString(),
							SubProjects.class);

					origin = new Origin();
					origin.setOriginId(project.getSubProjectId());
					origin.setOriginName(project.getSubProjectName());

				}

				origins.add(origin);

			}
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return origins;
	}
	
}


