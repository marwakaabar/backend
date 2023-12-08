package com.esprit.myfirstproject.services;

import com.esprit.myfirstproject.entities.Skieur;
import com.esprit.myfirstproject.entities.enums.TypeAbonnement;

import java.time.LocalDate;
import java.util.List;

public interface SkieurService {// on ne touche plus cette interface (contient seulement les méthodes minimales

    // et les méthodes avancées)
//    Ce principe est nommé : Ouvert à l'extension, fermé à la modification.
    Skieur addSkieur(Skieur skieur);

    Skieur updateSkieur(Skieur skieur);

    List<Skieur> getAll();

    Skieur getSkieurbyId(Long id);

    boolean deleteById(Long id);

    Skieur assignSkieurToPiste(Long numSkieur, Long numPiste);

    Skieur addSkieurAndAssignToCourse(Skieur skieur, Long numCourse);

    List<Skieur> retrieveSkiersBySubscriptionType(TypeAbonnement typeAbonnement);

    List<Skieur> getSkieurbyMoniteurNameJPQL(String MoniteurName);

    public void testscheduler();

    LocalDate dateTest(LocalDate date);
}
