package com.blogmaker.blog.service.serviceImpl;


import com.blogmaker.blog.dtos.PostDTO;
import com.blogmaker.blog.entity.Category;
import com.blogmaker.blog.entity.Post;
import com.blogmaker.blog.entity.User;
import com.blogmaker.blog.exception.ResourceNotFoundException;
import com.blogmaker.blog.repository.CategoryRepo;
import com.blogmaker.blog.repository.PostRepo;
import com.blogmaker.blog.repository.UserRepository;
import com.blogmaker.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepo postRepo;

    private final UserRepository userRepository;

    private final CategoryRepo categoryRepo;

    @Autowired
    public PostServiceImpl(PostRepo postRepo, UserRepository userRepository, CategoryRepo categoryRepo) {
        this.postRepo = postRepo;
        this.userRepository = userRepository;
        this.categoryRepo = categoryRepo;
    }

    @Override
    public ResponseEntity<PostDTO> createPost(Long userId, Long catId, PostDTO postDTO) {

        Post post = new Post();
        post.setTitle(postDTO.getTitle());
        post.setContent(postDTO.getContent());
        post.setCreatedOn(LocalDate.now());
        post.setUpdatedOn(LocalDate.now());
        User user = userRepository.findById(userId).orElseThrow(
                ()-> new ResourceNotFoundException("No User found for this Id.")
        );
        post.setUser(user);
        Category category = categoryRepo.findById(catId).orElseThrow(
                ()-> new ResourceNotFoundException("Category not found")
        );
        post.setCategory(category);
        postRepo.save(post);
        postDTO.setUserId(userId);
        postDTO.setCategoryId(catId);
        return new ResponseEntity<>(postDTO, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<List<Post>> getPostByUserId(Long userId) {
        List<Post> post = postRepo.findByUserId(userId);
    return new ResponseEntity<>(post, HttpStatus.OK);

    }

    @Override
    public ResponseEntity<List<Post>> getAllPosts() {
        List<Post> posts = postRepo.findAll();
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PostDTO> updatePostById(Long id, PostDTO postDto) {
        Post post = postRepo.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Not Found")
        );
        post.setTitle(postDto.getTitle());
        post.setUpdatedOn(LocalDate.now());
        post.setContent(postDto.getContent());
        postRepo.save(post);
        postDto.setUserId(post.getUser().getId());
        postDto.setCategoryId(post.getCategory().getId());
        return new ResponseEntity<>(postDto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> deletePostById(Long id) {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}