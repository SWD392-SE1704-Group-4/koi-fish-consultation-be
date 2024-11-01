package com.fengshui.corebusiness.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    @Autowired
    Filter filter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(cors -> cors.disable())
                .authorizeHttpRequests(req -> req
//                        .requestMatchers(HttpMethod.OPTIONS,"/**").permitAll()
//                        .requestMatchers("/api/v1/**").permitAll() // Adjust public endpoints as necessary
//                        .requestMatchers("/api/v1/koi-fish/**").permitAll()
//                        .requestMatchers("/api/v1/advertisement/get-list").permitAll()
//                        .requestMatchers("/api/v1/advertisement/get-list-advertisement-type").permitAll()
//                        .requestMatchers("/api/fengshui/**").permitAll()
//                        .requestMatchers("/api/v1/app-user/**").permitAll()
//                        .requestMatchers("api/v1/fengshui-direction/**").permitAll()
//                        .requestMatchers("api/v1/fengshui-element/**").permitAll()
                                .requestMatchers("/**")
                                .permitAll()
                                .anyRequest()
                                .authenticated()
                )
                .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class).build();
    }
}
