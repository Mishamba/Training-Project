package com.epam.training.model.parameter;

public enum Permission {
	DELETE_FILE("delete_file"),
	CHANGE_ROLE("change_role");
	
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
