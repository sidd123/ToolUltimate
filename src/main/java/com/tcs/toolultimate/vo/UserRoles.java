package com.tcs.toolultimate.vo;

import java.util.List;


public class UserRoles {
	
	private String role;
	private String roleId;

	private String level;
	
	private List<String> originIds;
	
	/**
	 * @return the originIds
	 */
	public List<String> getOriginIds() {
		return originIds;
	}
	/**
	 * @param originIds the originIds to set
	 */
	public void setOriginIds(List<String> originIds) {
		this.originIds = originIds;
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
	
	
	
	
}
