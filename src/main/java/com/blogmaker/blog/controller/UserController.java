package com.blogmaker.blog.controller;

import com.blogmaker.blog.dtos.UserDTO;
import com.blogmaker.blog.entity.User;
import com.blogmaker.blog.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody User user){
       return userService.createUser(user);
    }

    @GetMapping("/")
    public UserDTO getUserById(@RequestBody UserDTO userDTO){
        return userService.findUserById(userDTO.getUserId());
    }

}
