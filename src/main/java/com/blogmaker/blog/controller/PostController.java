package com.blogmaker.blog.controller;

import com.blogmaker.blog.dtos.PostDTO;
import com.blogmaker.blog.entity.Post;
import com.blogmaker.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/user/{userId}/category/{catId}/post")
    public ResponseEntity<PostDTO> createPost(@PathVariable Long userId, @PathVariable Long catId, @RequestBody PostDTO postDTO){
        return postService.createPost(userId, catId, postDTO);
    }


    @GetMapping("/user/{userId}/post")
    public ResponseEntity<List<Post>> getPost(@PathVariable Long userId){
        return postService.getPostByUserId(userId);
    }


    @GetMapping("/getAll")
    public ResponseEntity<List<Post>> getPosts(){
        return postService.getAllPosts();
    }


    @PutMapping("/{Id}")
    public ResponseEntity<PostDTO> updatePostById(@PathVariable Long Id, @RequestBody PostDTO postDTO){
        return postService.updatePostById(Id, postDTO);
    }

    @DeleteMapping("/{Id}")
    public ResponseEntity<?> deletePostById(@PathVariable Long Id){
        return postService.deletePostById(Id);
    }



}
