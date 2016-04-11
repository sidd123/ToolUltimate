package com.tcs.toolultimate.vo;

import org.springframework.data.mongodb.core.mapping.Document;

import com.tcs.toolultimate.config.Constants;

@Document(collection=Constants.ACCOUNT_STORE_NAME)
public class Project {

	private String accountId;
	private String accountName;
	private String umbrellaProjectId;
	private String umbrellaProjectName;
	private String projectId;
	private String projectName;
	
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getUmbrellaProjectName() {
		return umbrellaProjectName;
	}
	public void setUmbrellaProjectName(String umbrellaProjectName) {
		this.umbrellaProjectName = umbrellaProjectName;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	
	
	
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public String getUmbrellaProjectId() {
		return umbrellaProjectId;
	}
	public void setUmbrellaProjectId(String umbrellaProjectId) {
		this.umbrellaProjectId = umbrellaProjectId;
	}
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}


	
	
}
