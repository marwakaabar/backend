package com.esprit.myfirstproject.services;

import com.esprit.myfirstproject.entities.Cours;
import com.esprit.myfirstproject.entities.Moniteur;

import java.util.List;

public interface MoniteurService {// on ne touche plus cette interface (contient seulement les méthodes minimales)
    //    on rajoute les methodes avancées dans l'AbonnementServiceImpl (classe) qui implémente cette interface
//    Ce principe est nommé : Ouvert à l'extension, fermé à la modification.
    Moniteur addMoniteur(Moniteur c);

    Moniteur updateMoniteur(Moniteur c);

    List<Moniteur> getAll();

    Moniteur getId(Long id);

    boolean deleteById(Long id);

    Moniteur addMoniteurAndAssignToCourse(Moniteur moniteur, Long numCourse);


}
