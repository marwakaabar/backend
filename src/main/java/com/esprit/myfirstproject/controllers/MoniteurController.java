package com.esprit.myfirstproject.controllers;


import com.esprit.myfirstproject.entities.Cours;
import com.esprit.myfirstproject.entities.Moniteur;
import com.esprit.myfirstproject.services.MoniteurService;
import com.esprit.myfirstproject.services.servicesimpl.MoniteurServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("moniteurs")// le path du controller → tjr en minuscule
@RequiredArgsConstructor // permet d'injester par constructeur
public class MoniteurController {


    private final MoniteurService moniteurService;

    @GetMapping
    public List<Moniteur> getAll() {
        return moniteurService.getAll(); // tjr appel d'une méthode provenant du service associé.
    }

    @GetMapping("{id}")
    public Moniteur getMoniteurbyId(@PathVariable Long id) { //@pathvariable prend la mémé chaîne que le nom de l'attribut voir @GetMapping
        return moniteurService.getId(id);
    }

    @PostMapping
    public Moniteur addMoniteur(@RequestBody Moniteur Moniteur) {// @RequestBody pr les objets et @PathVariable pour les attributs
        return moniteurService.addMoniteur(Moniteur);
    }

    @PutMapping
    public Moniteur updateMoniteur(@RequestBody Moniteur Moniteur) {
        return moniteurService.updateMoniteur(Moniteur);
    }

    @DeleteMapping("{id}")
    public boolean deleteMoniteur(@PathVariable long id) {
        return moniteurService.deleteById(id);
    }

    @PostMapping("/addInstructorAndAssignToCourse/{numCourse}")
    public Moniteur addMoniteurAndAssignToCourse(@RequestBody Moniteur moniteur,@PathVariable Long numCourse){
        return moniteurService.addMoniteurAndAssignToCourse(moniteur,numCourse);

    }

}
