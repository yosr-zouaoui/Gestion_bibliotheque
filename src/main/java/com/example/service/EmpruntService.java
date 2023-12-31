package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.dao.EmprunteDAO;
import com.example.dao.LiverDAO;
import com.example.entity.Emprunt;

import jakarta.transaction.Transactional;

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
	public List<Emprunt> getEmpuntsByUserId(Long id) {return empruntDAO.getEmpuntsByUserId(id);}

	@Override
	@Transactional
	public List<Emprunt> search(String keyword) {return empruntDAO.search(keyword);}

	@Override
	@Transactional
	public void updateEmpruntStatus(String status, Long id) {empruntDAO.updateEmpruntStatus(status, id);}

}
