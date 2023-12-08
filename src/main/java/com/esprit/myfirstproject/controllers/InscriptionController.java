package com.esprit.myfirstproject.controllers;

import com.esprit.myfirstproject.entities.Inscription;
import com.esprit.myfirstproject.services.InscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("inscriptions")// le path du controller → tjr en minuscule
@RequiredArgsConstructor // permet d'injester par constructeur
public class InscriptionController {

//    private final inscriptionServiceImpl inscriptionService;


    private final InscriptionService inscriptionService; // pas d'autowired car injection par constructeur

    @GetMapping
    public List<Inscription> getAll() {
        return inscriptionService.getAll(); // tjr appel d'une méthode provenant du service associé.
    }

    @GetMapping("{id}")
    public Inscription getInscriptionbyId(@PathVariable Long id) { //@pathvariable prend la mémé chaîne que le nom de l'attribut voir @GetMapping
        return inscriptionService.getId(id);
    }

    @PostMapping
    public Inscription addInscription(@RequestBody Inscription Inscription) {// @RequestBody pr les objets et @PathVariable pour les attributs
        return inscriptionService.addInscription(Inscription);
    }

    @PutMapping
    public Inscription updateInscription(@RequestBody Inscription Inscription) {
        return inscriptionService.updateInscription(Inscription);
    }

    //méthodes avancées

    @PostMapping("{idSkieur}") // on doit précisier l'id du skieur à ajouter dans l'inscription
    //post car on a créé un objet
    public Inscription addInscriptionAndAssignToSkieur(@PathVariable Long idSkieur, @RequestBody Inscription inscription) {
        return inscriptionService.addInscriptionAndAssignToSkieur(idSkieur, inscription);
    }

    @DeleteMapping("{id}")
    public boolean deleteInscription(@PathVariable long id) {
        return inscriptionService.deleteById(id);
    }

    @PutMapping("/addRegisterToCours") // Put car on n'a pas créé d'objets juste assigner

    public Inscription assignInscriptionToCourse(@RequestParam Long numInscription, @RequestParam Long numCours) {

        return inscriptionService.assignInscriptionToCourse(numInscription, numCours);
    }


    @PostMapping("{numSkieur}/{numCours}")
    Inscription addInscriptionAndAssignToSkierAndCourse(Inscription inscription,@PathVariable Long numSkieur,@PathVariable Long numCours){
        return inscriptionService.addInscriptionAndAssignToSkierAndCourse(inscription,numSkieur,numCours);
    }
}
