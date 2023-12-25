package com.blogmaker.blog.dtos;


import com.blogmaker.blog.entity.Category;
import com.blogmaker.blog.entity.User;
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

    private Category category;

}
