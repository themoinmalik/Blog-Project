package com.blogmaker.blog.service.serviceImpl;


import com.blogmaker.blog.dtos.PostDTO;
import com.blogmaker.blog.entity.Post;
import com.blogmaker.blog.exception.ResourceNotFoundException;
import com.blogmaker.blog.repository.PostRepo;
import com.blogmaker.blog.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepo postRepo;


    public PostServiceImpl(PostRepo postRepo) {
        this.postRepo = postRepo;
    }

    @Override
    public ResponseEntity<Post> createPost(PostDTO postDTO) {

        Post post = new Post();
        post.setTitle(postDTO.getTitle());
        post.setContent(postDTO.getContent());
        post.setCreatedOn(LocalDate.now());
        post.setUpdatedOn(LocalDate.now());
        post.setCategory(postDTO.getCategory());

        postRepo.save(post);

        return new ResponseEntity<>(post, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Post> getPostById(Long Id) {
        Post post = postRepo.findById(Id).orElseThrow(
                ()-> new ResourceNotFoundException("Not Found")
        );
        return new ResponseEntity<>(post, HttpStatus.OK);

    }

    @Override
    public ResponseEntity<List<Post>> getAllPosts() {
        List<Post> posts = postRepo.findAll();
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Post> updatePostById(Long id, PostDTO postDto) {
        Post post = postRepo.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Not Found")
        );
        post.setTitle(postDto.getTitle());
        post.setUpdatedOn(LocalDate.now());
        post.setContent(postDto.getContent());

        postRepo.save(post);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> deletePostById(Long id) {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
