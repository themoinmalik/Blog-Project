package com.blogmaker.blog.repository;

import com.blogmaker.blog.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepo extends JpaRepository<Post, Long> {


    List<Post> findByUserId(Long userId);



}
