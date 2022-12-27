package com.global77.lms.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.global77.lms.contoller.dto.UserRegistrationDto;
import com.global77.lms.model.Role;
import com.global77.lms.model.User;
import com.global77.lms.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public UserServiceImpl(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	@Override
	public User save(UserRegistrationDto registrationDto) {

		User user = new User(registrationDto.getFirstName(),
				registrationDto.getLastName(), registrationDto.getEmail(),
				passwordEncoder.encode(registrationDto.getPassword()),

				// Arrays.asList(new Role("ROLE_CUSTOMER"), new
				// Role("ROLE_ADMIN"),
				// new Role("ROLE_MANAGER"), new Role("ROLE_OWNER")),
				Arrays.asList(new Role("ROLE_MANAGER")),

				true);

		return userRepository.save(user);
	}

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {

		User user = userRepository.findByEmail(username);
		if (user == null) {
			throw new UsernameNotFoundException(
					"Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(
				user.getEmail(), user.getPassword(),
				mapRolesToAuthorities(user.getRoles()));
	}

	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(
			Collection<Role> roles) {
		return roles.stream()
				.map(role -> new SimpleGrantedAuthority(role.getName()))
				.collect(Collectors.toList());
	}

	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}

	@Override
	public Page<User> findPaginated(int pageNo, int pageSize, String sortField,
			String sortDirection) {
		// TODO Auto-generated method stub
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name())
				? Sort.by(sortField).ascending()
				: Sort.by(sortField).descending();

		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return this.userRepository.findAll(pageable);
	}

}