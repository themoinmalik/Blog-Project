package com.blogmaker.blog.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String userName;

    private String authority;

    private String password;

    private String email;

    
    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    private List<Post> posts;

    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    private List<Comment> comments;


    @Override
    public String toString() {
        return "BlogUser{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", posts=" + posts.size() +
                ", comments=" + comments.size() +
                '}';
    }
}
