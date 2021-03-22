package com.epam.training.model.dto;

import com.epam.training.model.role.Role;

public class UserDTO {
	private String username;
	private Role role;

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @return the role
	 */
	public Role getRole() {
		return role;
	}
}
