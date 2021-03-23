package com.epam.training.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.training.exception.ServiceException;
import com.epam.training.model.entity.User;
import com.epam.training.model.role.Role;
import com.epam.training.repository.UserRepository;
import com.epam.training.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	private final UserRepository userRepository;
	
	@Autowired
	public UserServiceImpl(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	@Override
	public void changeUserRole(String username, Role role) throws ServiceException {
		Optional<User> optionalUser = userRepository.findById(username);
		
		if (optionalUser.isEmpty()) {
			throw new ServiceException("can't find user");
		}
		
		User user = optionalUser.get();
		user.setRole(role);
		
		userRepository.save(user);
	}

	@Override
	public List<User> findUsers() throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

}
