package com.wcs.springfullexample.security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.wcs.springfullexample.security.jwt.AuthEntryPointJwt;
import com.wcs.springfullexample.security.jwt.AuthTokenFilter;
import com.wcs.springfullexample.security.userdetails.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Value("${wcs.springfullexample.urlsCors}")
	private String urlsCors;
	
	@Autowired
	AuthEntryPointJwt unauthorizedHandler;
	
	@Autowired
	UserDetailsServiceImpl detailsServiceImpl;
	
	@Bean
	public AuthTokenFilter authenticationJwtTokenFilter() {
		return new AuthTokenFilter();
	}
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	@Override
	public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder.userDetailsService(detailsServiceImpl).passwordEncoder(passwordEncoder());
	}
	
	/**
	 * Permet de gérer les requêtes envoyées vers l'API
	 * On autorise toutes requêtes pour l'instant.
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		// désactivation des csrf && activation des CORS
		http.cors().and().csrf().disable();
		// gestion de l'exception d'authentification & parametrage du type de session
		http.exceptionHandling().authenticationEntryPoint(unauthorizedHandler);
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		// TODO : Path à définir
		http.authorizeRequests().antMatchers("/pwet**").permitAll();
		http.authorizeRequests().anyRequest().authenticated();
				
		http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
	}
	
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		// on défini les URLS authorisées au niveau des CORS de l'application
		// récupération de ce qui est dans l'application properties transformé en List<String>
		configuration.setAllowedOrigins(Arrays.asList(urlsCors.split(",")));
		configuration.setAllowedMethods(Arrays.asList("*"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setAllowCredentials(true);
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		// on indique sur quel path s'applique les cors ci dessus.
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}
	
	@Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
