package com.ms.user.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ms.user.models.UserModel;
import com.ms.user.producers.UserProducer;
import com.ms.user.repository.UserRepository;

@Service
public class UserService {
	
	final UserRepository userRepository;
	final UserProducer userProducer;
	
	public UserService(UserRepository userRepository, UserProducer userProducer) {
		this.userRepository = userRepository;
		this.UserProducer = userProducer;
	}
	
	@Transactional
	public UserModel save(UserModel userModel) {
		userModel = userRepository.save(userModel);
		userProducer.publishMessageEmail(userModel);

		return userModel;
	}
	

}
