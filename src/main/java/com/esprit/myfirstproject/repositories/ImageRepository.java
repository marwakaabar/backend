package com.esprit.myfirstproject.repositories;

import com.esprit.myfirstproject.entities.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image,Long> {
}
