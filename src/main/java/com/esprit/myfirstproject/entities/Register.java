package com.esprit.myfirstproject.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Register {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    private String nom;
    private String prenom;
    private String email;
    private int telephone;
    private String adresse;

    private LocalDate dateCreation;

    @ManyToOne
    @JoinColumn(name = "cours_id")

    Cours cours ;
    @Transient
    private Long coursId; // Ajoutez cette propriétéq
}
