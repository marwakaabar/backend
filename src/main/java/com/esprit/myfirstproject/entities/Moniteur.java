package com.esprit.myfirstproject.entities;

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
@FieldDefaults(level = AccessLevel.PRIVATE) //rend tous les attributs private
public class Moniteur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long numMoniteur;
    String nomM;
    String prenomM;
    LocalDate dateRecru;

    @OneToMany// la classe Moniteur est la classe child(mapped by), elle est géréé par la classe Inscription
    private Set<Cours> cours;

}
