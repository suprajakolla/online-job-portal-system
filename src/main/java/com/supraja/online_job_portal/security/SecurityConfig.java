package com.supraja.online_job_portal.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())

                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )

                .authorizeHttpRequests(auth -> auth

                        // -----------------------------
                        // Public APIs
                        // -----------------------------
                        .requestMatchers(
                                "/swagger-ui/**",
                                "/v3/api-docs/**",
                                "/api/auth/**"
                        ).permitAll()

                        // User Registration
                        .requestMatchers(HttpMethod.POST, "/api/users")
                        .permitAll()

                        // -----------------------------
                        // User APIs
                        // -----------------------------
                        .requestMatchers(HttpMethod.GET, "/api/users/**")
                        .hasRole("ADMIN")

                        .requestMatchers(HttpMethod.PUT, "/api/users/**")
                        .hasAnyRole("ADMIN", "JOB_SEEKER")

                        .requestMatchers(HttpMethod.DELETE, "/api/users/**")
                        .hasRole("ADMIN")

                        // -----------------------------
                        // Company APIs
                        // -----------------------------
                        .requestMatchers("/api/companies/**")
                        .hasRole("ADMIN")

                        // -----------------------------
                        // Job APIs
                        // -----------------------------

                        // Everyone can view jobs
                        .requestMatchers(HttpMethod.GET, "/api/jobs/**")
                        .hasAnyRole("ADMIN", "RECRUITER", "JOB_SEEKER")

                        // Recruiter & Admin can create jobs
                        .requestMatchers(HttpMethod.POST, "/api/jobs")
                        .hasAnyRole("ADMIN", "RECRUITER")

                        // Recruiter & Admin can update jobs
                        .requestMatchers(HttpMethod.PUT, "/api/jobs/**")
                        .hasAnyRole("ADMIN", "RECRUITER")

                        // Only Admin can delete jobs
                        .requestMatchers(HttpMethod.DELETE, "/api/jobs/**")
                        .hasRole("ADMIN")

                        // -----------------------------
                        // Application APIs
                        // -----------------------------

                        // Job seekers apply for jobs
                        .requestMatchers(HttpMethod.POST, "/api/applications")
                        .hasRole("JOB_SEEKER")

                        // Admin & Recruiter can view applications
                        .requestMatchers(HttpMethod.GET, "/api/applications/**")
                        .hasAnyRole("ADMIN", "RECRUITER")

                        // Job seeker can update application
                        .requestMatchers(HttpMethod.PUT, "/api/applications/**")
                        .hasRole("JOB_SEEKER")

                        // Admin can delete applications
                        .requestMatchers(HttpMethod.DELETE, "/api/applications/**")
                        .hasRole("ADMIN")

                        // Any other request
                        .anyRequest()
                        .authenticated()
                )

                .addFilterBefore(
                        jwtAuthenticationFilter,
                        UsernamePasswordAuthenticationFilter.class
                );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration config) throws Exception {

        return config.getAuthenticationManager();
    }
}