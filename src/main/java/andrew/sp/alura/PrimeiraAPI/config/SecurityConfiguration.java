package andrew.sp.alura.PrimeiraAPI.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import andrew.sp.alura.PrimeiraAPI.filter.SecurityFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
	
	@Autowired
	private SecurityFilter securityFilter;
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
		
		return httpSecurity.csrf(csrf -> csrf.disable())
		.authorizeHttpRequests(authorize -> authorize.antMatchers(HttpMethod.POST, "/usuario/login").permitAll())
		.authorizeHttpRequests().anyRequest().authenticated()
		.and().addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
		.sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		.build();
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
