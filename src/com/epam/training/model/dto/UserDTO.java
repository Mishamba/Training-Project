package com.epam.training.model.dto;

import com.epam.training.model.parameter.Role;

public class UserDTO {
	private String username;
	private Role role;
	
	public UserDTO() {
		super();
	}

	public UserDTO(String username, Role role) {
		super();
		this.username = username;
		this.role = role;
	}

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
