package com.devdnp.socio.controllers;

import java.util.List;
//import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devdnp.socio.models.User;
import com.devdnp.socio.repositories.UserRepository;
import com.devdnp.socio.services.UserService;

@RestController
public class UserController {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserService userService;
	
	
	
	@GetMapping("/api/users")
	public List<User> getUser() {
		
		List<User> users = userRepository.findAll();	
		
		return users;
	}
	
	@GetMapping("/api/users/{userId}")
	public User getUserById(@PathVariable("userId") Integer id) throws Exception {
		
		User user = userService.findUserById(id);
		
		return user;
	}
	

	
	@PutMapping("/api/users/{userId}")
	public User updateUser(@RequestBody User user, @PathVariable Integer userId) throws Exception {
		
		User updatedUser = userService.updateUser(user, userId);
		return updatedUser;
	}
	
//	@DeleteMapping("/users/{userId}")
//	public String deleteUser(@PathVariable Integer userId) throws Exception {
//		
//		Optional<User> userToDelete = userRepository.findById(userId);
//		
//		if(userToDelete.isEmpty()) {
//			throw new Exception("No user exist with id: "+userId);
//		}
//		
//		userRepository.delete(userToDelete.get());
//		
//		return "User deleted successfully with id: "+userId;
//	}
	
	
	@PutMapping("/api/users/follow/{userId1}/{userId2}")
	public User followUserHandler(@PathVariable Integer userId1,@PathVariable Integer userId2) throws Exception {
		
		User user = userService.followUser(userId1, userId2);
		return user;
	}
	
	@GetMapping("/api/users/search")
	public List<User> searchUser(@RequestParam("query") String query){
		
		List<User> users = userService.searchUser(query);
		
		return users;
	}
	
}
