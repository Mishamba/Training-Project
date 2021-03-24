package com.epam.training.service.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.epam.training.model.entity.User;
import com.epam.training.repository.UserRepository;

public class CustomUserDetailsService implements UserDetailsService {
	
	private UserRepository userRepository;
	
	@Autowired
	public CustomUserDetailsService(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> optionalUser =  userRepository.findById(username);
		if (optionalUser.isEmpty()) {
			throw new UsernameNotFoundException("no user found");
		}
		
		return optionalUser.get();
	}
}
