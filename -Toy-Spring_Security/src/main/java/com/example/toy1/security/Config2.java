// package login.oauthtest4.global.config;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.security.web.SecurityFilterChain;
//
// import com.fasterxml.jackson.databind.ObjectMapper;
//
// import lombok.RequiredArgsConstructor;
//
// /**
//  * 인증은 CustomJsonUsernamePasswordAuthenticationFilter에서 authenticate()로 인증된 사용자로 처리
//  * JwtAuthenticationProcessingFilter는 AccessToken, RefreshToken 재발급
//  */
// @Configuration
// @EnableWebSecurity(debug = true)
// @RequiredArgsConstructor
// public class Config2 {
//
// 	// private final LoginService loginService;
// 	// private final JwtService jwtService;
// 	// private final UserRepository userRepository;
// 	private final ObjectMapper objectMapper;
// 	// private final OAuth2LoginSuccessHandler oAuth2LoginSuccessHandler;
// 	// private final OAuth2LoginFailureHandler oAuth2LoginFailureHandler;
// 	// private final CustomOAuth2UserService customOAuth2UserService;
//
// 	@Bean
//     public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
//         httpSecurity
// 				// .formLogin(it->it.disable())
//                 .csrf(AbstractHttpConfigurer::disable)
//                 .cors(AbstractHttpConfigurer::disable)
//                 .formLogin(AbstractHttpConfigurer::disable)
//                 .httpBasic(AbstractHttpConfigurer::disable)
//                 .authorizeHttpRequests(request -> {
//                     request.requestMatchers("/sign-up").permitAll()
//                             .anyRequest().authenticated();
//                 });
//         return httpSecurity.build();
//     }
//
// 	@Bean
// 	public PasswordEncoder passwordEncoder() {
// 		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
// 	}
//
// 	/**
// 	 * AuthenticationManager 설정 후 등록
// 	 * PasswordEncoder를 사용하는 AuthenticationProvider 지정 (PasswordEncoder는 위에서 등록한 PasswordEncoder 사용)
// 	 * FormLogin(기존 스프링 시큐리티 로그인)과 동일하게 DaoAuthenticationProvider 사용
// 	 * UserDetailsService는 커스텀 LoginService로 등록
// 	 * 또한, FormLogin과 동일하게 AuthenticationManager로는 구현체인 ProviderManager 사용(return ProviderManager)
// 	 *
// 	 */
// 	// @Bean
// 	// public AuthenticationManager authenticationManager() {
// 	// 	DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
// 	// 	provider.setPasswordEncoder(passwordEncoder());
// 	// 	provider.setUserDetailsService(loginService);
// 	// 	return new ProviderManager(provider);
// 	// }
//
// 	/**
// 	 * 로그인 성공 시 호출되는 LoginSuccessJWTProviderHandler 빈 등록
// 	 */
// 	// @Bean
// 	// public LoginSuccessHandler loginSuccessHandler() {
// 	// 	return new LoginSuccessHandler(jwtService, userRepository);
// 	// }
// 	//
// 	// /**
// 	//  * 로그인 실패 시 호출되는 LoginFailureHandler 빈 등록
// 	//  */
// 	// @Bean
// 	// public LoginFailureHandler loginFailureHandler() {
// 	// 	return new LoginFailureHandler();
// 	// }
// 	//
// 	// /**
// 	//  * CustomJsonLoginFilter 빈 등록
// 	//  * 커스텀 필터를 사용하기 위해 만든 커스텀 필터를 Bean으로 등록
// 	//  * setAuthenticationManager(authenticationManager())로 위에서 등록한 AuthenticationManager(ProviderManager) 설정
// 	//  * 로그인 성공 시 호출할 handler, 실패 시 호출할 handler로 위에서 등록한 handler 설정
// 	//  * oauth2 로그인이 아닌 즉 기본로그인
// 	//  */
// 	//
// 	// @Bean
// 	// public CustomJsonLoginFilter customJsonLoginFilter() {
// 	// 	CustomJsonLoginFilter customJsonLoginFilter = new CustomJsonLoginFilter(objectMapper);
// 	// 	customJsonLoginFilter.setAuthenticationManager(authenticationManager());
// 	// 	customJsonLoginFilter.setAuthenticationSuccessHandler(loginSuccessHandler());
// 	// 	customJsonLoginFilter.setAuthenticationFailureHandler(loginFailureHandler());
// 	// 	return customJsonLoginFilter;
// 	// }
// 	//
// 	// @Bean
// 	// public JwtAuthenticationProcessingFilter jwtAuthenticationProcessingFilter() {
// 	// 	JwtAuthenticationProcessingFilter jwtAuthenticationFilter = new JwtAuthenticationProcessingFilter(jwtService,
// 	// 		userRepository);
// 	// 	return jwtAuthenticationFilter;
// 	// }
//
// }