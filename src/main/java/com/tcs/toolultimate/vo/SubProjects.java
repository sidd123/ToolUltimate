package com.tcs.toolultimate.vo;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="ACCOUNT")
public class SubProjects {

	private String accountId;
	private String accountName;
	private String umbrellaProjectId;
	private String umbrellaProjectName;
	private String projectId;
	private String projectName;
	private String subProjectName;
	private String subProjectId;
	private String subProjectCreatedBy;
	private String subProjectCreatedOn;
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
	 * @return the accountName
	 */
	public String getAccountName() {
		return accountName;
	}
	/**
	 * @param accountName the accountName to set
	 */
	public void setAccountName(String accountName) {
		this.accountName = accountName;
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
	 * @return the umbrellaProjectName
	 */
	public String getUmbrellaProjectName() {
		return umbrellaProjectName;
	}
	/**
	 * @param umbrellaProjectName the umbrellaProjectName to set
	 */
	public void setUmbrellaProjectName(String umbrellaProjectName) {
		this.umbrellaProjectName = umbrellaProjectName;
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
	 * @return the projectName
	 */
	public String getProjectName() {
		return projectName;
	}
	/**
	 * @param projectName the projectName to set
	 */
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	/**
	 * @return the subProjectName
	 */
	public String getSubProjectName() {
		return subProjectName;
	}
	/**
	 * @param subProjectName the subProjectName to set
	 */
	public void setSubProjectName(String subProjectName) {
		this.subProjectName = subProjectName;
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
	 * @return the subProjectCreatedBy
	 */
	public String getSubProjectCreatedBy() {
		return subProjectCreatedBy;
	}
	/**
	 * @param subProjectCreatedBy the subProjectCreatedBy to set
	 */
	public void setSubProjectCreatedBy(String subProjectCreatedBy) {
		this.subProjectCreatedBy = subProjectCreatedBy;
	}
	/**
	 * @return the subProjectCreatedOn
	 */
	public String getSubProjectCreatedOn() {
		return subProjectCreatedOn;
	}
	/**
	 * @param subProjectCreatedOn the subProjectCreatedOn to set
	 */
	public void setSubProjectCreatedOn(String subProjectCreatedOn) {
		this.subProjectCreatedOn = subProjectCreatedOn;
	}
	
	
	
	
}
