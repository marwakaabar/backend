package com.esprit.myfirstproject.repositories;

import com.esprit.myfirstproject.entities.Register;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegisterRepo extends JpaRepository<Register,Long> {
}
