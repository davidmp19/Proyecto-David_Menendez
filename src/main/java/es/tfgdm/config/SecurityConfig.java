package es.tfgdm.config;

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

	// Configuración del codificador de contraseñas BCrypt
	@Bean
	BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	// Configuración del administrador de autenticación
	@Bean
	AuthenticationManager authenticationManager(HttpSecurity http, BCryptPasswordEncoder bCryptPasswordEncoder,
			UserDetailsService userDetailsService) throws Exception {

		return http.getSharedObject(AuthenticationManagerBuilder.class).userDetailsService(userDetailsService)
				.passwordEncoder(bCryptPasswordEncoder).and().build();
	}

	// Configuración de las reglas de seguridad
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		return http
				// Configuración del inicio de sesión y cierre de sesión personalizados
				.formLogin(form -> form.loginPage("/login").defaultSuccessUrl("/", true).permitAll()).logout()
				.logoutSuccessUrl("/").permitAll().and()
				// Configuración de las autorizaciones
				.authorizeRequests(auth -> auth
						.requestMatchers("/repuestos", "/proveedores", "/coches", "/login", "/", "/usuario/registrar")
						.permitAll()
						.requestMatchers("/repuestos/del/**", "/proveedor/del/**", "/suministra/del/**",
								"/coches/del/**", "/usuario/del/**")
						.hasAuthority("ADMIN")
						.requestMatchers("/repuesto/add", "/proveedor/add", "/suministra/add", "/coche/add")
						.hasAuthority("ADMIN")
						.requestMatchers("/repuesto/edit/**", "/proveedor/edit/**", "/coche/edit/**")
						.hasAuthority("ADMIN").requestMatchers("/suministra", "/usuarios").hasAuthority("ADMIN")
						.requestMatchers("/repuestos/**", "/proveedores/**", "/coches/**", "buscarRepuesto/**",
								"/logout","/estadisticas")
						.authenticated().requestMatchers("/usuarios/**").authenticated())
				// Configuración de la página de acceso denegado
				.exceptionHandling(exception -> exception.accessDeniedPage("/access-denied")).build();

	}

}
