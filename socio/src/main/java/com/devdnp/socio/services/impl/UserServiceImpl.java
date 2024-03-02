package com.devdnp.socio.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devdnp.socio.models.User;
import com.devdnp.socio.repositories.UserRepository;
import com.devdnp.socio.services.UserService;



@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	UserRepository userRepository;

	@Override
	public User registerUser(User user) {
		
		User newUser = new User();
		newUser.setEmail(user.getEmail());
		newUser.setFirstName(user.getFirstName());
		newUser.setLastName(user.getLastName());
		newUser.setPassword(user.getPassword());
		newUser.setId(user.getId());
		
		User savedUser = userRepository.save(newUser);
		
		return savedUser;
	}

	@Override
	public User findUserById(Integer userId) throws Exception {
		Optional<User> user = userRepository.findById(userId);
		
		if(user.isPresent()) {
			return user.get();
		}
		
		throw new Exception("No user exist with the id: " + userId);
		
	}

	@Override
	public User findUserByEmail(String email) {
		
		User user = userRepository.findByEmail(email);
		return user;
	}

	@Override
	public User followUser(Integer userId1, Integer userId2) throws Exception {
		
		User user1 = findUserById(userId1);
		User user2 = findUserById(userId2);
		
		user2.getFollowers().add(user1.getId());
		user1.getFollowing().add(user2.getId());
		
		userRepository.save(user1);
		userRepository.save(user2);
		
		return user1;
	}

	@Override
	public User updateUser(User user, Integer userId) throws Exception {
		Optional<User> userToUpdate = userRepository.findById(userId);
		
		if(userToUpdate.isEmpty()) {
			throw new Exception("No user exist with id: "+userId);
		}
		
		User newUser = userToUpdate.get();
		
		
		
		if(user.getFirstName()!=null) {
			newUser.setFirstName(user.getFirstName());
		}
		if(user.getLastName()!=null) {
			newUser.setLastName(user.getLastName());
		}
		if(user.getEmail()!=null) {
			newUser.setEmail(user.getEmail());
		}
		if(user.getPassword()!=null) {
			newUser.setPassword(user.getPassword());
		}
		
		User updatedUser = userRepository.save(newUser);
		
		return updatedUser;
	}

	@Override
	public List<User> searchUser(String query) {
		
		return userRepository.searchUser(query);
	}
	
	

}
