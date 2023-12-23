package com.example.entity;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "auteur")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Auteur {
	   public enum Status {
        DECEDE,
        VIVANT
    }
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long auteur_id;

    private String nom;

    private String prenom;

    private String dateNaissance;

    private String nationalite;

    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToMany(mappedBy = "auteur")
    private Set<Livre> livres;


}

