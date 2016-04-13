package com.tcs.toolultimate.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.tcs.toolultimate.config.Constants;
import com.tcs.toolultimate.dao.common.BaseDAO;
import com.tcs.toolultimate.vo.Account;
import com.tcs.toolultimate.vo.Employee;
import com.tcs.toolultimate.vo.EmployeeHierarchyVO;
import com.tcs.toolultimate.vo.Level;
import com.tcs.toolultimate.vo.Origin;
import com.tcs.toolultimate.vo.Project;
import com.tcs.toolultimate.vo.Role;
import com.tcs.toolultimate.vo.SubProjects;
import com.tcs.toolultimate.vo.UmbrellaProject;
import com.tcs.toolultimate.vo.UserLogin;

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
	
	
	private Employee mergeEmployeeRecordsforSame(List<Employee> employeeRecords){
		Employee employee = null;
		EmployeeHierarchyVO orgVO = null;
	
		
		if(employeeRecords != null && employeeRecords.size() >0){
			employee = employeeRecords.get(0);
			List<EmployeeHierarchyVO> allOrgs = new ArrayList<EmployeeHierarchyVO>();
			
			for(Employee emp: employeeRecords){
				orgVO = new EmployeeHierarchyVO();
				orgVO.setRoleId(emp.getRoleId());
				orgVO.setRole(emp.getRole());
				orgVO.setLevel(emp.getLevel());
				/*orgVO.setOriginId(emp.getOriginId());
				orgVO.setOriginName(emp.getOriginName());*/
				allOrgs.add(orgVO);
			}
			/*employee.setAllOrgs(allOrgs);*/
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
	
	
	public List<Origin> getAllOrignis(String selectedlevel, List<String> creatorOrgId,String creatorOrgLevel){
		List<Origin> origins = null;
		String originFieldId = "";
		String parentOriginFieldId = "";
		Class<?> objectType = null;
		Origin origin = null;
		StringBuffer orgStr = new StringBuffer("");
		
		if (Constants.LEVEL_VALUE_ACOOUNT.equals(selectedlevel)) {
			originFieldId =  Constants.COLUMN_NAME_ACCOUNT_ID;
			objectType = Account.class;
		} else if (Constants.LEVEL_VALUE_UMBRELLA.equals(selectedlevel)) {
			originFieldId =  Constants.COLUMN_NAME_UMBRELLA_PROJ_ID;
			parentOriginFieldId = Constants.COLUMN_NAME_ACCOUNT_ID;
			objectType = UmbrellaProject.class;
		} else if (Constants.LEVEL_VALUE_PROJECT.equals(selectedlevel)) {
			originFieldId =  Constants.COLUMN_NAME_PROJ_ID;
			parentOriginFieldId = Constants.COLUMN_NAME_UMBRELLA_PROJ_ID;
			objectType = Project.class;
		} else if (Constants.LEVEL_VALUE_SUB_PROJECT.equals(selectedlevel)) {
			originFieldId =  Constants.COLUMN_NAME_SUB_PROJ_ID;
			parentOriginFieldId = Constants.COLUMN_NAME_PROJ_ID;
			objectType = SubProjects.class;
		}
		
		if(Constants.LEVEL_VALUE_ACOOUNT.equals(creatorOrgLevel)){
			parentOriginFieldId = Constants.COLUMN_NAME_ACCOUNT_ID;
		} else if (Constants.LEVEL_VALUE_UMBRELLA.equals(creatorOrgLevel)) {
			parentOriginFieldId = Constants.COLUMN_NAME_UMBRELLA_PROJ_ID;
		}else if (Constants.LEVEL_VALUE_PROJECT.equals(creatorOrgLevel)) {
			parentOriginFieldId = Constants.COLUMN_NAME_PROJ_ID;
		}else if (Constants.LEVEL_VALUE_SUB_PROJECT.equals(creatorOrgLevel)) {
			parentOriginFieldId = Constants.COLUMN_NAME_SUB_PROJ_ID;
		}
		
		Map<String,String> matchCriteria = new HashMap<String,String>();
		if(creatorOrgId != null && creatorOrgId.size()>0){
			for(String orgId : creatorOrgId) {
				if(orgStr.length() == 0) {
					orgStr.append(orgId);
				}else {
					orgStr.append(",").append(orgId);
				}
			}
		}
		matchCriteria.put(parentOriginFieldId, orgStr.toString());
		
		
		List<?> results = baseDAO.fetchDistinctRecords(Constants.ACCOUNT_STORE_NAME, matchCriteria, originFieldId, objectType);
		
		if(results != null && results.size()>0){
			origins = new ArrayList<Origin>();
			for(Object o  : results){
				if(o instanceof Account){
					origin = new Origin();
					origin.setOriginId(((Account) o).getAccountId());
					origin.setOriginName(((Account) o).getAccountName());
				}else if(o instanceof UmbrellaProject){
					origin = new Origin();
					origin.setOriginId(((UmbrellaProject) o).getUmbrellaProjectId());
					origin.setOriginName(((UmbrellaProject) o).getUmbrellaProjectName());
				}else if(o instanceof Project){
					origin = new Origin();
					origin.setOriginId(((Project) o).getProjectId());
					origin.setOriginName(((Project) o).getProjectName());
				}else if(o instanceof SubProjects){
					origin = new Origin();
					origin.setOriginId(((SubProjects) o).getSubProjectId());
					origin.setOriginName(((SubProjects) o).getSubProjectName());
				}
				
				origins.add(origin);
			}
		}
		
		
		return origins;
	}
}


