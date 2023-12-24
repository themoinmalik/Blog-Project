package com.blogmaker.blog.controller;

import com.blogmaker.blog.dtos.PostDTO;
import com.blogmaker.blog.entity.Post;
import com.blogmaker.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/post/")
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/create")
    public ResponseEntity<Post> createPost(@RequestBody PostDTO postDTO){
        return postService.createPost(postDTO);
    }


    @GetMapping("/{Id}")
    public ResponseEntity<Post> getPost(@PathVariable Long Id){
        return postService.getPostById(Id);
    }


    @GetMapping("/getAll")
    public ResponseEntity<List<Post>> getPosts(){
        return postService.getAllPosts();
    }


    @PutMapping("/{Id}")
    public ResponseEntity<Post> updatePostById(@PathVariable Long Id, @RequestBody PostDTO postDTO){
        return postService.updatePostById(Id, postDTO);
    }

    @DeleteMapping("/{Id}")
    public ResponseEntity<?> deletePostById(@PathVariable Long Id){
        return postService.deletePostById(Id);
    }



}
