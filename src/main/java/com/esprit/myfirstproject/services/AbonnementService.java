package com.esprit.myfirstproject.services;

import com.esprit.myfirstproject.entities.Abonnement;
import com.esprit.myfirstproject.entities.Abonnement;
import com.esprit.myfirstproject.entities.enums.TypeAbonnement;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface AbonnementService { // on ne touche plus cette interface (contient seulement les méthodes minimales)
//    on rajoute les methodes avancées dans l'AbonnementServiceImpl (classe) qui implémente cette interface
//    Ce principe est nommé : Ouvert à l'extension, fermé à la modification.
    Abonnement addAbonnement(Abonnement a);

    Abonnement updateAbonnement(Abonnement a);

    List<Abonnement> getAll();

    Abonnement getId(Long id);

    boolean deleteById(Long id);

    Set<Abonnement> getAbonnementByType(TypeAbonnement typeAbonnement);

    List<Abonnement> retrieveSubscriptionsByDates(LocalDate startDate, LocalDate endDate);

//    public void testscheduler();
}
