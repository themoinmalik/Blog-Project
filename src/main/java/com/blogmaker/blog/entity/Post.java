package com.blogmaker.blog.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity(name = "posts")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Post implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content;

    private LocalDate createdOn;

    private LocalDate updatedOn;

    @ManyToOne(cascade = {CascadeType.ALL},fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(cascade = {CascadeType.DETACH}, fetch = FetchType.LAZY)
    private Category category;

    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    private List<Comment> comments;



}
