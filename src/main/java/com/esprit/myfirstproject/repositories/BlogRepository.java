package com.esprit.myfirstproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.esprit.myfirstproject.entities.Blog;


public interface BlogRepository extends JpaRepository<Blog, Long>{

}
