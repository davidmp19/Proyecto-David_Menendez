package com.spring.start.h2.seguridad;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {


	  
	    @Bean
	    BCryptPasswordEncoder bCryptPasswordEncoder() {
	        return new BCryptPasswordEncoder();
	    }
	    
	    
	    
	   
	   @Bean
	   AuthenticationManager authenticationManager(
			   HttpSecurity http, 
			   BCryptPasswordEncoder bCryptPasswordEncoder, 
			   UserDetailsService userDetailsService) throws Exception {
		   
	       return http.getSharedObject(AuthenticationManagerBuilder.class)
	         .userDetailsService(userDetailsService)
	         .passwordEncoder(bCryptPasswordEncoder)
	         .and()
	         .build();
	   }	
	    @Bean
	    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	        http.authorizeRequests()
	                .requestMatchers("/login", "/css/**", "/js/**").permitAll()
	                .requestMatchers("/repuestos", "/suministra", "/proveedores", "/coches").permitAll()
	                .requestMatchers("/repuestos/del/**", "/proveedor/del/**").hasAuthority("ADMIN")
	                .anyRequest().authenticated()
	                .and()
	                .formLogin()
//	                .loginPage("/login")
//	                .defaultSuccessUrl("/", true)
//	                .failureUrl("/login?error=true")
//	                .permitAll()
//	                .and()
//	                .logout()
//	                .logoutUrl("/logout")
//	                .logoutSuccessUrl("/login?logout=true")
//	                .invalidateHttpSession(true)
//	                .deleteCookies("JSESSIONID")
	                .permitAll();

	        return http.build();
	    }
	   
}
