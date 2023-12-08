package com.esprit.myfirstproject.entities;

import com.esprit.myfirstproject.entities.enums.TypeAbonnement;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE) //rend tous mles attributs private
public class Abonnement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //tjr IDENTITY
    Long numAbon;
    LocalDate dateDebut;
    LocalDate dateFin;
    Float prixAbon;
    @Enumerated(EnumType.STRING)
    private TypeAbonnement typeAbon;


}
