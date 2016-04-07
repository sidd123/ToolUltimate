package com.tcs.toolultimate.vo;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="ACCOUNT")
public class UmbrellaProject {

	private String accountId;
	private String accountName;
	private String umbrellaProjectId;
	private String umbrellaProjectName;
	
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
	
	
	
}
