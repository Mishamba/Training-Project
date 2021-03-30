package com.epam.training.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.epam.training.exception.ServiceException;
import com.epam.training.model.entity.User;
import com.epam.training.model.parameter.Role;

public interface UserService {
	void changeUserRole(String username, Role role) throws ServiceException;
	User findUserByUsername(String username) throws ServiceException;
	List<User> findUsers(int pageStart, int pageEnd) throws ServiceException;
	long findUsersCount();
}
