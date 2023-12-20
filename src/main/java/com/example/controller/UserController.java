 package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.User;
import com.example.service.UserService;


@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/users")
	public List<User> getUsers(){return userService.getUsers();}
	
	@PostMapping("/user")
	public User postUser (@RequestBody User user) 
	{userService.saveUser(user);return user;}
	
	@GetMapping("/user/{id}")
	public User getUserById(@PathVariable int id) 
	{
		User user = userService.getUser(id);
		if(user == null) throw new RuntimeException("Employee with id: "+id+" is not found!");
		return userService.getUser(id);
	}

	@DeleteMapping("/user/{id}")
	public String deleteUserById (@PathVariable int id){ userService.deleteUser(id);return "User has been deleted with id: "+id;}

	@PutMapping("/user")
	public User updateUser (@RequestBody User user) {userService.saveUser(user);return user;}
}
