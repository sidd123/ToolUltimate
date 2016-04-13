package com.tcs.toolultimate.vo;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import com.tcs.toolultimate.config.Constants;

@Document(collection=Constants.ULTIMATEDOCUMENT_STORE_NAME)
public class UmbrellaProject {

	private String umbrellaProjectId;
	private String umbrellaProjectName;
	private String umbrellaProjectCreatedOn;
	private String umbrellaProjectCreatedBy;
	
	private List<Project> projects;

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
	 * @return the umbrellaProjectCreatedOn
	 */
	public String getUmbrellaProjectCreatedOn() {
		return umbrellaProjectCreatedOn;
	}

	/**
	 * @param umbrellaProjectCreatedOn the umbrellaProjectCreatedOn to set
	 */
	public void setUmbrellaProjectCreatedOn(String umbrellaProjectCreatedOn) {
		this.umbrellaProjectCreatedOn = umbrellaProjectCreatedOn;
	}

	/**
	 * @return the umbrellaProjectCreatedBy
	 */
	public String getUmbrellaProjectCreatedBy() {
		return umbrellaProjectCreatedBy;
	}

	/**
	 * @param umbrellaProjectCreatedBy the umbrellaProjectCreatedBy to set
	 */
	public void setUmbrellaProjectCreatedBy(String umbrellaProjectCreatedBy) {
		this.umbrellaProjectCreatedBy = umbrellaProjectCreatedBy;
	}

	/**
	 * @return the projects
	 */
	public List<Project> getProjects() {
		return projects;
	}

	/**
	 * @param projects the projects to set
	 */
	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}
	
	
	
	
	
}
