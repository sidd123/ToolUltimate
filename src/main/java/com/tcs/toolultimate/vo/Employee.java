package com.tcs.toolultimate.vo;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import com.tcs.toolultimate.config.Constants;


@Document(collection=Constants.EMPLOYEE_STORE_NAME)
public class Employee implements Cloneable{
	
	
	@Id
	private String id;
	
	private String employeeId="";
	
	private String employeeName="";
	
	private String cardNo="";
	
	private String mobile="";
	
	private String email="";
	
	
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
	
	private String role = "";
	
	private String roleId = "";
	
	private String level = "";
	
	private String accountId = "";
	
	private String umbrellaProjectId = "";
	
	private String projectId = "";
	
	private String subProjectId = "";
	
	@Transient
	private UserRoles userRoles;
	
	
	
	

	/**
	 * @return the userRoles
	 */
	public UserRoles getUserRoles() {
		return userRoles;
	}

	/**
	 * @param userRoles the userRoles to set
	 */
	public void setUserRoles(UserRoles userRoles) {
		this.userRoles = userRoles;
	}

	/**
	 * @return the role
	 */
	public String getRole() {
		return role;
	}

	/**
	 * @param role the role to set
	 */
	public void setRole(String role) {
		this.role = role;
	}

	/**
	 * @return the roleId
	 */
	public String getRoleId() {
		return roleId;
	}

	/**
	 * @param roleId the roleId to set
	 */
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	/**
	 * @return the level
	 */
	public String getLevel() {
		return level;
	}

	/**
	 * @param level the level to set
	 */
	public void setLevel(String level) {
		this.level = level;
	}

	/**
	 * @return the accountId
	 */
	public String getAccountId() {
		return accountId;
	}

	/**
	 * @param accountId the accountId to set
	 */
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	/**
	 * @return the umbrellaProjectId
	 */
	public String getUmbrellaProjectId() {
		return umbrellaProjectId;
	}

	/**
	 * @param umbrellaProjectId the umbrellaProjectId to set
	 */
	public void setUmbrellaProjectId(String umbrellaProjectId) {
		this.umbrellaProjectId = umbrellaProjectId;
	}

	/**
	 * @return the projectId
	 */
	public String getProjectId() {
		return projectId;
	}

	/**
	 * @param projectId the projectId to set
	 */
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	/**
	 * @return the subProjectId
	 */
	public String getSubProjectId() {
		return subProjectId;
	}

	/**
	 * @param subProjectId the subProjectId to set
	 */
	public void setSubProjectId(String subProjectId) {
		this.subProjectId = subProjectId;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the employeeId
	 */
	public String getEmployeeId() {
		return employeeId;
	}

	/**
	 * @param employeeId the employeeId to set
	 */
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	/**
	 * @return the employeeName
	 */
	public String getEmployeeName() {
		return employeeName;
	}

	/**
	 * @param employeeName the employeeName to set
	 */
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	/**
	 * @return the cardNo
	 */
	public String getCardNo() {
		return cardNo;
	}

	/**
	 * @param cardNo the cardNo to set
	 */
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	/**
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * @param mobile the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the totalExp
	 */
	public String getTotalExp() {
		return totalExp;
	}

	/**
	 * @param totalExp the totalExp to set
	 */
	public void setTotalExp(String totalExp) {
		this.totalExp = totalExp;
	}

	/**
	 * @return the grade
	 */
	public String getGrade() {
		return grade;
	}

	/**
	 * @param grade the grade to set
	 */
	public void setGrade(String grade) {
		this.grade = grade;
	}

	/**
	 * @return the skillSet
	 */
	public String getSkillSet() {
		return skillSet;
	}

	/**
	 * @param skillSet the skillSet to set
	 */
	public void setSkillSet(String skillSet) {
		this.skillSet = skillSet;
	}

	/**
	 * @return the currentLocation
	 */
	public String getCurrentLocation() {
		return currentLocation;
	}

	/**
	 * @param currentLocation the currentLocation to set
	 */
	public void setCurrentLocation(String currentLocation) {
		this.currentLocation = currentLocation;
	}

	/**
	 * @return the buildingName
	 */
	public String getBuildingName() {
		return buildingName;
	}

	/**
	 * @param buildingName the buildingName to set
	 */
	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}

	/**
	 * @return the cubicleNo
	 */
	public String getCubicleNo() {
		return cubicleNo;
	}

	/**
	 * @param cubicleNo the cubicleNo to set
	 */
	public void setCubicleNo(String cubicleNo) {
		this.cubicleNo = cubicleNo;
	}

	/**
	 * @return the machineIp
	 */
	public String getMachineIp() {
		return machineIp;
	}

	/**
	 * @param machineIp the machineIp to set
	 */
	public void setMachineIp(String machineIp) {
		this.machineIp = machineIp;
	}

	/**
	 * @return the assetId
	 */
	public String getAssetId() {
		return assetId;
	}

	/**
	 * @param assetId the assetId to set
	 */
	public void setAssetId(String assetId) {
		this.assetId = assetId;
	}

	/**
	 * @return the deskPhone
	 */
	public String getDeskPhone() {
		return deskPhone;
	}

	/**
	 * @param deskPhone the deskPhone to set
	 */
	public void setDeskPhone(String deskPhone) {
		this.deskPhone = deskPhone;
	}

	/**
	 * @return the emergencyContactName
	 */
	public String getEmergencyContactName() {
		return emergencyContactName;
	}

	/**
	 * @param emergencyContactName the emergencyContactName to set
	 */
	public void setEmergencyContactName(String emergencyContactName) {
		this.emergencyContactName = emergencyContactName;
	}

	/**
	 * @return the emergencyContactNumber
	 */
	public String getEmergencyContactNumber() {
		return emergencyContactNumber;
	}

	/**
	 * @param emergencyContactNumber the emergencyContactNumber to set
	 */
	public void setEmergencyContactNumber(String emergencyContactNumber) {
		this.emergencyContactNumber = emergencyContactNumber;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the visaStartDate
	 */
	public String getVisaStartDate() {
		return visaStartDate;
	}

	/**
	 * @param visaStartDate the visaStartDate to set
	 */
	public void setVisaStartDate(String visaStartDate) {
		this.visaStartDate = visaStartDate;
	}

	/**
	 * @return the visaEndDate
	 */
	public String getVisaEndDate() {
		return visaEndDate;
	}

	/**
	 * @param visaEndDate the visaEndDate to set
	 */
	public void setVisaEndDate(String visaEndDate) {
		this.visaEndDate = visaEndDate;
	}
	
	
	public Object clone(){  
	    try{  
	        return super.clone();  
	    }catch(Exception e){ 
	        return null; 
	    }
	}
	
}
