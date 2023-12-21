package com.example.entity;

import java.util.Date;

import org.hibernate.mapping.List;

import com.example.entity.Auteur.Status;

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

@DiscriminatorValue("ADHERENT")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Adherent extends User {

    @Temporal(TemporalType.DATE)
    private Date abonnementDateExp;

}
