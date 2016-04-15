package com.tcs.toolultimate.vo;

import org.springframework.data.mongodb.core.mapping.Document;

import com.tcs.toolultimate.config.Constants;

@Document(collection=Constants.ULTIMATEDOCUMENT_STORE_NAME)
public class HierarchyVO {

	private String accountId;
	private String umbrellaProjectId;
	private String projectId;
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
	
	
}
