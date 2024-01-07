package com.blogmaker.blog.service;


import com.blogmaker.blog.dtos.PostDTO;
import com.blogmaker.blog.entity.Post;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PostService {


    ResponseEntity<PostDTO> createPost(Long userId, Long catId, PostDTO postDTO);

    ResponseEntity<List<PostDTO>> getPostByUserId(Long userId);

    ResponseEntity<List<PostDTO>> getAllPosts(Integer pageNum, Integer pageSize, String sortBy);

    ResponseEntity<PostDTO> getPostById(Long postId);

    ResponseEntity<PostDTO> updatePostById(Long id, PostDTO postDTO);

    ResponseEntity<?> deletePostById(Long id);
}
