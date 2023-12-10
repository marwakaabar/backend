package com.esprit.myfirstproject.controllers;

import com.esprit.myfirstproject.entities.Cours;
import com.esprit.myfirstproject.entities.Register;
import com.esprit.myfirstproject.services.RegisterService;
import com.esprit.myfirstproject.services.servicesimpl.CoursServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("register")
@RestController
@RequiredArgsConstructor
public class RegisterController {
    private final RegisterService registerService;
    private final CoursServiceImpl coursService;

    @GetMapping
    public List<Register> getAll() {
        return registerService.getAll(); // tjr appel d'une méthode provenant du service associé.
    }

    @GetMapping("{id}")
    public Register getRegisterbyId(@PathVariable Long id) { //@pathvariable prend la mémé chaîne que le nom de l'attribut voir @GetMapping
        return registerService.getRegisterById(id);
    }

    @PostMapping
    public Register add(@RequestBody Register register) {// @RequestBody pr les objets et @PathVariable pour les attributs
        return registerService.addRegister(register);
    }
    @PostMapping("/ajouterInscrit/{coursId}")
    public ResponseEntity<Register> ajouterInscrit(@RequestBody Register inscrit, @PathVariable Long coursId) {
        // Récupérez le cours depuis la base de données en utilisant le service de cours
        Cours cours = coursService.getCoursById(coursId);

        // Assurez-vous que le cours a été trouvé
        if (cours != null) {
            inscrit.setCours(cours);

            // Ajoutez votre logique d'ajout d'inscription ici
            Register savedInscrit = registerService.ajouterInscrit(inscrit);

            // Retournez la réponse avec l'inscription ajoutée
            return ResponseEntity.ok(savedInscrit);
        } else {
            // Gérer le cas où le cours n'est pas trouvé
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("{id}")
    public Register updateCours(@RequestBody Register r) {
        return registerService.updateRegister(r);
    }

    @DeleteMapping("{id}")
    public boolean deleteCours(@PathVariable long id) {
        return registerService.deleteById(id);
    }



}
