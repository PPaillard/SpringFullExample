package com.wcs.springfullexample.security.userdetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.wcs.springfullexample.model.Role;
import com.wcs.springfullexample.model.User;

public class UserDetailsImpl implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private String username;

	private String password;

	private List<Role> authorities;

	/*
	 * Méthode pour créer un UserDetailsImpl depuis un User
	 */
	public static UserDetailsImpl build(User user) {

		UserDetailsImpl userDetailsImpl = new UserDetailsImpl();
		userDetailsImpl.setId(user.getId());
		userDetailsImpl.setUsername(user.getUsername());
		userDetailsImpl.setPassword(user.getPassword());
		userDetailsImpl.setAuthorities(user.getRoles());
		return userDetailsImpl;
	}

	@Override
	public List<Role> getAuthorities() {
		return this.authorities;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setAuthorities(List<Role> authorities) {
		this.authorities = authorities;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
