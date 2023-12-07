package com.blogmaker.blog.dtos;


import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UserDto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    String name;

    String email;

    String password;



}
