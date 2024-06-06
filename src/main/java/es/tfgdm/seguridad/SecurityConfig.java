package es.tfgdm.seguridad;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
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
	    return http
	                .formLogin(form->form.loginPage("/login").defaultSuccessUrl("/", true)
	                		.permitAll())
	                .logout()
	                .logoutSuccessUrl("/") 
	                .permitAll() 
	                .and()
	                .authorizeRequests(auth->auth
	                		.requestMatchers("/repuestos","/proveedores", "/coches","/login","/", "/usuario/registrar", "/estadisticas").permitAll()
	                		.requestMatchers("/repuestos/del/**", "/proveedor/del/**","/suministra/del/**","/coches/del/**","/usuario/del/**").hasAuthority("ADMIN")
	                		.requestMatchers("/repuesto/add", "/proveedor/add","/suministra/add","/coche/add").hasAuthority("ADMIN")
	                		.requestMatchers("/repuesto/edit/**","/proveedor/edit/**","/coche/edit/**").hasAuthority("ADMIN")
	                		.requestMatchers("/suministra","/usuarios").hasAuthority("ADMIN")
	                		.requestMatchers("/repuestos/**", "/proveedores/**", "/coches/**","buscarRepuesto/**","/logout").authenticated()
	                		.requestMatchers("/usuarios/**").authenticated()
	                		)
	                .build();
 
	    } 
	   
} 
