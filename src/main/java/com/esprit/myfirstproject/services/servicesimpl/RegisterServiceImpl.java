package com.esprit.myfirstproject.services.servicesimpl;

import com.esprit.myfirstproject.entities.Cours;
import com.esprit.myfirstproject.entities.Register;
import com.esprit.myfirstproject.repositories.RegisterRepo;
import com.esprit.myfirstproject.services.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RegisterServiceImpl implements RegisterService {
    @Autowired
    private RegisterRepo registerRepo;
    @Autowired
    private CoursServiceImpl coursService;
    @Override
    public Register addRegister(Register r) {
        return registerRepo.save(r);
    }

    @Override
    public Register updateRegister(Register r) {
        return registerRepo.save(r);
    }

    @Override
    public List<Register> getAll() {
        return registerRepo.findAll();
    }

    @Override
    public Register getRegisterById(Long id) {
        return registerRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Inscription " + id + " INEXISTANTE !!!"));

    }

    @Override
    public Register ajouterInscrit(Register inscrit) {
        // Votre logique pour gérer la propriété coursId
        // Votre logique pour gérer la propriété coursId
        Long coursId = inscrit.getCoursId();

        // Chargez le cours à partir de la base de données en utilisant le service de cours
        Cours cours = coursService.getCoursById(coursId);

        // Assurez-vous que le cours a été trouvé
        if (cours != null) {
            inscrit.setCours(cours);

            // Enregistrez l'inscription dans la base de données
            return registerRepo.save(inscrit);
        }
        return inscrit;
    }


    @Override
    public boolean deleteById(Long id) {
        registerRepo.deleteById(id);
        return !(registerRepo.existsById(id)); // retourne true/false selon l'existence de l'objet Recherché (retournee LE CONTRAIRE car "!")
    }
}
