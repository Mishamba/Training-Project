package test.com.epam.training.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Optional;
import java.util.stream.Stream;

import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;

import com.epam.training.exception.ServiceException;
import com.epam.training.model.entity.User;
import com.epam.training.model.parameter.Role;
import com.epam.training.repository.UserRepository;
import com.epam.training.service.UserService;
import com.epam.training.service.impl.UserServiceImpl;

public class TestUserService {
	@ParameterizedTest
	@MethodSource("provideUsernameRoleUserCorrect")
	public void testChangeUserRoleCorrect(String username, Role role, User userToReturn) {
		UserRepository userRepository = Mockito.mock(UserRepository.class);
		
		Mockito.when(userRepository.findById(username)).thenReturn(Optional.of(userToReturn));
		
		UserService userService = new UserServiceImpl(userRepository);
		
		try {
			userService.changeUserRole(username, role);
			assertTrue(true);
		} catch (ServiceException exception) {
			fail("exception \n" + exception.toString());
		}
	}
	
	private Stream<Arguments> provideUsernameRoleUserCorrect() {
		return Stream.of(
				Arguments.of("smth", Role.ADMIN, new User("smth", Role.USER))
		);
	}

	@Test
	public void testChangeUserRoleIncorrect() {
		UserRepository userRepository = Mockito.mock(UserRepository.class);
		
		Mockito.when(userRepository.findById("smth")).thenReturn(Optional.empty());
		
		UserService userService = new UserServiceImpl(userRepository);
		
		try {
			userService.changeUserRole("smth", Role.ADMIN);
		} catch(ServiceException exception) {
			assertEquals("can't find user", exception.getMessage());
		}
	}
	
	@ParameterizedTest
	@MethodSource("provideUsernameAndUser")
	public void testFindUserByUsername(String username, User expectedUser) {
		UserRepository userRepository = Mockito.mock(UserRepository.class);
		
		Mockito.when(userRepository.findById(username)).thenReturn(Optional.of(expectedUser));
		
		UserService userService = new UserServiceImpl(userRepository);
		
		try {
			User actualUser = userService.findUserByUsername(username);
			
			assertEquals(expectedUser, actualUser);
		} catch (ServiceException exception) {
			fail("got exception " + exception.toString());
		}
	}
	
	private Stream<Arguments> provideUsernameAndUser() {
		return Stream.of(
				Arguments.of("smth", new User("smth", Role.ADMIN))
		);
	}
}
