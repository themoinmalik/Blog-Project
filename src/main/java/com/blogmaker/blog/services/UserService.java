package com.blogmaker.blog.services;

import com.blogmaker.blog.dtos.UserDto;
import com.blogmaker.blog.entities.User;

import java.util.List;

public interface UserService {

    User getUser(Long Id);

    List<User> getAllUsers();

    User createUser(User user);

    User updateUser(UserDto userDto, Long userId);

    void deleteUser(Long userId);
}
