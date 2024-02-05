package com.robertocosta.dscommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.robertocosta.dscommerce.entities.User;
import com.robertocosta.dscommerce.repositories.UserRepository;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	private UserRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User result = repository.searchUserAndRolesByEmail(username);
		if(result == null) {
			throw new UsernameNotFoundException("User not found");
		}
		
		User user = new User();
		user.setEmail(result.getEmail());
		user.setPassword(result.getPassword());
		user.setRoles(result.getRoles());
		return user;
	}
}