package com.example.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.entity.Emprunt;

public interface IEmprunteDAO {

	Page<Emprunt> getEmprunts(Pageable pageable, String keyword);
	Emprunt getEmprunt(Long id);
	void saveEmprunt(Emprunt Emprunt);
	void deleteEmprunt(Long id);
	
	List<Emprunt> getEmpuntsByUserId(Long id);
	List<Emprunt> search(String keyword, String username);
	
	void updateEmpruntStatus (String status, Long id);
	Page<Emprunt> getEmpruntsByUsername(Pageable pageable, String keyword, String username);
}
