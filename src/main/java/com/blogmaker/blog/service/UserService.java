package com.blogmaker.blog.service;


import com.blogmaker.blog.dtos.UserDTO;
import com.blogmaker.blog.entity.User;
import com.blogmaker.blog.repository.UserRepository;
import jakarta.transaction.Transactional;
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

    public ResponseEntity<UserDTO> findUserById(Long userId) {

        Optional<User> user = userRepository.findById(userId);
        UserDTO userDTO = new UserDTO();
        userDTO.setUserName(user.get().getUserName());
        userDTO.setUserId(user.get().getId());
        userDTO.setUserEmail(user.get().getEmail());
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    public ResponseEntity<User> createUser(User user) {
        userRepository.save(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Transactional
    public ResponseEntity<UserDTO> updateUserById(UserDTO userDTO) {

        User user = new User();
        user.setId(userDTO.getUserId());
        user.setUserName(userDTO.getUserName());
        user.setEmail(userDTO.getUserEmail());
        userRepository.save(user);
        return new ResponseEntity<>(userDTO, HttpStatus.ACCEPTED);

    }

    @Transactional
    public void deleteUserById(Long userId) {
        userRepository.deleteById(userId);
    }
}
