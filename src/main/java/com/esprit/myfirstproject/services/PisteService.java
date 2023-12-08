package com.esprit.myfirstproject.services;

import com.esprit.myfirstproject.entities.Piste;

import java.util.List;

public interface PisteService {// on ne touche plus cette interface (contient seulement les méthodes minimales)
    //    on rajoute les methodes avancées dans l'AbonnementServiceImpl (classe) qui implémente cette interface
//    Ce principe est nommé : Ouvert à l'extension, fermé à la modification.
    Piste addPiste(Piste p);

    Piste updatePiste(Piste p);

    List<Piste> getAll();

    Piste getId(Long id);

    boolean deleteById(Long id);
}
