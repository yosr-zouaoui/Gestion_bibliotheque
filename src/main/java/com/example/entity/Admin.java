package com.example.entity;

import java.util.Date;

import com.example.entity.Emprunt.Status;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity

@DiscriminatorValue("ADMIN")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Admin extends User {

    private double salaire;

    @Temporal(TemporalType.DATE)
    private Date dateEmbauche;
}
