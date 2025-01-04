//package com.example.demo.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//public class SecurityConfig {
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//            // URLパスごとの認可設定
//            .authorizeHttpRequests(authz -> authz
//                .requestMatchers("/webjars/**", "/css/**", "/js/**", "/h2-console/**").permitAll() // 静的リソースは全て許可
//                .requestMatchers("/login", "/signup").permitAll() // ログインとサインアップは許可
//                .anyRequest().authenticated() // その他のリクエストは認証が必要
//            )
//            // CSRF対策を無効化 (H2コンソールのため)
//            .csrf(csrf -> csrf.disable())
//            // H2コンソール用にフレームオプションを無効化
//            .headers(headers -> headers.frameOptions(frame -> frame.disable()));
//
//        return http.build();
//    }
//}