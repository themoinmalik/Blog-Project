package com.blogmaker.blog.service;


import com.blogmaker.blog.dtos.PostDTO;
import com.blogmaker.blog.entity.Post;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PostService {


    ResponseEntity<PostDTO> createPost(Long userId, Long catId, PostDTO postDTO);

    ResponseEntity<List<Post>> getPostByUserId(Long userId);

    ResponseEntity<List<Post>> getAllPosts();

    ResponseEntity<PostDTO> updatePostById(Long id, PostDTO postDTO);

    ResponseEntity<?> deletePostById(Long id);
}
