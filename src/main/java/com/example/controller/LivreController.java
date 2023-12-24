package com.example.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
@RequestMapping("/api/livres")
@Api(value = "Livre Controller")
public class LivreController {

	@Autowired
    private LivreService livreService;
	
	@GetMapping
    @ApiOperation(value = "Cette opération nous permet de recevoir la liste des livres")
    public ModelAndView getLivres(Model model) 
	{
        List<Livre> livres = livreService.getLivres();
        model.addAttribute("livres", livres);
        return new ModelAndView("LivreTemplates/ListesLivre", model.asMap());
    }
	
	/* Form create   */ 
    @GetMapping("/createLivreForm")
    @ApiOperation(value = "Afficher le formulaire d'ajout d'un livre")
    public ModelAndView getCreateAuteurForm(Model model) {
        Livre nouvelLivre = new Livre();
        model.addAttribute("nouvelLivre", nouvelLivre);
        return new ModelAndView("LivreTemplates/ajoutLivre", model.asMap());
    }
    /* Action Create*/
	@PostMapping("/createLivre")
    @ApiOperation(value = "Cette opération nous permet d'ajouter un livre")
    public ModelAndView createAuteur(@ModelAttribute("nouvelLivre") Livre livre) 
	{
        livreService.saveLivre(livre);
        return new ModelAndView("redirect:/api/livres/"+livre.getLivre_id());
    }
	
    @GetMapping("/{id}")
    @ApiOperation(value = "Cette opération nous permet de retourner un livre demandé")
    public ModelAndView getLivre( Model model, @PathVariable Long id) {
    	model.addAttribute("livre", livreService.getLivre(id));
        return new ModelAndView("LivreTemplates/livreDetails", model.asMap()); 
    }
	
}
