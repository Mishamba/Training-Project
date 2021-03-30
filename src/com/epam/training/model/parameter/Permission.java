package com.epam.training.model.parameter;

public enum Permission {
	DELETE_FILE("delete_file"),
	CHANGE_ROLE("change_role"),
	GET_USER_BROWSER_PAGE("get_user_browser_page");
	
	private String permission;

	private Permission(String permission) {
		this.permission = permission;
	}

	/**
	 * @return the permission
	 */
	public String getPermission() {
		return permission;
	}
}
