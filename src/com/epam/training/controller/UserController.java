package com.epam.training.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.epam.training.controller.reponse.Response;
import com.epam.training.exception.ControllerException;
import com.epam.training.exception.ServiceException;
import com.epam.training.model.dto.UserDTO;
import com.epam.training.model.entity.User;
import com.epam.training.model.parameter.Role;
import com.epam.training.service.UserService;

@RestController("/")
public class UserController {
	
	private UserService userService;
	
	@Autowired
	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}

	@GetMapping("/users")
	public List<UserDTO> findUsers(@RequestParam("usersStart") int userStart, @RequestParam("usersEnd") int userEnd) throws ControllerException {
		try {
			List<User> users = userService.findUsers(userStart, userEnd);
			
			return users.stream().map(User::toDTO).collect(Collectors.toList());
		} catch (ServiceException exception) {
			throw new ControllerException("can't get users", exception);
		}
	}
	
	@PostMapping("/{username}/change-role")
	public ResponseEntity<Response> makeAdmin(@PathVariable("username") String username) throws ControllerException {
		try {
			userService.changeUserRole(username, Role.ADMIN);
			
			return new ResponseEntity<Response>(new Response("made user ADMIN"), HttpStatus.OK);
		} catch (ServiceException exception) {
			throw new ControllerException("can't change role", exception);
		}
	}
	
	@GetMapping("/users-count")
	public Long findUsersCount() {
		return userService.findUsersCount();
	}
}
