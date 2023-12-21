package com.example.entity;

import java.util.Date;
import java.util.Set;

import com.example.entity.Emprunt.Status;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "admin")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Admin extends User {

    private double salaire;

    @Temporal(TemporalType.DATE)
    private Date dateEmbauche;
}
