package com.employee_management.management.security;

import com.employee_management.management.utility.ResponseHelper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http,JwtRequestFilter jwtRequestFilter) throws Exception {
        try{
            http.csrf(csrf -> csrf.disable())
                    .authorizeHttpRequests(
                            auth -> auth
                                    .requestMatchers("/api/auth/**").permitAll()
                                    .requestMatchers("/api/user/**").hasRole("USER")
                                    .anyRequest().permitAll()
                    )
                    .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

            return http.build();
        }catch (Exception e){
            System.out.println("Error in SecurityFilterChain"+e.getMessage());
            ResponseHelper.createErrorResponse(HttpStatus.UNAUTHORIZED,e.getMessage(),false,null);
            throw e;
        }
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
