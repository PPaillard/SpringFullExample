package com.wcs.springfullexample.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wcs.springfullexample.model.Article;

public interface ArticlesRepository extends JpaRepository<Article, Long> {

}
