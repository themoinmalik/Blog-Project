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
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepo postRepo;

    private final UserRepository userRepository;

    private final CategoryRepo categoryRepo;

    private final ModelMapper modelMapper;

    @Autowired
    public PostServiceImpl(PostRepo postRepo, UserRepository userRepository, CategoryRepo categoryRepo, ModelMapper modelMapper) {
        this.postRepo = postRepo;
        this.userRepository = userRepository;
        this.categoryRepo = categoryRepo;
        this.modelMapper = modelMapper;
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
    public ResponseEntity<List<PostDTO>> getPostByUserId(Long userId) {

        List<Post> posts = postRepo.findByUserId(userId);
        List<PostDTO> postDTOS = posts.stream().map(
                post-> modelMapper.map(post,PostDTO.class)
        ).collect(Collectors.toList());
    return new ResponseEntity<>(postDTOS, HttpStatus.OK);

    }

    @Override
    public ResponseEntity<List<PostDTO>> getAllPosts(Integer pageNum, Integer pageSize, String sortBy) {
        // make posts [pageable..

        Pageable pageable = PageRequest.of(pageNum,pageSize, Sort.by(sortBy));
        Page<Post> postPage = postRepo.findAll(pageable);
        List<Post> posts = postPage.getContent();
        List<PostDTO> postDTOS = posts.stream().map(
                post -> modelMapper.map(post, PostDTO.class)
        ).collect(Collectors.toList());
        return new ResponseEntity<>(postDTOS, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PostDTO> getPostById(Long postId) {
        Post post = postRepo.findById(postId).orElseThrow(
                ()-> new ResourceNotFoundException("no post found")
        );
        PostDTO postDTO = modelMapper.map(post, PostDTO.class);
        return new ResponseEntity<>(postDTO, HttpStatus.OK);
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
