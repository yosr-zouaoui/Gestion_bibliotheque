package com.example.dao;


import java.util.List;

import com.example.entity.Auteur;

public interface IAuteurDAO {
	 
		List<Auteur> getAuteurs();
	    Auteur getAuteur(Long id);
	    void saveAuteur(Auteur auteur);
	    void deleteAuteur(Long id);
}
