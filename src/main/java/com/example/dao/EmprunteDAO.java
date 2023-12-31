package com.example.dao;

import java.util.Collections;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.example.entity.Emprunt;
import com.example.entity.Emprunt.Status;
import com.example.entity.Livre;

import jakarta.persistence.EntityManager;

public class EmprunteDAO implements IEmprunteDAO {

	//When workign with spring MVC and Hibernate, in DAO implementation we inject the session factory to get the current session.
	//In JPA we use entity manager to get current session
	//Autowired : allows Spring to resolve and inject collaborating beans into our bean
	@Autowired
	private EntityManager entityManger;
	
	@Override
	public Page<Emprunt> getEmprunts(Pageable pageable, String keyword) {
		int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Emprunt> Emprunts;
        List<Emprunt> list;
        if(keyword != null)
        {
        	Emprunts = this.search(keyword);
        }
        else
        {
			Session currentSession = entityManger.unwrap(Session.class);
			Query<Emprunt> query = currentSession.createQuery("from emprunt", Emprunt.class);
			Emprunts = query.getResultList();
		}
        
        if (Emprunts.size() < startItem) list = Collections.emptyList();
        else {
            int toIndex = Math.min(startItem + pageSize, Emprunts.size());
            list = Emprunts.subList(startItem, toIndex);
        }
        Page<Emprunt> EmpruntPage = new PageImpl<Emprunt>(list, PageRequest.of(currentPage, pageSize), Emprunts.size());

        return EmpruntPage;
	}

	@Override
	public Emprunt getEmprunt(Long id) 
	{
		try
		{
			Session currentSession = entityManger.unwrap(Session.class);
			Emprunt emprunt = currentSession.get(Emprunt.class, id);
			return emprunt;
		}
		catch(Exception ex){System.out.println(ex.getMessage());return null;}
	}

	@Override
	public void saveEmprunt(Emprunt Emprunt) 
	{
		try
		{
			Emprunt.setStatus(Status.EN_ATTENTE);
			Session currentSession = entityManger.unwrap(Session.class);
			currentSession.merge(Emprunt);
		}
		catch(Exception ex) {System.out.println(ex.getMessage());}
	}

	@Override
	public void deleteEmprunt(Long id) {
		try
		{
			Session currentSession = entityManger.unwrap(Session.class);
			Emprunt emprunt = currentSession.get(Emprunt.class, id);
			currentSession.remove(emprunt);
		}
		catch(Exception ex){System.out.println(ex.getMessage());}
	}

	@Override
	public List<Emprunt> getEmpuntsByUserId(Long userId) {
	    try {
	        Session currentSession = entityManger.unwrap(Session.class);

	        Query<Emprunt> query = currentSession.createQuery(
	            "FROM Emprunt e WHERE e.user.id = :userId",
	            Emprunt.class
	        );
	        query.setParameter("userId", userId);

	        List<Emprunt> emprunts = query.list();
	        return emprunts;
	    } catch (Exception ex) {
	        System.out.println(ex.getMessage());
	        return null;
	    }
	}


	@Override
	public List<Emprunt> search(String keyword) {
		try {
		Session currentSession = entityManger.unwrap(Session.class);
	    if(Emprunt.Status.EMPRUNTE.toString().equals(keyword.toUpperCase()) || Emprunt.Status.EN_ATTENTE.toString().equals(keyword.toUpperCase()) || Emprunt.Status.REFUSE.toString().equals(keyword.toUpperCase()) || Emprunt.Status.RETOURNE.toString().equals(keyword.toUpperCase()) )
	    {
	    Emprunt.Status statusEnum = Emprunt.Status.valueOf(keyword.toUpperCase());
	    Query<Emprunt> query = currentSession.createQuery(
	            "SELECT p FROM Emprunt p WHERE " +
	            "p.status = :keyword " ,
	            Emprunt.class
	    );
	    query.setParameter("keyword", statusEnum);
	    List<Emprunt> emprunt = query.getResultList();
	    return emprunt;
	    }
	    else return null;
		}
		catch (Exception ex) {
	        System.out.println(ex.getMessage());
	        return null;
	    }
	}

	@Override
	public void updateEmpruntStatus(String status, Long id) {
		try 
		{
			Emprunt emp = this.getEmprunt(id);
			emp.setStatus(Status.valueOf(status.toUpperCase()));
			Session currentSession = entityManger.unwrap(Session.class);
			currentSession.merge(emp);
		}
		catch (Exception ex) {
	        System.out.println(ex.getMessage());
	    }
	}

}
