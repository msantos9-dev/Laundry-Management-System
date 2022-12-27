package com.global77.lms.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.global77.lms.contoller.dto.UserRegistrationDto;
import com.global77.lms.model.User;

public interface UserService extends UserDetailsService {
	User save(UserRegistrationDto registrationDto);
	void saveManager(User user);
	List<User> getAllUsers();
	List<User> getAllOwners();
	Page<User> findPaginated(int pageNo, int pageSize, String sortField,
			String sortDirection);
	void deleteUserById(long id);
	User getUserById(long id);
}
