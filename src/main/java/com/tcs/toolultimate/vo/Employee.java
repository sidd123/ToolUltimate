package com.tcs.toolultimate.vo;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import com.tcs.toolultimate.config.Constants;


@Document(collection=Constants.EMPLOYEE_STORE_NAME)
public class Employee{
	
	
	@Id
	private String id;
	
	private String employeeId="";
	
	private String employeeName="";
	
	private String cardNo="";
	
	private String mobile="";
	
	private String email="";
	
	private String role="";
	
	private String roleId = "";
	
	private String level="";
	
	private String originId="";
	
	private String originName="";
	
	
	private String totalExp="";
	
	
	private String grade="";
	
	private String skillSet="";
	
	private String currentLocation="";
	
	private String buildingName="";
	

	private String cubicleNo="";
	

	private String machineIp="";
	

	private String assetId="";
	

	private String deskPhone="";	
	

	private String emergencyContactName="";
	
	private String emergencyContactNumber="";
	
	private String username="";
	
	private String password="";
	
	private String visaStartDate = "";
	

	private String visaEndDate = "";
	
	@Transient
	private List<EmployeeHierarchyVO> allOrgs;
	

	public String getOriginName() {
		return originName;
	}


	public void setOriginName(String originName) {
		this.originName = originName;
	}


	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}


	public String getRoleId() {
		return roleId;
	}


	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}


	public String getLevel() {
		return level;
	}


	public void setLevel(String level) {
		this.level = level;
	}

	


	public String getOriginId() {
		return originId;
	}


	public void setOriginId(String originId) {
		this.originId = originId;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getEmployeeId() {
		return employeeId;
	}


	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}


	public String getEmployeeName() {
		return employeeName;
	}


	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}


	public String getCardNo() {
		return cardNo;
	}


	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}


	public String getMobile() {
		return mobile;
	}


	public void setMobile(String mobile) {
		this.mobile = mobile;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getTotalExp() {
		return totalExp;
	}


	public void setTotalExp(String totalExp) {
		this.totalExp = totalExp;
	}


	public String getGrade() {
		return grade;
	}


	public void setGrade(String grade) {
		this.grade = grade;
	}


	public String getSkillSet() {
		return skillSet;
	}


	public void setSkillSet(String skillSet) {
		this.skillSet = skillSet;
	}


	public String getCurrentLocation() {
		return currentLocation;
	}


	public void setCurrentLocation(String currentLocation) {
		this.currentLocation = currentLocation;
	}


	public String getBuildingName() {
		return buildingName;
	}


	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}


	public String getCubicleNo() {
		return cubicleNo;
	}


	public void setCubicleNo(String cubicleNo) {
		this.cubicleNo = cubicleNo;
	}


	public String getMachineIp() {
		return machineIp;
	}


	public void setMachineIp(String machineIp) {
		this.machineIp = machineIp;
	}


	public String getAssetId() {
		return assetId;
	}


	public void setAssetId(String assetId) {
		this.assetId = assetId;
	}


	public String getDeskPhone() {
		return deskPhone;
	}


	public void setDeskPhone(String deskPhone) {
		this.deskPhone = deskPhone;
	}


	public String getEmergencyContactName() {
		return emergencyContactName;
	}


	public void setEmergencyContactName(String emergencyContactName) {
		this.emergencyContactName = emergencyContactName;
	}


	public String getEmergencyContactNumber() {
		return emergencyContactNumber;
	}


	public void setEmergencyContactNumber(String emergencyContactNumber) {
		this.emergencyContactNumber = emergencyContactNumber;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getVisaStartDate() {
		return visaStartDate;
	}


	public void setVisaStartDate(String visaStartDate) {
		this.visaStartDate = visaStartDate;
	}


	public String getVisaEndDate() {
		return visaEndDate;
	}


	public void setVisaEndDate(String visaEndDate) {
		this.visaEndDate = visaEndDate;
	}


	public List<EmployeeHierarchyVO> getAllOrgs() {
		return allOrgs;
	}


	public void setAllOrgs(List<EmployeeHierarchyVO> allOrgs) {
		this.allOrgs = allOrgs;
	}
	
}
