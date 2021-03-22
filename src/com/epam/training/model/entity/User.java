package com.epam.training.model.entity;

import com.epam.training.model.role.Role;

public class User {
	private int id;
	private String username;
	private Role role;

	public User(int id, String username, Role role) {
		super();
		this.id = id;
		this.username = username;
		this.role = role;
	}

	public User(String username, Role role) {
		super();
		this.username = username;
		this.role = role;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		
		if (obj == null || obj.getClass() != this.getClass()) {
			return false;
		}
		
		User that = (User) obj;
		return this.getId() == that.getId() &&
				this.getUsername().equals(that.getUsername()) &&
				this.getRole().equals(that.getRole());
	}

	@Override
	public int hashCode() {
		int prime = 5;
		int hash = 1;
		hash *= this.getId() * prime;
		hash *= this.getUsername().hashCode() * prime;
		hash *= this.getRole().name().hashCode() * prime;
		
		return hash;
	}

	@Override
	public String toString() {
		return "User{" +
				"id=" + this.getId() + 
				", username='" + this.getUsername() + "\'" +
				", role='" + this.getRole().name() + "\'" +
				"}";
	}
}
