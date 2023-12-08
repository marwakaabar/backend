package com.esprit.myfirstproject.services.servicesimpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.esprit.myfirstproject.entities.Blog;
import com.esprit.myfirstproject.entities.enums.BlogStatus;
import com.esprit.myfirstproject.repositories.BlogRepository;
import com.esprit.myfirstproject.services.BlogService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor // pour l'injection des dépendances niveau constructor
@Service
public class BlogServiceImpl implements BlogService{

	 private final BlogRepository blogRepository;
	 
	@Override
	public Blog addBlog(Blog b) {
		return blogRepository.save(b);
	}

	@Override
	public Blog updateBlog(Blog b) {
		return blogRepository.save(b);
	}

	@Override
	public List<Blog> getAll() {
		return blogRepository.findAll();
	}

	@Override
	public Blog getBlogById(Long id) {
        return blogRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Blog " + id + " INEXISTANT !!!"));
	}

	@Override
	public boolean deleteById(Long id) {
		blogRepository.deleteById(id);
        return !(blogRepository.existsById(id));
	}
	
	 public Blog updateBlogStatus(Long id, BlogStatus newStatus) {
	        Blog blogToUpdate = blogRepository.findById(id)
	                .orElseThrow(() -> new EntityNotFoundException("Blog not found with id: " + id));

	        blogToUpdate.setStatus(newStatus);
	        return blogRepository.save(blogToUpdate);
	    }
}
