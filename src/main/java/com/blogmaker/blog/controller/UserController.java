package com.blogmaker.blog.controller;

import com.blogmaker.blog.dtos.UserDTO;
import com.blogmaker.blog.entity.User;
import com.blogmaker.blog.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user){
       return userService.createUser(user);
    }

    @GetMapping("/{Id}")
    public ResponseEntity<User> getUserById(@PathVariable Long Id){
        return userService.findUserById(Id);
    }

    @GetMapping("/listAllUsers")
    public ResponseEntity<List<User>> getUserById(){
        return userService.findAll();
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUserById(@RequestBody UserDTO userDTO, @PathVariable String id){
        return userService.updateUserById(userDTO);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteById(@PathVariable("userId") Long userId){
        userService.deleteUserById(userId);
        return new ResponseEntity<>("200", HttpStatus.OK);
    }

}
