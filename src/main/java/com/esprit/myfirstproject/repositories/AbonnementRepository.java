package com.esprit.myfirstproject.repositories;

import com.esprit.myfirstproject.entities.Abonnement;
import com.esprit.myfirstproject.entities.enums.TypeAbonnement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AbonnementRepository extends JpaRepository<Abonnement, Long> {

    List<Abonnement> findByTypeAbonOrderByDateDebut(TypeAbonnement typeAbonnement); // permet de récupérer depuis la BD
    //complexité diminuée


    List<Abonnement> findByDateDebutAfterAndDateFinBefore(LocalDate datedebut, LocalDate datefin);


}
