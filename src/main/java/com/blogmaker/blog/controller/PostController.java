package com.blogmaker.blog.controller;

import com.blogmaker.blog.dtos.PostDTO;
import com.blogmaker.blog.entity.Post;
import com.blogmaker.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

    @PostMapping("/user/{userId}/category/{catId}")
    public ResponseEntity<PostDTO> createPost(@PathVariable Long userId, @PathVariable Long catId, @RequestBody PostDTO postDTO){
        return postService.createPost(userId, catId, postDTO);
    }


    @GetMapping("/user/{userId}")
    public ResponseEntity<List<PostDTO>> getPost(@PathVariable Long userId){
        return postService.getPostByUserId(userId);
    }


    @GetMapping("/getAll")
    public ResponseEntity<List<PostDTO>> getPosts(
            @RequestParam(value = "pageNum", defaultValue = "0",required = false) Integer pageNum,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize){
        return postService.getAllPosts(pageNum, pageSize);
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
