package com.epam.training.model.parameter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Arrays;
import java.util.ArrayList;

public enum Role {
	USER(new ArrayList<>()),
	ADMIN(Arrays.asList(
			Permission.DELETE_FILE, 
			Permission.CHANGE_ROLE,
			Permission.GET_USER_BROWSER_PAGE
	));
	
	private List<Permission> permissions;

	private Role(List<Permission> permissions) {
		this.permissions = permissions;
	}

	/**
	 * @return the permissions
	 */
	public List<Permission> getPermissions() {
		return permissions;
	}
	
	public List<SimpleGrantedAuthority> getAuthority() {
		return this.getPermissions().stream().
				map(permission -> new SimpleGrantedAuthority(permission.getPermission())).
				collect(Collectors.toList());
	}
}
