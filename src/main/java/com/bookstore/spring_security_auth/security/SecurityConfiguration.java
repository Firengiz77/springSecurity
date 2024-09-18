package com.bookstore.spring_security_auth.security;

import com.bookstore.spring_security_auth.service.impl.Oauth2AuthenticationSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import com.bookstore.spring_security_auth.handler.CustomAuthenticationFailureHandler;

@Configuration
public class SecurityConfiguration {

	private final CustomAuthenticationFailureHandler customAuthenticationFailureHandler;
	private final Oauth2AuthenticationSuccessHandler oauth2AuthenticationSuccessHandler;

	public SecurityConfiguration(CustomAuthenticationFailureHandler customAuthenticationFailureHandler ,
                                  Oauth2AuthenticationSuccessHandler oauth2AuthenticationSuccessHandler) {
		this.customAuthenticationFailureHandler = customAuthenticationFailureHandler;
		this.oauth2AuthenticationSuccessHandler = oauth2AuthenticationSuccessHandler;
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
				.requiresChannel(channel -> channel.anyRequest().requiresSecure())
				.authorizeHttpRequests(authz -> authz
						.requestMatchers("/adduser", "/login", "/login-error", "/login-verified", "/login-disabled", "/verify/email").permitAll()
						.anyRequest().authenticated()
				)
				.formLogin(form -> form
						.loginPage("/login")
						.failureHandler(customAuthenticationFailureHandler)
				)
				.rememberMe(rememberMe -> rememberMe
						.key("remember-me-key")
						.rememberMeCookieName("course-tracker-remember-me")
				)
				.logout(logout -> logout
						.deleteCookies("course-tracker-remember-me")
						.logoutSuccessUrl("/login?logout")
				)
				.oauth2Login(oauth2 -> oauth2
						.loginPage("/login")
						.successHandler(oauth2AuthenticationSuccessHandler)
				);

		return http.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
