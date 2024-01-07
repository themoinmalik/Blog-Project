package com.blogmaker.blog.controller;

import com.blogmaker.blog.dtos.PostDTO;
import com.blogmaker.blog.entity.Post;
import com.blogmaker.blog.service.FileService;
import com.blogmaker.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/post/")
public class PostController {

    private final PostService postService;

    private final FileService fileService;

    @Value("${project.image}")
    private String path;


    @Autowired
    public PostController(PostService postService, FileService fileService) {
        this.postService = postService;
        this.fileService = fileService;
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
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize,
            @RequestParam(value = "sortBy", defaultValue = "Id", required = false) String sortBy){
        return postService.getAllPosts(pageNum, pageSize, sortBy);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostDTO> getPostById(@PathVariable Long postId){
        return postService.getPostById(postId);
    }


    @PutMapping("/{Id}")
    public ResponseEntity<PostDTO> updatePostById(@PathVariable Long Id, @RequestBody PostDTO postDTO){
        return postService.updatePostById(Id, postDTO);
    }

    @DeleteMapping("/{Id}")
    public ResponseEntity<?> deletePostById(@PathVariable Long Id){
        return postService.deletePostById(Id);
    }


    /// post image upload.
    @PostMapping("/image/uploadImage/{postId}")
    public ResponseEntity<PostDTO> uploadImage(
            @RequestParam("image") MultipartFile image,
            @PathVariable Long postId) throws IOException {
       String fileName = fileService.uploadImage(path, image);
       PostDTO postDTO = postService.getPostById(postId).getBody();
       Objects.requireNonNull(postDTO).setImageName(fileName);
       return postService.updatePostById(postId, postDTO);

    }


}
