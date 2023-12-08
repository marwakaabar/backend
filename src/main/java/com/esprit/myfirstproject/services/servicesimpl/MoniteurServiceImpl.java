package com.esprit.myfirstproject.services.servicesimpl;

import com.esprit.myfirstproject.entities.Cours;
import com.esprit.myfirstproject.entities.Moniteur;
import com.esprit.myfirstproject.repositories.CoursRepository;
import com.esprit.myfirstproject.repositories.MoniteurRepository;
import com.esprit.myfirstproject.services.MoniteurService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class MoniteurServiceImpl implements MoniteurService {


    private final MoniteurRepository moniteurRepository;
    private final CoursRepository coursRepository;

    @Override
    public Moniteur addMoniteur(Moniteur c) {
        return moniteurRepository.save(c);
    }

    @Override
    public Moniteur updateMoniteur(Moniteur c) {
        return moniteurRepository.save(c);
    }

    @Override
    public List<Moniteur> getAll() {
        return moniteurRepository.findAll();
    }

    @Override
    public Moniteur getId(Long id) {
        return moniteurRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Moniteur " + id + " INEXISTANT !!!"));
    }

    @Override
    public boolean deleteById(Long id) {
        moniteurRepository.deleteById(id);
        return !(moniteurRepository.existsById(id));
    }

    @Override // cas unidirectionnel avec SET
    public Moniteur addMoniteurAndAssignToCourse(Moniteur moniteur, Long numCourse) {

//      Etape 1 : recherche de l'objet avec l'id en paramêtre (ne pas oublier le repository associée à l'objet)
        Cours cours = coursRepository.findById(numCourse).orElse(null);

//      Etape 2 : comme l'attribut cours dans Moniteur est un SET, on va créer une variable locale coursSet
//                et mettre dedans la valeur initiale l'attribut cours

        Set<Cours> coursSet = moniteur.getCours();

//      Etape 3 : On rajoute l'objet cours (ETAPE 1) dans la variable locale coursSet
        coursSet.add(cours);

//      Etape 4 : On modifie la valeur de l'attribut cours(SET) de la classe Moniteur en lui affectant la valeur de la variable
//                locale coursSet (les 2 variables sont du même type)

        moniteur.setCours(coursSet);

//      Etape 5 : on retourne le nouvel objet Moniteur créé  dans la BD (on n'a pas de @Transactionnal ici)
        return moniteurRepository.save(moniteur);
    }


}
