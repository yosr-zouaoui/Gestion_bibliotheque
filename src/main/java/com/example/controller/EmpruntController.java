package com.example.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.query.Param;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.entity.Auteur;
import com.example.entity.Emprunt;
import com.example.entity.Livre;
import com.example.service.AuteurService;
import com.example.service.EmpruntService;
import com.example.service.LivreService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/emprunts")
@Api(value = "Emprunt Controller")
public class EmpruntController {

	@Autowired
    private EmpruntService empruntService;	
	
	@GetMapping(value = "/")
    @ApiOperation(value = "Cette opération nous permet de recevoir la liste des emprunts")
    public ModelAndView getEmprunts(Model model,@RequestParam(defaultValue = "0") int page,@Param("keyword") String keyword ) 
	{

        Page<Emprunt> empruntPage = empruntService.getEmprunts(PageRequest.of(page,5), keyword);
        model.addAttribute("emprunts", empruntPage);
        model.addAttribute("currentPage", page);
       
        return new ModelAndView("EmpruntTemplates/ListesEmprunt", model.asMap());
    }
	
	@GetMapping(value = "/{username}")
    @ApiOperation(value = "Cette opération nous permet de recevoir la liste des emprunts par utilisateur")
    public ModelAndView getEmpruntsByUserId(Model model,@RequestParam(defaultValue = "0") int page,@Param("keyword") String keyword, @PathVariable String username ) 
	{
		Page<Emprunt> empruntPage = empruntService.getEmpruntsByUsername(PageRequest.of(page,5), keyword, username);
        model.addAttribute("emprunts", empruntPage);
        model.addAttribute("currentPage", page);
       
        return new ModelAndView("EmpruntTemplates/ListesEmprunt", model.asMap());
    }
	
    
}
