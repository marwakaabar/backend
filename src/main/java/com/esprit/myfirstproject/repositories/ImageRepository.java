package com.esprit.myfirstproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.esprit.myfirstproject.entities.Image;

public interface ImageRepository extends JpaRepository<Image,Long>{

}
