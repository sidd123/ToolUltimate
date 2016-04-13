package com.tcs.toolultimate.vo;


import java.util.Set;


public class UserRoles {
	
	private String role;
	private String roleId;

	private String level;
	
	private Set<String> originIds;
	
	/**
	 * @return the originIds
	 */
	public Set<String> getOriginIds() {
		return originIds;
	}
	/**
	 * @param originIds the originIds to set
	 */
	public void setOriginIds(Set<String> originIds) {
>>>>>>> dff79e0 Changes for Data structire change
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
