package com.example.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.entity.User;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class UserDAO implements IUserDAO{

	//When workign with spring MVC and Hibernate, in DAO implementation we inject the session factory to get the current session.
	//In JPA we use entity manager to get current session
	//Autowired : allows Spring to resolve and inject collaborating beans into our bean
	@Autowired
	private EntityManager entityManger;
	 @PersistenceContext
	    private EntityManager entityManager;
	@Override
	public List<User> getUsers(String authority) {
		 try {
		        Session currentSession = entityManger.unwrap(Session.class);

		        // Use a join query to fetch users based on their roles
		        Query<User> query = currentSession.createQuery("SELECT u FROM User u JOIN Authority r ON u.username = r.user.username WHERE r.authority LIKE CONCAT('%', :authority, '%')", User.class);
		        query.setParameter("authority", authority);

		        List<User> userList = query.getResultList();
		        return userList;
		    } catch (Exception ex) {
		        System.out.println(ex.getMessage());
		        return null;
		    }
		}

	@Override
	public User getUser(int id) {
		try
		{
			Session currentSession = entityManger.unwrap(Session.class);
			User user = currentSession.get(User.class, id);
			return user;
		}
		catch(Exception ex){System.out.println(ex.getMessage());return null;}
	}

	@Override
	public void saveUser(User user) {
		try
		{
			Session currentSession = entityManger.unwrap(Session.class);
			currentSession.merge(user);
		}
		catch(Exception ex) {System.out.println(ex.getMessage());}

	}

	@Override
	public void deleteUser(int id) {
		try
		{
			Session currentSession = entityManger.unwrap(Session.class);
			User user = currentSession.get(User.class, id);
			currentSession.remove(user);
		}
		catch(Exception ex){System.out.println(ex.getMessage());}
		}
	
	 public User getUserByUsername(String username) {
	        return entityManager.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class)
	                .setParameter("username", username)
	                .getSingleResult();
	    }
}
