package com.wedit.weditapp.global.config;

import com.wedit.weditapp.global.auth.login.handler.OAuth2LoginFailureHandler;
import com.wedit.weditapp.global.auth.login.handler.OAuth2LoginSuccessHandler;
import com.wedit.weditapp.global.auth.login.service.CustomOAuth2UserService;
import com.wedit.weditapp.global.auth.jwt.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.RequiredArgsConstructor;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

	private final JwtAuthenticationFilter jwtAuthenticationFilter;
	private final CustomOAuth2UserService customOAuth2UserService;
	private final OAuth2LoginSuccessHandler oAuth2LoginSuccessHandler;
	private final OAuth2LoginFailureHandler oAuth2LoginFailureHandler;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
				// 1. 폼로그인 / httpBasic / CSRF 비활성화
				.formLogin(AbstractHttpConfigurer::disable)
				.httpBasic(AbstractHttpConfigurer::disable)
				.csrf(AbstractHttpConfigurer::disable)
				.cors(Customizer.withDefaults())

				// 2. 세션 관련 정책 추가 : 세션 방식 사용 X (오직 JWT만 사용)
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

				.headers(headers ->
						headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin)
				)
				// 3. 권한 설정
				.authorizeHttpRequests(auth -> auth
						.requestMatchers("/v3/api-docs/**",
								"/swagger-ui/**",
								"/api/members/login",
								"/api/members/signup",
								"/oauth2/**")
						.permitAll()
						.anyRequest().authenticated()
				)

				// 4. OAuth2 설정
				.oauth2Login(oauth2 -> oauth2
						.userInfoEndpoint(userInfo ->
								userInfo.userService(customOAuth2UserService)
						)
						.successHandler(oAuth2LoginSuccessHandler)
						.failureHandler(oAuth2LoginFailureHandler)
				)
				.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

		// 5. JWT 인증 필터 등록

		return http.build();
	}

	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();

		configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
		configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PATCH", "DELETE"));
		configuration.setAllowedHeaders(Arrays.asList("*"));
		configuration.setExposedHeaders(Arrays.asList("Authorization", "Authorization_refresh", "accept"));
		configuration.setAllowCredentials(true);

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}
}
