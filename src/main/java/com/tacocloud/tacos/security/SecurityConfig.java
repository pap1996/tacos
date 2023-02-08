package com.tacocloud.tacos.security;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.tacocloud.tacos.domain.User;
import com.tacocloud.tacos.repository.UserRepository;


@Configuration
public class SecurityConfig {

	
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	/*// Change from InMemoryUserDetailsManager to Customerized User Authentication
	@Bean
	public UserDetailsService userDetailsService(PasswordEncoder encoder) {
		List<UserDetails> usersList = new ArrayList<UserDetails>();
		usersList.add(new User("buzz", 
				               encoder.encode("password"),
				               Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"))));
		
		usersList.add(new User("woody", 
	               encoder.encode("password"),
	               Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"))));
		
		return new InMemoryUserDetailsManager(usersList);
		
	}
	*/
	
	@Bean
	public UserDetailsService userDetailsService(UserRepository userRepo) {
		return username -> {
			User user = userRepo.findByUsername(username);
			
			if(user != null) return user;
			
			throw new UsernameNotFoundException("User with name: " + username + " is not found!"); 
			
		};
	}
	
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
		return httpSecurity
				.authorizeRequests()
				.antMatchers("/design", "/orders").hasRole("USER")
				.antMatchers("/", "/**").permitAll()
				.and()
				.formLogin()
					.loginPage("/login")
					.usernameParameter("username")
					.passwordParameter("password-test")
					.defaultSuccessUrl("/design")
					
				.and()
				.build();
	}
}
