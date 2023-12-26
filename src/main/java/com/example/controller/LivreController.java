package com.example.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
@RequestMapping("/")
@Api(value = "Livre Controller")
public class LivreController {

	@Autowired
    private LivreService livreService;
	
	@Autowired
    private AuteurService auteurService;
	
	@GetMapping(value = "/livres", params = { "page", "size" })
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
    public ModelAndView getCreateLivreForm(Model model) {
        Livre nouvelLivre = new Livre();
        List<Auteur> auteurs = new ArrayList<Auteur>();
        Auteur auteur = new Auteur();
        auteurs = auteurService.getAuteurs();
        model.addAttribute("nouvelLivre", nouvelLivre);
        model.addAttribute("auteurs", auteurs);
        model.addAttribute("auteur", auteur);
        return new ModelAndView("LivreTemplates/ajoutLivre", model.asMap());
    }


    @PostMapping("/createLivre")
    @ApiOperation(value = "Cette opération nous permet d'ajouter un livre")
    public ModelAndView createLivre(@ModelAttribute("nouvelLivre") Livre livre, @ModelAttribute("auteur") Auteur auteur ) {
        Auteur selectedAuteur = auteurService.getAuteur(auteur.getAuteur_id());
        livre.setAuteur(selectedAuteur);
        livreService.saveLivre(livre);
        return new ModelAndView("redirect:/livres/" + livre.getLivre_id());
    }

	
	@GetMapping("/updateLivreForm/{id}")
    @ApiOperation(value = "Afficher le formulaire de mise à jour d'un livre")
    public ModelAndView getUpdateLivreForm(@PathVariable Long id, Model model) {
        // Retrieve the existing Auteur object from the database based on the ID
        Livre livre = livreService.getLivre(id);

        // Add the existing Auteur object to the model
        model.addAttribute("livre", livre);

        // Return the Thymeleaf view name (updateAuteurForm.html)
        return new ModelAndView("LivreTemplates/updateLivre", model.asMap());
    }
    
    
    @PutMapping("/updateLivre/{id}")
    @ApiOperation(value = "Cette opération nous permet de modifier les données d'un livre choisi")
    public ModelAndView updateLivre(@PathVariable Long id, @ModelAttribute("existingLivre") Livre livre) {
        // Assurez-vous que l'auteur existe avant de le mettre à jour
        Livre existingLivre = livreService.getLivre(id);
        if (existingLivre != null) {
        	livre.setLivre_id(id);
            livreService.saveLivre(livre);
        }
        return new ModelAndView("redirect:/livres/{id}");
    }
    
    @GetMapping("/livres/{id}")
    @ApiOperation(value = "Cette opération nous permet de retourner un livre demandé")
    public ModelAndView getLivre( Model model, @PathVariable Long id) {
    	model.addAttribute("livre", livreService.getLivre(id));
        return new ModelAndView("LivreTemplates/livreDetails", model.asMap()); 
    }
    
    @DeleteMapping("/livres/delete/{id}")
    @ApiOperation(value = "Cette opération nous permet de supprimer un livre précis")
    public ModelAndView deleteLivre(@PathVariable Long id) {
        livreService.deleteLivre(id);
        return new ModelAndView("redirect:/livres");
    }
	
}
