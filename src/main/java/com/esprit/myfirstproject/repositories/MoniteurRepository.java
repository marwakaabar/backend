package com.esprit.myfirstproject.repositories;

import com.esprit.myfirstproject.entities.Moniteur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MoniteurRepository extends JpaRepository<Moniteur, Long> {
}
