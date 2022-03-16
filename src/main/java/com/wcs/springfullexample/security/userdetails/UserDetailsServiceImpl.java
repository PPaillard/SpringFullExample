package com.wcs.springfullexample.security.userdetails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.wcs.springfullexample.model.User;
import com.wcs.springfullexample.repository.UsersRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	UsersRepository usersRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = usersRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("L'utilisateur n'existe pas en BDD"));

		return UserDetailsImpl.build(user);
	}

}
