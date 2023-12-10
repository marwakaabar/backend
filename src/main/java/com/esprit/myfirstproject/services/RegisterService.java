package com.esprit.myfirstproject.services;

import com.esprit.myfirstproject.entities.Cours;
import com.esprit.myfirstproject.entities.Register;

import java.util.List;

public interface RegisterService {
    Register addRegister(Register r);

    Register updateRegister(Register r);

    List<Register> getAll();

    Register getRegisterById(Long id);
    public Register ajouterInscrit(Register inscrit) ;

    boolean deleteById(Long id);
}
