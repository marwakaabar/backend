package com.esprit.myfirstproject.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE) //rend tous mles attributs private
//@JsonIdentityInfo(
//        generator = ObjectIdGenerators.PropertyGenerator.class,
//        property = "numSkieur"
//)// permet d'empêcher que le fichier fasse une boucle infini
public class Skieur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    int numSkieur;

    @ManyToMany(mappedBy = "skieurs") //la classe Piste gère la classe Skieur c pk il y a un mapped by dans la relation ManyToMany
    Set<Piste> pistes;


    @OneToOne(cascade = CascadeType.ALL)//relation 1..1 (avec composition <=> CascadeType.ALL) entre SKIEUR et ABONNEMENT
    Abonnement abonnement;

    @JsonIgnore
    @OneToMany(mappedBy = "skieur", fetch = FetchType.EAGER)// la classe skieur est la classe child(mapped by), elle est géréé par la classe InscriptionRepository
    //l'attribut skieur sera donc situé dans la classe Inscription donc on utilise l'InscriptionRepository pour sauvegarder un Skieur
    Set<Inscription> inscriptions;


    String nomS;
    String prenomS;
    LocalDate dateNaissance;
    String ville;

}
