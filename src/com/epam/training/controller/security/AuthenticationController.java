package com.epam.training.controller.security;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.security.PermitAll;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.epam.training.controller.reponse.Response;
import com.epam.training.controller.security.jwt.JwtTokenProvider;
import com.epam.training.exception.ServiceException;
import com.epam.training.model.entity.User;
import com.epam.training.model.entity.security.AuthenticationRequestDTO;
import com.epam.training.service.UserService;

@RestController
public class AuthenticationController {
	private AuthenticationManager authenticationManager;
	private UserService userService;
	private JwtTokenProvider jwtTokenProvider;
	
	@Autowired
	public AuthenticationController(AuthenticationManager authenticationManager, UserService userService,
			JwtTokenProvider jwtTokenProvider) {
		super();
		this.authenticationManager = authenticationManager;
		this.userService = userService;
		this.jwtTokenProvider = jwtTokenProvider;
	}

	@PermitAll
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody AuthenticationRequestDTO authenticationRequestDTO) {
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(
							authenticationRequestDTO.getUsername(), 
							authenticationRequestDTO.getPassword()));

			User user = userService.findUserByUsername(authenticationRequestDTO.getUsername());
			String token = jwtTokenProvider.createToken(user.getUsername(), user.getRole().name());
			Map<String, String> response = new HashMap<>();
			response.put("token", token);
			response.put("user", user.getUsername());
			response.put("role", user.getRole().name());
			
			return ResponseEntity.ok(response);
		} catch (AuthenticationException | ServiceException exception) {
			return new ResponseEntity<Response>(new Response("can't authorize"), HttpStatus.UNAUTHORIZED);
		}
	}
}
