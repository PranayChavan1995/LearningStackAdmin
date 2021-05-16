package com.example.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;

import com.example.dao.impl.HibernateTokenRepositoryImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	@Qualifier("customUserDetailsService")
	UserDetailsService userDetailsService;
	@Autowired
	HibernateTokenRepositoryImpl tokenRepository;
	@Autowired
	private CustomAuthenticationProvider authProvider;

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.formLogin().loginPage("/loginNew.jsp").loginProcessingUrl("/login").defaultSuccessUrl("/", true)
				.permitAll().and().authorizeRequests()
				.antMatchers("/login", "/", "/static/css/fonts/untitled-font-2*", "/static/img/favicon.ico","/static/img/i-smart.jpg").permitAll()
				.anyRequest().authenticated().and().rememberMe().rememberMeParameter("remember-me")
				.tokenRepository(tokenRepository).tokenValiditySeconds(10000000).and().exceptionHandling()
				.accessDeniedPage("/Access_Denied").and().csrf().disable().headers().frameOptions().sameOrigin();
//		http.formLogin().and().authorizeRequests()
//        .antMatchers("/signIn", "/login", "/").permitAll().anyRequest().authenticated()
//        .and().rememberMe().rememberMeParameter("remember-me")
//				.tokenRepository(tokenRepository).tokenValiditySeconds(10000)
//				.and().exceptionHandling()
//				.accessDeniedPage("/Access_Denied").and().csrf().disable();

//		 http
//	        .authorizeRequests()
//	            .anyRequest().authenticated()
//	            .and()
//	        .formLogin()
//	            .loginPage("/login") 
//	            .permitAll();  
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authProvider);
	}

	@Bean
	public AuthenticationTrustResolver getAuthenticationTrustResolver() {
		return new AuthenticationTrustResolverImpl();
	}

	@Bean
	public PersistentTokenBasedRememberMeServices getPersistentTokenBasedRememberMeServices() {
		PersistentTokenBasedRememberMeServices tokenBasedservice = new PersistentTokenBasedRememberMeServices(
				"remember-me", userDetailsService, tokenRepository);
		return tokenBasedservice;
	}

}