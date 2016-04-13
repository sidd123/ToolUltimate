package com.tcs.toolultimate.vo;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import com.tcs.toolultimate.config.Constants;

@Document(collection=Constants.ULTIMATEDOCUMENT_STORE_NAME)
public class Project {

	private String projectId;
	private String projectName;
	
	private String projectCreatedOn;
	
	private String projectCreatedBy;
	
	private List<SubProjects> subProjects;
	
	
	

	/**
	 * @return the subProjects
	 */
	public List<SubProjects> getSubProjects() {
		return subProjects;
	}

	/**
	 * @param subProjects the subProjects to set
	 */
	public void setSubProjects(List<SubProjects> subProjects) {
		this.subProjects = subProjects;
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
	 * @return the projectCreatedOn
	 */
	public String getProjectCreatedOn() {
		return projectCreatedOn;
	}

	/**
	 * @param projectCreatedOn the projectCreatedOn to set
	 */
	public void setProjectCreatedOn(String projectCreatedOn) {
		this.projectCreatedOn = projectCreatedOn;
	}

	/**
	 * @return the projectCreatedBy
	 */
	public String getProjectCreatedBy() {
		return projectCreatedBy;
	}

	/**
	 * @param projectCreatedBy the projectCreatedBy to set
	 */
	public void setProjectCreatedBy(String projectCreatedBy) {
		this.projectCreatedBy = projectCreatedBy;
	}
	
	
}
