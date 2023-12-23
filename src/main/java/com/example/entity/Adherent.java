package com.example.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "adherent")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Adherent extends User {

    @Temporal(TemporalType.DATE)
    private Date abonnementDateExp;

}
