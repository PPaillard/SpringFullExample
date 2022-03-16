package com.wcs.springfullexample.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wcs.springfullexample.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
