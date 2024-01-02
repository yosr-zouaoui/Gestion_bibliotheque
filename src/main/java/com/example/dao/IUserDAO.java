package com.example.dao;

import java.util.List;

import com.example.entity.User;

public interface IUserDAO {
	List<User> getUsers(String role);
	User getUser(int id);
	void saveUser(User user);
	void deleteUser(int id);
	User getUserByUsername(String username);
}
