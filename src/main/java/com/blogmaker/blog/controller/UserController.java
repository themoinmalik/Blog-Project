package com.blogmaker.blog.controller;


import com.blogmaker.blog.dtos.UserDto;
import com.blogmaker.blog.entities.User;
import com.blogmaker.blog.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // get user
    @GetMapping("/{userId}")
    public User getUser(@PathVariable Long userId){
        return userService.getUser(userId);
    }

    //create User..
    @GetMapping("/createUser")
    public User createUser(@RequestBody User user){
        return userService.createUser(user);
    }

    // update

    // delete


}
