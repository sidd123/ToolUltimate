package com.tcs.toolultimate.vo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection="EMPLOYEE_DETAILS")
public class Employee{
	
	
	@Id
	private String id;
	
	private String employeeId="";
	
	private String employeeName="";
	
	private String cardNo="";
	
	private String mobile="";
	
	private String email="";
	
	private String projectName="";
	
	private String projectId = "";
	
	private String subProjectName="";
	
	private String subProjectId="";
	
	
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


	public String getProjectName() {
		return projectName;
	}


	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}


	public String getProjectId() {
		return projectId;
	}


	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}


	public String getSubProjectName() {
		return subProjectName;
	}


	public void setSubProjectName(String subProjectName) {
		this.subProjectName = subProjectName;
	}


	public String getSubProjectId() {
		return subProjectId;
	}


	public void setSubProjectId(String subProjectId) {
		this.subProjectId = subProjectId;
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
	
	
	
	
	
	
	
}
