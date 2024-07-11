package com.example.social_network.configuration;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;

@Configuration
@EnableWebSecurity
public class AppConfig {

    @Bean
    SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.sessionManagement(management -> management
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(Authorize -> Authorize
                .requestMatchers("/api/**").authenticated()
                .anyRequest().permitAll())
                .addFilterBefore(new jwtValidator(), BasicAuthenticationFilter.class)
                .csrf(csrf -> csrf.disable())
                .cors(cors->cors.configurationSource(corsConfigurationSource()));
        return httpSecurity.build();
    }

    private CorsConfigurationSource corsConfigurationSource() {
        return new CorsConfigurationSource() {
            @Override
            public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                CorsConfiguration corsConfiguration = new CorsConfiguration();
                corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:3000/"));
                corsConfiguration.setAllowedMethods(Collections.singletonList("*"));
                corsConfiguration.setAllowCredentials(true);
                corsConfiguration.setAllowedHeaders(Collections.singletonList("*"));
                corsConfiguration.setExposedHeaders(Arrays.asList("Authorization"));
                corsConfiguration.setMaxAge(3600L);
                return corsConfiguration;
            }
        };
    }


}
