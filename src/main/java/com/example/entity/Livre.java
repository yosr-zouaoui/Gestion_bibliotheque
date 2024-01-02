package com.example.entity;

import java.util.ArrayList;
import java.util.Set;

import org.hibernate.mapping.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "livre")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Livre {


		  public enum Langue {
		        ARABE,
		        FRANCAIS,
		        ANGLAIS
		    }


	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long livre_id;
	    private String titre;
	    private int anneePublication;
	    private String ISBN;
	    @Enumerated(EnumType.STRING)
	    private Langue langue;
	    private String description;
	    private int quantite;
	    private double amendeParJour;
	    private String genre;

	    @ManyToOne
	    @JoinColumn(name = "auteur_id")
	    private Auteur auteur;
	    
	    @OneToMany(mappedBy = "livre", cascade = CascadeType.ALL)
	    private Set<Comment> comment;

}
