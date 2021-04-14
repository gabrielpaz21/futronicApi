package com.telcomsis.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.telcomsis.model.User;
import com.telcomsis.repository.UserRepository;
import com.telcomsis.service.base.BaseService;

@Service
public class UserService extends BaseService<User, Long, UserRepository> {
	
	@Autowired
	private UserRepository userRepository;

	public Optional<User> findByUsername(String username) {
		
		return userRepository.findByUsername(username);
	}
}
