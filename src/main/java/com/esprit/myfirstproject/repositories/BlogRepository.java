package com.esprit.myfirstproject.repositories;

import com.esprit.myfirstproject.entities.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Blog,Long> {
}
