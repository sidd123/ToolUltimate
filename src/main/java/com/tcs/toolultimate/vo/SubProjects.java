package com.tcs.toolultimate.vo;

import org.springframework.data.mongodb.core.mapping.Document;

import com.tcs.toolultimate.config.Constants;

@Document(collection=Constants.ULTIMATEDOCUMENT_STORE_NAME)
public class SubProjects {

	private String subProjectName;
	private String subProjectId;
	private String subProjectCreatedBy;
	private String subProjectCreatedOn;
	
	
	
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
