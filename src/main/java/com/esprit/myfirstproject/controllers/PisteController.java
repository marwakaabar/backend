package com.esprit.myfirstproject.controllers;

import com.esprit.myfirstproject.entities.Piste;
import com.esprit.myfirstproject.services.servicesimpl.PisteServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("pistes")// le path du controller → tjr en minuscule
@RequiredArgsConstructor // permet d'injester par constructeur
public class PisteController {
    private final PisteServiceImpl PisteService;

    @GetMapping
    public List<Piste> getAll() {
        return PisteService.getAll(); // tjr appel d'une méthode provenant du service associé.
    }

    @GetMapping("{id}")
    public Piste getPistebyId(@PathVariable Long id) { //@pathvariable prend la mémé chaîne que le nom de l'attribut voir @GetMapping
        return PisteService.getId(id);
    }

    @PostMapping
    public Piste addPiste(@RequestBody Piste Piste) {// @RequestBody pr les objets et @PathVariable pour les attributs
        return PisteService.addPiste(Piste);
    }

    @PutMapping
    public Piste updatePiste(@RequestBody Piste Piste) {
        return PisteService.updatePiste(Piste);
    }

    @DeleteMapping("{id}")
    public boolean deletePiste(@PathVariable long id) {
        return PisteService.deleteById(id);
    }
}
