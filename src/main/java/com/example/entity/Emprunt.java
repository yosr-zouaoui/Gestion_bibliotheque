package com.example.entity;

import java.util.Date;

import com.example.entity.Auteur.Status;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "emprunt")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Emprunt {

	  public enum Status {
	        EN_ATTENTE,
	        RETOURNE,
	        EMPRUNTE,
	        REFUSE
	    }

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long emprunt_id;

	    @Enumerated(EnumType.STRING)
	    private Status status;

	    @Temporal(TemporalType.DATE)
	    private Date dateDebut;

	    @Temporal(TemporalType.DATE)
	    private Date dateFin;

	    @Temporal(TemporalType.DATE)
	    private Date dateRetour;

	    private int nbCopies;

	    private double amendes;

	    @ManyToOne
	    @JoinColumn(name = "livre_id")
	    private Livre livre;
	    
	    @ManyToOne
	    @JoinColumn(name = "user_id")
	    private Adherent adherent;

	    // Constructors

	 	
}
