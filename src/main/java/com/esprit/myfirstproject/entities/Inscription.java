package com.esprit.myfirstproject.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE) //rend tous mles attributs private
public class Inscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long numInscription;
    int numSemaine;
    @ManyToOne
    @JsonIgnore // permet d'ignorer l'attribut nulSkeiur lors du binfi=ding entre le skieur et l'inscription
    Skieur skieur ;
    @ManyToOne
    Cours cours ;

}
