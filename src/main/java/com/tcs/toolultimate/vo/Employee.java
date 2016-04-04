package com.tcs.toolultimate.vo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection="EMPLOYEE_DETAILS")
public class Employee{
	
	
	@Id
	private String id;
	
	private String building_name;
	
	private String asset_id;
	
	private String project_name;
	
	private String status;
	
	private String emergency_contact_name;
	
	private String desk_phone;
	
	private String visa_end_date;
	
	private String Selected;
	
	private String emergency_contact_number;
	
	
	private String card_no;
	
	
	private String current_location;
	
	private String employee_name;
	
	private String machine_ip;
	
	
	
	
	private String email;
	
	
	private String total_exp;
	

	private String grade;
	

	private String skill_set;
	

	private String cubicle_no;
	

	private String employee_id;
	
	

	private String working_application;
	

	private String mobile;
	

	private String Username;
	

	private String Password;
	
	
	
	
	

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getEmergency_contact_name() {
		return emergency_contact_name;
	}

	public void setEmergency_contact_name(String emergency_contact_name) {
		this.emergency_contact_name = emergency_contact_name;
	}

	public String getDesk_phone() {
		return desk_phone;
	}

	public void setDesk_phone(String desk_phone) {
		this.desk_phone = desk_phone;
	}

	public String getVisa_end_date() {
		return visa_end_date;
	}

	public void setVisa_end_date(String visa_end_date) {
		this.visa_end_date = visa_end_date;
	}

	public String getSelected() {
		return Selected;
	}

	public void setSelected(String selected) {
		Selected = selected;
	}

	public String getEmergency_contact_number() {
		return emergency_contact_number;
	}

	public void setEmergency_contact_number(String emergency_contact_number) {
		this.emergency_contact_number = emergency_contact_number;
	}

	public String getCard_no() {
		return card_no;
	}

	public void setCard_no(String card_no) {
		this.card_no = card_no;
	}

	public String getCurrent_location() {
		return current_location;
	}

	public void setCurrent_location(String current_location) {
		this.current_location = current_location;
	}

	public String getEmployee_name() {
		return employee_name;
	}

	public void setEmployee_name(String employee_name) {
		this.employee_name = employee_name;
	}

	public String getMachine_ip() {
		return machine_ip;
	}

	public void setMachine_ip(String machine_ip) {
		this.machine_ip = machine_ip;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTotal_exp() {
		return total_exp;
	}

	public void setTotal_exp(String total_exp) {
		this.total_exp = total_exp;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getSkill_set() {
		return skill_set;
	}

	public void setSkill_set(String skill_set) {
		this.skill_set = skill_set;
	}

	public String getCubicle_no() {
		return cubicle_no;
	}

	public void setCubicle_no(String cubicle_no) {
		this.cubicle_no = cubicle_no;
	}

	public String getEmployee_id() {
		return employee_id;
	}

	public void setEmployee_id(String employee_id) {
		this.employee_id = employee_id;
	}

	public String getWorking_application() {
		return working_application;
	}

	public void setWorking_application(String working_application) {
		this.working_application = working_application;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getUsername() {
		return Username;
	}

	public void setUsername(String username) {
		Username = username;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBuilding_name() {
		return building_name;
	}

	public void setBuilding_name(String building_name) {
		this.building_name = building_name;
	}

	public String getAsset_id() {
		return asset_id;
	}

	public void setAsset_id(String asset_id) {
		this.asset_id = asset_id;
	}

	public String getProject_name() {
		return project_name;
	}

	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}
	
	
	
	
}
