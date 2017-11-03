package com.medicamentos;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private BCryptPasswordEncoder encoder;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		String sqlUser = "select * from (select u.identificacion as username, u.clave as password, "
				+ "u.estado as enabled from usuario as u) as users where username = ?";
		
		String sqlRole = "select * from (select u.identificacion as username, u.rol authority from "
				+ "usuario as u) as authorities where username = ?";

		auth.jdbcAuthentication().dataSource(dataSource).passwordEncoder(encoder).
			usersByUsernameQuery(sqlUser).authoritiesByUsernameQuery(sqlRole);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests()
			.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
			.antMatchers("/user/**").access("hasRole('ROLE_USER')")
			.antMatchers("/inicio").access("authenticated")
			.and().formLogin()
			.loginPage("/login").loginProcessingUrl("/j_spring_security_check")
			.defaultSuccessUrl("/inicio").failureUrl("/login?error")
			.usernameParameter("usuario").passwordParameter("clave").and().logout()
			.logoutSuccessUrl("/login").logoutUrl("/j_spring_security_logout")
			.and().exceptionHandling().accessDeniedPage("/403").and().csrf();

	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		
		web.ignoring().antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
	}

	
}
