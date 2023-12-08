package com.esprit.myfirstproject.services.servicesimpl;

import com.esprit.myfirstproject.entities.Piste;
import com.esprit.myfirstproject.repositories.PisteRepository;
import com.esprit.myfirstproject.services.PisteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PisteServiceImpl implements PisteService {


    private final PisteRepository repository;

    @Override
    public Piste addPiste(Piste p) {
        return repository.save(p);
    }

    @Override
    public Piste updatePiste(Piste p) {
        return repository.save(p);
    }

    @Override
    public List<Piste> getAll() {
        return repository.findAll();
    }

    @Override
    public Piste getId(Long id) {
        return repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Piste " + id + " INEXISTANTE !!!"));
    }

    @Override
    public boolean deleteById(Long id) {
        repository.deleteById(id);
        return !(repository.existsById(id));
    }
}
