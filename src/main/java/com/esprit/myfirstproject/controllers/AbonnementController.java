package com.esprit.myfirstproject.controllers;

import com.esprit.myfirstproject.entities.Abonnement;
import com.esprit.myfirstproject.entities.enums.TypeAbonnement;
import com.esprit.myfirstproject.services.AbonnementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("abonnements")// le path du controller → tjr en minuscule
@RequiredArgsConstructor // permet d'injester par constructeur
public class AbonnementController {

    private final AbonnementService abonnementService; // on appelle l'interface ici pas la classe

//    on injecte au controlleur l'interface (pas la classe) car

    @GetMapping
    public List<Abonnement> getAll() {
        return abonnementService.getAll(); // tjr appel d'une méthode provenant du service associé.
    }

//    @GetMapping
//    public ResponseEntity<List<Abonnement>> getAllAbonnements() {
//
//        List<Abonnement> abonnements = new ArrayList<Abonnement>(abonnementService.getAll());
//
//        if (abonnements.isEmpty()) {
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }
//
//        return new ResponseEntity<>(abonnements, HttpStatus.OK);
//    }


    @GetMapping("{id}")
    public Abonnement getAbonnementbyId(@PathVariable Long id) { //@pathvariable prend la mémé chaîne que le nom de l'attribut voir @GetMapping
        return abonnementService.getId(id);
    }

//    @GetMapping("{id}")
//    public ResponseEntity<Abonnement> getAbonnementById(@PathVariable Long id) {
//        try {
//            Abonnement abonnement = abonnementService.getId(id);
//            if (abonnement != null) {
//                return new ResponseEntity<>(abonnement, HttpStatus.OK);
//            } else {
//                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }


//    @GetMapping("/typeAbonnement/{typeAbonnement}")
//    public ResponseEntity<Set<Abonnement>> getAbonnementByType(@PathVariable TypeAbonnement typeAbonnement) {
//        try {
//            Set<Abonnement> abonnements = abonnementService.getAbonnementByType(typeAbonnement);
//            if (!abonnements.isEmpty()) {
//                return new ResponseEntity<>(abonnements, HttpStatus.OK);
//            } else {
//                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//            }
//        } catch (Exception e) {
//            // Gérer l'exception ici, par exemple, en renvoyant une réponse d'erreur appropriée.
//            e.printStackTrace();
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }


    @PostMapping
    public Abonnement addAbonnement(@RequestBody Abonnement Abonnement) {// @RequestBody pr les objets et @PathVariable pour les attributs
        return abonnementService.addAbonnement(Abonnement);
    }


//    @PostMapping
//    public ResponseEntity<Abonnement> addAbonnement(@RequestBody Abonnement abonnement) {
//        try {
//            Abonnement newAbonnement = abonnementService.addAbonnement(abonnement);
//            return new ResponseEntity<>(newAbonnement, HttpStatus.CREATED);
//        } catch (Exception e) {
//            // Gérer l'exception ici, par exemple, en renvoyant une réponse d'erreur appropriée.
//            e.printStackTrace();
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
    @PutMapping
    public Abonnement updateAbonnement(@RequestBody Abonnement Abonnement) {
        return abonnementService.updateAbonnement(Abonnement);
    }

    @DeleteMapping("{id}")
    public boolean deleteAbonnement(@PathVariable long id) {
        return abonnementService.deleteById(id);
    }

//    @PutMapping
//    public ResponseEntity<Abonnement> updateAbonnement(@RequestBody Abonnement abonnement) {
//        try {
//            Abonnement updatedAbonnement = abonnementService.updateAbonnement(abonnement);
//            if (updatedAbonnement != null) {
//                return new ResponseEntity<>(updatedAbonnement, HttpStatus.OK);
//            } else {
//                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//            }
//        } catch (Exception e) {
//            // Gérer l'exception ici, par exemple, en renvoyant une réponse d'erreur appropriée.
//            e.printStackTrace();
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }


    @GetMapping("dateAbonnement")
    List<Abonnement> retrieveSubscriptionsByDates(@RequestParam LocalDate startDate, @RequestParam LocalDate endDate) {
        return abonnementService.retrieveSubscriptionsByDates(startDate, endDate);
    }


//    @GetMapping("dateAbonnement")
//    public ResponseEntity<List<Abonnement>> retrieveSubscriptionsByDates(
//            @RequestParam LocalDate startDate, @RequestParam LocalDate endDate) {
//        try {
//            List<Abonnement> abonnements = abonnementService.retrieveSubscriptionsByDates(startDate, endDate);
//            if (!abonnements.isEmpty()) {
//                return new ResponseEntity<>(abonnements, HttpStatus.OK);
//            } else {
//                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//            }
//        } catch (Exception e) {
//            // Gérer l'exception ici, par exemple, en renvoyant une réponse d'erreur appropriée.
//            e.printStackTrace();
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }


}
