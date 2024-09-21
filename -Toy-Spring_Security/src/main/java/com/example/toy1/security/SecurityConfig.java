package com.example.toy1.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutFilter;

import com.example.toy1.domain.user.repository.UserRepository;
import com.example.toy1.security.filter.CustomJsonLoginFilter;
import com.example.toy1.security.filter.JwtAuthenticationProcessingFilter;
import com.example.toy1.security.handler.LoginFailureHandler;
import com.example.toy1.security.handler.LoginSuccessHandler;
import com.example.toy1.security.handler.OAuth2LoginFailureHandler;
import com.example.toy1.security.handler.OAuth2LoginSuccessHandler;
import com.example.toy1.security.oauth2.service.CustomOAuth2UserService;
import com.example.toy1.security.service.JwtService;
import com.example.toy1.security.service.LoginService;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

	private final ObjectMapper objectMapper;

	private final LoginService loginService;
	private final JwtService jwtService;
	private final UserRepository userRepository;
	private final OAuth2LoginSuccessHandler oAuth2LoginSuccessHandler;
	private final OAuth2LoginFailureHandler oAuth2LoginFailureHandler;
	private final CustomOAuth2UserService customOAuth2UserService;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		httpSecurity
			.csrf(AbstractHttpConfigurer::disable)
			.cors(AbstractHttpConfigurer::disable)
			.formLogin(AbstractHttpConfigurer::disable)
			.httpBasic(AbstractHttpConfigurer::disable)
			.headers(headers -> headers.frameOptions().disable())
			// .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.sessionManagement(sessionManage -> sessionManage.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.authorizeHttpRequests(request -> request
				.requestMatchers("/sign-up")
				.permitAll()
				.requestMatchers("/jwt-test")
				.permitAll()
				.requestMatchers("/login/oauth2/code/naver", "/login/oauth2/code/kakao")
				.permitAll()
				.requestMatchers("/", "/css/**", "/images/**", "/js/**", "/favicon.ico", "/h2-console/**", "login")
				.permitAll()
				.anyRequest()
				.authenticated())
			//== 소셜 로그인 설정 ==//
			.oauth2Login()
			.successHandler(oAuth2LoginSuccessHandler) // 동의하고 계속하기를 눌렀을 때 Handler 설정
			.failureHandler(oAuth2LoginFailureHandler) // 소셜 로그인 실패 시 핸들러 설정
			.userInfoEndpoint().userService(customOAuth2UserService); // customUserService 설정

		// 원래 스프링 시큐리티 필터 순서가 LogoutFilter 이후에 로그인 필터 동작
		// 따라서, LogoutFilter 이후에 우리가 만든 필터 동작하도록 설정
		// 순서 : LogoutFilter -> JwtAuthenticationProcessingFilter -> CustomJsonLoginFilter
		httpSecurity.addFilterAfter(customJsonLoginFilter(), LogoutFilter.class);
		// httpSecurity.addFilterAfter(customJsonLoginFilter(), UsernamePasswordAuthenticationFilter.class);

		httpSecurity.addFilterBefore(jwtAuthenticationProcessingFilter(), CustomJsonLoginFilter.class);

		return httpSecurity.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

	/**
	 * AuthenticationManager 설정 후 등록
	 * PasswordEncoder를 사용하는 AuthenticationProvider 지정 (PasswordEncoder는 위에서 등록한 PasswordEncoder 사용)
	 * FormLogin(기존 스프링 시큐리티 로그인)과 동일하게 DaoAuthenticationProvider 사용
	 * UserDetailsService는 커스텀 LoginService로 등록
	 * 또한, FormLogin과 동일하게 AuthenticationManager로는 구현체인 ProviderManager 사용(return ProviderManager)
	 *
	 */
	@Bean
	public AuthenticationManager authenticationManager() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setPasswordEncoder(passwordEncoder());
		provider.setUserDetailsService(loginService);
		return new ProviderManager(provider);
	}

	/**
	 * 로그인 성공 시 호출되는 LoginSuccessJWTProviderHandler 빈 등록
	 */
	@Bean
	public LoginSuccessHandler loginSuccessHandler() {
		return new LoginSuccessHandler(jwtService, userRepository);
	}

	/**
	 * 로그인 실패 시 호출되는 LoginFailureHandler 빈 등록
	 */
	@Bean
	public LoginFailureHandler loginFailureHandler() {
		return new LoginFailureHandler();
	}

	/**
	 * CustomJsonLoginFilter 빈 등록
	 * 커스텀 필터를 사용하기 위해 만든 커스텀 필터를 Bean으로 등록
	 * setAuthenticationManager(authenticationManager())로 위에서 등록한 AuthenticationManager(ProviderManager) 설정
	 * 로그인 성공 시 호출할 handler, 실패 시 호출할 handler로 위에서 등록한 handler 설정
	 * oauth2 로그인이 아닌 즉 기본로그인
	 */

	@Bean
	public CustomJsonLoginFilter customJsonLoginFilter() {
		CustomJsonLoginFilter customJsonLoginFilter = new CustomJsonLoginFilter(objectMapper);
		customJsonLoginFilter.setAuthenticationManager(authenticationManager());
		customJsonLoginFilter.setAuthenticationSuccessHandler(loginSuccessHandler());
		customJsonLoginFilter.setAuthenticationFailureHandler(loginFailureHandler());
		return customJsonLoginFilter;
	}

	@Bean
	public JwtAuthenticationProcessingFilter jwtAuthenticationProcessingFilter() {
		JwtAuthenticationProcessingFilter jwtAuthenticationFilter = new JwtAuthenticationProcessingFilter(jwtService,
			userRepository);
		return jwtAuthenticationFilter;
	}

}