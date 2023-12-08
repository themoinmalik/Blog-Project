package com.blogmaker.blog.service;


import com.blogmaker.blog.dtos.UserDTO;
import com.blogmaker.blog.entity.User;
import com.blogmaker.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDTO findUserById(Long userId) {

        Optional<User> user = userRepository.findById(userId);
        UserDTO userDTO = new UserDTO();
        userDTO.setUserName(user.get().getUserName());
        userDTO.setUserId(user.get().getId());

        return userDTO;

    }

    public ResponseEntity<User> createUser(User user) {
        userRepository.save(user);
        return new ResponseEntity<>(HttpStatus.CREATED);

    }
}
