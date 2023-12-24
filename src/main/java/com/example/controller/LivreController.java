package com.example.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.entity.Auteur;
import com.example.entity.Livre;
import com.example.service.AuteurService;
import com.example.service.LivreService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api")
@Api(value = "Livre Controller")
public class LivreController {

	@Autowired
    private LivreService livreService;
	
	@GetMapping("/livres")
    @ApiOperation(value = "Cette opération nous permet de recevoir la liste des livres")
    public ModelAndView getLivres(Model model) {
        List<Livre> livres = livreService.getLivres();
        model.addAttribute("livres", livres);
        return new ModelAndView("ListesLivre", model.asMap());
    }
	
}
