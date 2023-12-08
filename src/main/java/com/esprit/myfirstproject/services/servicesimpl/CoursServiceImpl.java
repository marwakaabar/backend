package com.esprit.myfirstproject.services.servicesimpl;

import com.esprit.myfirstproject.entities.Cours;
import com.esprit.myfirstproject.entities.Moniteur;
import com.esprit.myfirstproject.entities.enums.Support;
import com.esprit.myfirstproject.exceptions.ResourceNotFoundException;
import com.esprit.myfirstproject.repositories.CoursRepository;
import com.esprit.myfirstproject.repositories.MoniteurRepository;
import com.esprit.myfirstproject.services.CoursService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CoursServiceImpl implements CoursService {


    private final CoursRepository coursRepository;

    private final MoniteurRepository moniteurRepository;


    @Override
    public Cours addCours(Cours c) {
        return coursRepository.save(c);
    }

    @Override
    public Cours updateCours(Cours c) {
        return coursRepository.save(c);
    }

    @Override
    public List<Cours> getAll() {
        return coursRepository.findAll();
    }

    @Override
    public Cours getCoursById(Long id) {
        return coursRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("COURS " + id + " INEXISTANT !!!"));
    }

    @Override
    public boolean deleteById(Long id) {
        coursRepository.deleteById(id);
        return !(coursRepository.existsById(id)); // retourne true/false selon l'existence de l'objet Recherché (retournee LE CONTRAIRE car "!")
    }


    @Override
    public List<Integer> numWeeksCourseOfMoniteurBySupport(Long numMoniteur, Support support) {
        Moniteur moniteur = moniteurRepository.findById(numMoniteur).orElse(null);
        if (moniteur == null) {
            return new ArrayList<>(); // Moniteur non trouvé, retourner une liste vide
        }

        List<Integer> weeks = new ArrayList<>();

        // Parcourir tous les cours du moniteur
        for (Cours moniteurCours : moniteur.getCours()) {
            if (moniteurCours.getSupport() == support) {
                int creneau = moniteurCours.getCreneau();
                int weekNumber = calculateWeekNumber(creneau); // Utiliser la méthode pour calculer le numéro de semaine
                if (!weeks.contains(weekNumber)) {
                    weeks.add(weekNumber);
                }
            }
        }

        return weeks;
    }


    private int calculateWeekNumber(int creneau) {
        // Calculer le numéro de semaine en divisant le créneau par 7 (le nombre de jours dans une semaine).
        return creneau / 7;
    }

    @Override
    public ResponseEntity<Cours> partialUpdateCours(Long numCours, Map<String, Object> updates) {
        Optional<Cours> course = Optional.ofNullable(coursRepository.findById(numCours).orElseThrow(() -> new ResourceNotFoundException("il n'existe pas le cours avec l'id  = " + numCours)));


        if (course.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Cours cours = course.get();

        updates.forEach((key, value) -> {
            if (value != null) {
                try {
                    Field field = Cours.class.getDeclaredField(key);
                    field.setAccessible(true);

                    if (field.getType().isEnum()) {
                        // Vérifier si le champ est de type enum
                        Enum<?> enumValue = Enum.valueOf((Class<Enum>) field.getType(), (String) value);
                        field.set(cours, enumValue);
                    } else {
                        field.set(cours, value);
                    }
                } catch (NoSuchFieldException | IllegalAccessException | IllegalArgumentException e) {
                    e.printStackTrace(); // Gérer l'exception selon vos besoins
                }
            }
        });

        coursRepository.save(cours);

        return ResponseEntity.ok(cours);
    }


}
