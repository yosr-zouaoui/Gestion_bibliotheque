package com.example.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.entity.Auteur;

import jakarta.persistence.EntityManager;

@Repository
public class AuteurDAO implements IAuteurDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Auteur> getAuteurs() {
        try {
            Session currentSession = entityManager.unwrap(Session.class);
            Query<Auteur> query = currentSession.createQuery("from Auteur", Auteur.class);
            return query.getResultList();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    @Override
    public Auteur getAuteur(Long id) {
        try {
            Session currentSession = entityManager.unwrap(Session.class);
            return currentSession.get(Auteur.class, id);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    @Override
    public void saveAuteur(Auteur auteur) {
        try {
            Session currentSession = entityManager.unwrap(Session.class);
            currentSession.merge(auteur);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void deleteAuteur(Long id) {
        try {
            Session currentSession = entityManager.unwrap(Session.class);
            Auteur auteur = currentSession.get(Auteur.class, id);
            if (auteur != null) {
                currentSession.remove(auteur);
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
