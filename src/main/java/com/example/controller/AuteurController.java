package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.entity.Auteur;
import com.example.service.AuteurService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/auteurs")
@Api(value = "Auteur Controller")
public class AuteurController {

    @Autowired
    private AuteurService auteurService;

    @GetMapping
    @ApiOperation(value = "Cette opération nous permet de recevoir la liste des auteurs")
    public List<Auteur> getAuteurs() {
        return auteurService.getAuteurs();
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Cette opération nous permet de retourner un auteur demandé")
    public Auteur getAuteur(@PathVariable Long id) {
        return auteurService.getAuteur(id);
    }

    @PostMapping
    @ApiOperation(value = "Cette opération nous permet de créer un auteur")
    public Auteur createAuteur(@RequestBody Auteur auteur) {
        auteurService.saveAuteur(auteur);
        return auteur;
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Cette opération nous permet de modifier les données d'un auteur choisi")
    public Auteur updateAuteur(@PathVariable Long id, @RequestBody Auteur auteur) {
        // Assurez-vous que l'auteur existe avant de le mettre à jour
        Auteur existingAuteur = auteurService.getAuteur(id);
        if (existingAuteur != null) {
            auteur.setAuteur_id(id);
            auteurService.saveAuteur(auteur);
        }
        return auteur;
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Cette opération nous permet de supprimer un auteur précis")
    public String deleteAuteur(@PathVariable Long id) {
        auteurService.deleteAuteur(id);
        return "Auteur has been deleted with id: " + id;
    }
}
