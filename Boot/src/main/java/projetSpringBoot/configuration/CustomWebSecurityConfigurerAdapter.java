package projetSpringBoot.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import projetSpringBoot.service.AuthService;

@Configuration
public class CustomWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

	@Autowired
	private AuthService authService;

	// Règles d'accès aux url
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		// @formatter:off
		http
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
			.authorizeRequests().antMatchers(HttpMethod.OPTIONS).anonymous()
			.and()
			.csrf().disable()
			.authorizeRequests().antMatchers("/rest/inscription","/rest/inscription/**").permitAll()
			.and()
			.authorizeRequests().antMatchers("/rest/**").authenticated().and().httpBasic()
			.and()
			.authorizeRequests().anyRequest().permitAll();
			
			
			//TODO
//			.authorizeRequests().antMatchers("/**").permitAll();
		// @formatter:on
	}

	// méthode d'authentification
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(authService);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
