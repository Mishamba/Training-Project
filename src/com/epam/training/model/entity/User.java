package com.epam.training.model.entity;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;

import org.springframework.data.annotation.Id;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.epam.training.model.dto.UserDTO;
import com.epam.training.model.parameter.Role;

@Entity
public class User implements UserDetails {
	@Id
	@Column(name = "username")
	private String username;
	
	@Column(name = "password")
	private String password;

	@Column(name = "role")
	private Role role;

	public User() {
		super();
	}
	
	public User(String username, Role role) {
		super();
		this.username = username;
		this.role = role;
	}

	public UserDTO toDTO() {
		return new UserDTO(this.username, this.role);
	}

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
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

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
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.role.getAuthority();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
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
		return this.getUsername().equals(that.getUsername()) &&
				this.getRole().equals(that.getRole());
	}

	@Override
	public int hashCode() {
		int prime = 5;
		int hash = 1;
		hash *= this.getUsername().hashCode() * prime;
		hash *= this.getRole().name().hashCode() * prime;
		
		return hash;
	}

	@Override
	public String toString() {
		return "User { " +
				"username: \'" + this.getUsername() + "\', " +
				"password: \'" + this.getPassword() + "\', " +
				"role: " + this.getRole().name() + 
				" }";
	}
}
