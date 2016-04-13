package com.tcs.toolultimate.vo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.tcs.toolultimate.config.Constants;

@Document(collection=Constants.ULTIMATEDOCUMENT_STORE_NAME)
public class Account {

	@Id
	private String id;
	private String accountId;
	private String accountName;
	private String accountCreatedOn;
	private String accountCreatedBy;
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
	 * @return the accountCreatedOn
	 */
	public String getAccountCreatedOn() {
		return accountCreatedOn;
	}
	/**
	 * @param accountCreatedOn the accountCreatedOn to set
	 */
	public void setAccountCreatedOn(String accountCreatedOn) {
		this.accountCreatedOn = accountCreatedOn;
	}
	/**
	 * @return the accountCreatedBy
	 */
	public String getAccountCreatedBy() {
		return accountCreatedBy;
	}
	/**
	 * @param accountCreatedBy the accountCreatedBy to set
	 */
	public void setAccountCreatedBy(String accountCreatedBy) {
		this.accountCreatedBy = accountCreatedBy;
	}
		
}
