package com.global77.lms.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.global77.lms.contoller.dto.UserRegistrationDto;
import com.global77.lms.model.User;

public interface UserService extends UserDetailsService {
	User save(UserRegistrationDto registrationDto);
}
