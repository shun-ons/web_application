package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

	private UserDetailsService userDetailsService;
	
	public SecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
	
    @Bean
    protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authz -> authz
                .requestMatchers("/webjars/**", "/css/**", "/js/**", "/h2-console/**").permitAll()
                .requestMatchers("/login", "/signup").permitAll()
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginProcessingUrl("/login") // 認証処理URLを明確に指定
                .loginPage("/login") 
                .failureUrl("/login?error")
                .usernameParameter("mailAddress")
                .passwordParameter("password")
                .defaultSuccessUrl("/mypage", true)
            )
            .csrf(csrf -> csrf.disable())
            .headers(headers -> headers.frameOptions(frame -> frame.disable()));

        return http.build();
    }
    
    @Bean
    protected PasswordEncoder passwordEncoder() {
    	return new BCryptPasswordEncoder();
    }
    
    @Bean
    protected AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);

        PasswordEncoder encoder = passwordEncoder();
        
        /*
        authBuilder
            .inMemoryAuthentication()
                .withUser("user") // user追加
                    .password(encoder.encode("user")) // パスワードを明示的にエンコードなしに設定
                    .roles("GENERAL")
                .and()
                .withUser("admin") // adminを追加
                    .password(encoder.encode("admin")) // パスワードを明示的にエンコードなしに設定
                    .roles("ADMIN");
        */
        
        //ユーザデータ認証
        authBuilder
        .userDetailsService(userDetailsService)
        .passwordEncoder(encoder);

        return authBuilder.build();
    }

}

