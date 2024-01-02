package com.example.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "comment")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long comment_id;
    private String objet;
    private String contenu;
    private Boolean estSignale;
    private String raisonSign;

    @ManyToOne
    @JoinColumn(name = "livre_id")
    private Livre livre;

    @ManyToOne
    @JoinColumn(name = "username")
    private User adherent;
}
