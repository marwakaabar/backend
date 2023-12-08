package com.esprit.myfirstproject.controllers;

import com.esprit.myfirstproject.entities.Skieur;
import com.esprit.myfirstproject.entities.enums.TypeAbonnement;
import com.esprit.myfirstproject.services.servicesimpl.SkieurServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("skieurs")// le path du controller → tjr en minuscule
@RequiredArgsConstructor // permet d'injester par constructeur
@Slf4j
//pr l'attribut skieurService, on peut aussi l'injecter par champs en rajoutant seulement @Autowired
// pas la peine d'écrire final !!!!! => seulement pr l'injecteur par constructeur.
public class SkieurController {

    private final SkieurServiceImpl skieurService;

    @GetMapping
    public List<Skieur> getAll() {
        return skieurService.getAll(); // tjr appel d'une méthode provenant du service associé.
    }

    @GetMapping("{id}")
    public Skieur getSkieurbyId(@PathVariable Long id) { //@pathvariable prend la mémé chaîne que le nom de l'attribut voir @GetMapping
        return skieurService.getSkieurbyId(id);
    }

    @PostMapping
    public Skieur addSkieur(@RequestBody Skieur skieur) {// @RequestBody pr les objets et @PathVariable pour les attributs
        return skieurService.addSkieur(skieur);
    }

    @PutMapping("{id}")
    public Skieur updateSkieur(@RequestBody Skieur skieur) {
        return skieurService.updateSkieur(skieur);
    }

    @PutMapping("/AddSkieurToPiste")

    public Skieur assignSkieurToPiste(@RequestParam Long numSkieur, @RequestParam Long numPiste) {

        return skieurService.assignSkieurToPiste(numSkieur, numPiste);
    }


    @PostMapping("/addSkierAndAssignToCourse/{numCourse}")

    public Skieur addSkierAndAssignToCourse(@RequestBody Skieur skieur, @PathVariable Long numCourse) {
        return skieurService.addSkieurAndAssignToCourse(skieur, numCourse);
    }


//    1ère méthode avancée faite

    @GetMapping("/typeAbonnement/{typeAbonnement}")
    List<Skieur> retrieveSkiersBySubscriptionType(@PathVariable TypeAbonnement typeAbonnement) {
        return skieurService.retrieveSkiersBySubscriptionType(typeAbonnement);

    }


    @GetMapping("/getSkieurbyMoniteurNameJPQL")
    public List<Skieur> getSkieurbyMoniteurNameJPQL(@RequestParam("MoniteurName") String MoniteurName) {
        return skieurService.getSkieurbyMoniteurNameJPQL(MoniteurName);
    }

    @DeleteMapping("{id}")
    public boolean deleteSkieur(@PathVariable long id) {
        return skieurService.deleteById(id);
    }

    @GetMapping("test/{date}")
    public LocalDate dateTest(@PathVariable LocalDate date) {
        return skieurService.dateTest(date);
    }

}
