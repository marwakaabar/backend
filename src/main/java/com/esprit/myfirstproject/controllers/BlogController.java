package com.esprit.myfirstproject.controllers;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.esprit.myfirstproject.entities.Blog;
import com.esprit.myfirstproject.entities.enums.BlogStatus;
import com.esprit.myfirstproject.services.BlogService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("blog")
@RequiredArgsConstructor
public class BlogController {

    private final BlogService blogService; // on appelle l'interface ici pas la classe

    @GetMapping
    public List<Blog> getAll() {
        return blogService.getAll(); // tjr appel d'une méthode provenant du service associé.
    }

    @GetMapping("{id}")
    public Blog getBlogbyId(@PathVariable Long id) { //@pathvariable prend la mémé chaîne que le nom de l'attribut voir @GetMapping
        return blogService.getBlogById(id);
    }

    @PostMapping
    public Blog addBlog(@RequestBody Blog Blog) {// @RequestBody pr les objets et @PathVariable pour les attributs
        return blogService.addBlog(Blog);
    }

    @PutMapping("{id}")
    public Blog updateBlog(@RequestBody Blog Blog) {
        return blogService.updateBlog(Blog);
    }

    @DeleteMapping("{id}")
    public boolean deleteBlog(@PathVariable long id) {
        return blogService.deleteById(id);
    }
    @PutMapping("/{id}/status")
    public ResponseEntity<Blog> updateBlogStatus(@PathVariable Long id, @RequestBody BlogStatus newStatus) {
        Blog updatedBlog = blogService.updateBlogStatus(id, newStatus);
        return ResponseEntity.ok(updatedBlog);
    }
}