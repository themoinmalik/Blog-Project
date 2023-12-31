package com.blogmaker.blog.dtos;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PostDTO {

    private String title;

    private String content;

    private String imageName;

    private Long userId;

    private Long categoryId;

}
