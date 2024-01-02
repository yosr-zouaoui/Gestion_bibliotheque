package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.dao.UserDAO;
import com.example.entity.User;

@Service
public class UserService implements IUserService{

	@Autowired
	private UserDAO userDAO;

	//@Transactional délégue la gestion des transactions au conteneur IOC
	@Transactional
	@Override
	public List<User> getUsers(String role) {return userDAO.getUsers(role);}

	@Transactional
	@Override
	public User getUser(int id) {return userDAO.getUser(id);}

	@Transactional
	@Override
	public void saveUser(User user) {userDAO.saveUser(user);}

	@Transactional
	@Override
	public void deleteUser(int id) {userDAO.deleteUser(id);}

	@Transactional
	@Override
	public User getUserByUsername(String username) {
        return userDAO.getUserByUsername(username);
    }
}
