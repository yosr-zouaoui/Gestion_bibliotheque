package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.dao.IAuteurDAO;
import com.example.entity.Auteur;

@Service
public class AuteurService implements IAuteurService {

    @Autowired
    private IAuteurDAO auteurDAO;

    @Override
    @Transactional
    public List<Auteur> getAuteurs() {
        return auteurDAO.getAuteurs();
    }

    @Override
    @Transactional
    public Auteur getAuteur(Long id) {
        return auteurDAO.getAuteur(id);
    }

    @Override
    @Transactional
    public void saveAuteur(Auteur auteur) {
        auteurDAO.saveAuteur(auteur);
    }

    @Override
    @Transactional
    public void deleteAuteur(Long id) {
        auteurDAO.deleteAuteur(id);
    }
}
