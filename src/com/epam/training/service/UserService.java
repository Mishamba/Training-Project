package com.epam.training.service;

import java.util.List;

import com.epam.training.exception.ServiceException;
import com.epam.training.model.entity.User;
import com.epam.training.model.role.Role;

public interface UserService {
	void changeUserRole(String username, Role role) throws ServiceException;
	List<User> findUsers() throws ServiceException;
}
