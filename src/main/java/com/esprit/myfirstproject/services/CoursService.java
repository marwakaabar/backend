package com.esprit.myfirstproject.services;

import com.esprit.myfirstproject.entities.Cours;
import com.esprit.myfirstproject.entities.enums.Support;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface CoursService {// on ne touche plus cette interface (contient seulement les méthodes minimales)
    //    on rajoute les methodes avancées dans l'AbonnementServiceImpl (classe) qui implémente cette interface
//    Ce principe est nommé : Ouvert à l'extension, fermé à la modification.
    Cours addCours(Cours c);

    Cours updateCours(Cours c);

    List<Cours> getAll();

    Cours getCoursById(Long id);

    boolean deleteById(Long id);

    List<Integer> numWeeksCourseOfMoniteurBySupport(Long numMoniteur, Support support);

    ResponseEntity<Cours> partialUpdateCours(Long numCours, Map<String, Object> updates);
}
