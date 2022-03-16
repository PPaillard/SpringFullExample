package com.wcs.springfullexample.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wcs.springfullexample.model.ERole;
import com.wcs.springfullexample.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

	Optional<Role> findByName(ERole roleUser);
}
