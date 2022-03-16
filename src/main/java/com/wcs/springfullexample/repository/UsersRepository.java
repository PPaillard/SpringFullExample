package com.wcs.springfullexample.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wcs.springfullexample.model.User;

public interface UsersRepository extends JpaRepository<User, Long> {

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);

	Optional<User> findByUsername(String username);
}
