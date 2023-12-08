package com.esprit.myfirstproject.controllers;

import com.esprit.myfirstproject.entities.Cours;
import com.esprit.myfirstproject.entities.enums.Support;
import com.esprit.myfirstproject.services.CoursService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("cours")// le path du controller → tjr en minuscule
@RequiredArgsConstructor // permet d'injester par constructeur
public class CoursController {
    private final CoursService coursService;

    @GetMapping
    public List<Cours> getAll() {
        return coursService.getAll(); // tjr appel d'une méthode provenant du service associé.
    }

    @GetMapping("{id}")
    public Cours getCoursbyId(@PathVariable Long id) { //@pathvariable prend la mémé chaîne que le nom de l'attribut voir @GetMapping
        return coursService.getCoursById(id);
    }

    @PostMapping
    public Cours addCours(@RequestBody Cours Cours) {// @RequestBody pr les objets et @PathVariable pour les attributs
        return coursService.addCours(Cours);
    }

    @PutMapping
    public Cours updateCours(@RequestBody Cours Cours) {
        return coursService.updateCours(Cours);
    }

    @DeleteMapping("{id}")
    public boolean deleteCours(@PathVariable long id) {
        return coursService.deleteById(id);
    }

    @GetMapping("/numWeeksCourseOfMoniteurBySupport/{numMoniteur}")
    public List<Integer> numWeeksCourseOfMoniteurBySupport(@PathVariable Long numMoniteur, @RequestBody Support support) {
        return coursService.numWeeksCourseOfMoniteurBySupport(numMoniteur, support);
    }

    @PatchMapping("/update/{numCours}")
    public ResponseEntity<Cours> partialUpdateCours(@PathVariable Long numCours, @RequestBody Map<String, Object> updates) {
        return coursService.partialUpdateCours(numCours, updates);
    }

}
