package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.example.entity.Auteur;
import com.example.service.AuteurService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.ui.Model;

@RestController
@RequestMapping("/api/auteurs")
@Api(value = "Auteur Controller")
public class AuteurController {

    @Autowired
    private AuteurService auteurService;

    @GetMapping
    @ApiOperation(value = "Cette opération nous permet de recevoir la liste des auteurs")
    public ModelAndView getAuteurs(Model model) {
        List<Auteur> auteurs = auteurService.getAuteurs();
        model.addAttribute("auteurs", auteurs);
        return new ModelAndView("listesAuteur", model.asMap());
    }

    
    @GetMapping("/{id}")
    @ApiOperation(value = "Cette opération nous permet de retourner un auteur demandé")
    public Auteur getAuteur(@PathVariable Long id) {
        return auteurService.getAuteur(id);
    }

   /* Form create   */ 
    @GetMapping("/createAuteurForm")
    @ApiOperation(value = "Afficher le formulaire de création d'un auteur")
    public ModelAndView getCreateAuteurForm(Model model) {
        Auteur nouvelAuteur = new Auteur();
        model.addAttribute("nouvelAuteur", nouvelAuteur);
        return new ModelAndView("ajoutAuteur", model.asMap());
    }
    
    
    @PostMapping("/createAuteur")
    @ApiOperation(value = "Cette opération nous permet de créer un auteur")
    public ModelAndView createAuteur(@ModelAttribute("nouvelAuteur") Auteur auteur) {
        auteurService.saveAuteur(auteur);
        return new ModelAndView("redirect:/api/auteurs");
    }

    
    /* update form */ 
    @GetMapping("/updateAuteurForm/{id}")
    @ApiOperation(value = "Afficher le formulaire de mise à jour d'un auteur")
    public ModelAndView getUpdateAuteurForm(@PathVariable Long id, Model model) {
        // Retrieve the existing Auteur object from the database based on the ID
        Auteur existingAuteur = auteurService.getAuteur(id);

        // Add the existing Auteur object to the model
        model.addAttribute("existingAuteur", existingAuteur);

        // Return the Thymeleaf view name (updateAuteurForm.html)
        return new ModelAndView("updateForm", model.asMap());
    }
    @PutMapping("/update/{id}")
    @ApiOperation(value = "Cette opération nous permet de modifier les données d'un auteur choisi")
    public ModelAndView updateAuteur(@PathVariable Long id, @ModelAttribute("existingAuteur") Auteur auteur) {
        // Assurez-vous que l'auteur existe avant de le mettre à jour
        Auteur existingAuteur = auteurService.getAuteur(id);
        if (existingAuteur != null) {
            auteur.setAuteur_id(id);
            auteurService.saveAuteur(auteur);
        }
        return new ModelAndView("redirect:/api/auteurs");
    }

    @DeleteMapping("delete/{id}")
    @ApiOperation(value = "Cette opération nous permet de supprimer un auteur précis")
    public ModelAndView deleteAuteur(@PathVariable Long id) {
        auteurService.deleteAuteur(id);
        return new ModelAndView("redirect:/api/auteurs");
    }
}
