package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.dao.EmprunteDAO;
import com.example.dao.LiverDAO;
import com.example.entity.Emprunt;

import jakarta.transaction.Transactional;

@Service
public class EmpruntService implements IEmpuntService {

	public static final int PRODUCT_PER_PAGE = 5;
	@Autowired
	private EmprunteDAO empruntDAO;
	
	@Override
	@Transactional
	public Page<Emprunt> getEmprunts(Pageable pageable, String keyword) {return empruntDAO.getEmprunts(pageable, keyword);}

	@Override
	@Transactional
	public Emprunt getEmprunt(Long id) {return empruntDAO.getEmprunt(id);}

	@Override
	public void saveEmprunt(Emprunt Emprunt) {empruntDAO.saveEmprunt(Emprunt);}

	@Override
	@Transactional
	public void deleteEmprunt(Long id) {empruntDAO.deleteEmprunt(id);}

	@Override
	@Transactional
	public Page<Emprunt> getEmpruntsByUsername(Pageable pageable, String keyword, String username){ return empruntDAO.getEmpruntsByUsername(pageable, keyword, username);}

	@Override
	@Transactional
	public List<Emprunt> search(String keyword, String username) {return empruntDAO.search(keyword,username);}

	@Override
	@Transactional
	public void updateEmpruntStatus(String status, Long id) {empruntDAO.updateEmpruntStatus(status, id);}

	@Override
	public List<Emprunt> getEmpuntsByUserId(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
