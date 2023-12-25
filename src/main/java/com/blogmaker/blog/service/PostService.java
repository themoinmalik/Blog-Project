package com.blogmaker.blog.service;


import com.blogmaker.blog.dtos.PostDTO;
import com.blogmaker.blog.entity.Post;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PostService {


    ResponseEntity<Post> createPost(PostDTO postDTO);

    ResponseEntity<Post> getPostById(Long id);

    ResponseEntity<List<Post>> getAllPosts();

    ResponseEntity<Post> updatePostById(Long id, PostDTO postDTO);

    ResponseEntity<?> deletePostById(Long id);
}
