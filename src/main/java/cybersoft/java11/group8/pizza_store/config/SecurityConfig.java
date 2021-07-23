package cybersoft.java11.group8.pizza_store.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import cybersoft.java11.group8.pizza_store.security.jwt.JwtAuthorizationFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	@Autowired
	private JwtAuthorizationFilter jwtAuthorizationFilter;
	
	@Autowired
	private UserDetailsService userDetailsService;

	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService)
			.passwordEncoder(getPasswordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// enable cors
		http.cors();
		// disable csrf for development environment
		http.csrf().disable();
		// configure authentication for apis	
		http.antMatcher("/api/**").authorizeRequests()
			.antMatchers("/swagger-ui.html").permitAll()
			.antMatchers("/api/**").permitAll()
			.antMatchers("/login").permitAll()
			.anyRequest().authenticated();
		// make server stateless
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		// add jwt filter
		http.addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		// TODO Auto-generated method stub
		return super.authenticationManagerBean();
	}
	
	
	
	

}
