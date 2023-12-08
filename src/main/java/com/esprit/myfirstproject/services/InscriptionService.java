package com.esprit.myfirstproject.services;

import com.esprit.myfirstproject.entities.Inscription;

import java.util.List;

public interface InscriptionService {// on ne touche plus cette interface (contient seulement les méthodes minimales)
    //    on rajoute les methodes avancées dans l'AbonnementServiceImpl (classe) qui implémente cette interface
//    Ce principe est nommé : Ouvert à l'extension, fermé à la modification.
    Inscription addInscription(Inscription ins);

    Inscription updateInscription(Inscription ins);

    List<Inscription> getAll();

    Inscription getId(Long id);

    boolean deleteById(Long id);

    public Inscription addInscriptionAndAssignToSkieur(Long idSkieur, Inscription inscription);

    public Inscription assignInscriptionToCourse(Long numInscription, Long numCours);


    Inscription addInscriptionAndAssignToSkierAndCourse(Inscription inscription, Long numSkieur, Long numCours);
}
