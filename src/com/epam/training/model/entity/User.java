package com.epam.training.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

import org.springframework.data.annotation.Id;

import com.epam.training.model.role.Role;

@Entity
public class User {
	// TODO : check jpa configuration
	@Id
	@Column(name = "username")
	private String username;

	@Column(name = "role")
	private Role role;

	public User() {
		super();
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the role
	 */
	public Role getRole() {
		return role;
	}

	/**
	 * @param role the role to set
	 */
	public void setRole(Role role) {
		this.role = role;
	}

	@Override
	public int hashCode() {
		int prime = 5;
		int hash = 1;
		hash *= this.getUsername().hashCode() * prime;
		hash *= this.getRole().hashCode() * prime;
		
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		
		if (obj == null || obj.getClass() != this.getClass()) {
			return false;
		}
		
		User that = (User) obj;
		
		return this.getRole().equals(that) &&
				this.getUsername().equals(that);
	}

	@Override
	public String toString() {
		return "User { " +
				"username: \'" + this.getUsername() + "\', " +
				"role: " + this.getRole().name() + 
				" }";
	}
}
