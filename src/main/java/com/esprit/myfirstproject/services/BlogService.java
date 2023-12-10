package com.esprit.myfirstproject.services;

import com.esprit.myfirstproject.entities.Blog;
import com.esprit.myfirstproject.entities.enums.BlogStatus;

import java.util.List;

public interface BlogService {
    Blog addBlog(Blog b);

    Blog updateBlog(Blog b);

    List<Blog> getAll();

    Blog getBlogById(Long id);

    boolean deleteById(Long id);

    Blog updateBlogStatus(Long id, BlogStatus newStatus);
}
