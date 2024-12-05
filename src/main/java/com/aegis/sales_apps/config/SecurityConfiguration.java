package com.aegis.sales_apps.config;

/*
Created By IntelliJ IDEA 2022.1.3 (Community Edition)
Build #IC-221.5921.22, built on June 21, 2022
@Author JEJE a.k.a Jefri S
Java Developer
Created On 10/3/2023 10:47
@Last Modified 10/3/2023 10:47
Version 1.0
*/

import com.aegis.sales_apps.security.AuthEntryPoint;
import com.aegis.sales_apps.security.AuthTokenFilter;
import com.aegis.sales_apps.security.CustomAccessDeniedHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        prePostEnabled = true
)
public class SecurityConfiguration {

    @Autowired
    private AuthTokenFilter authTokenFilter;

    @Autowired
    private AuthEntryPoint authEntryPoint;

    @Autowired
    private CustomAccessDeniedHandler customAccessDeniedHandler;

    @Autowired
    private UserDetailsService userDetailsService;

    public SecurityConfiguration(AuthTokenFilter authTokenFilter, AuthEntryPoint authEntryPoint, CustomAccessDeniedHandler customAccessDeniedHandler, UserDetailsService userDetailsService) {
        this.authTokenFilter = authTokenFilter;
        this.authEntryPoint = authEntryPoint;
        this.customAccessDeniedHandler = customAccessDeniedHandler;
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .exceptionHandling(exceptionHandling ->
                        exceptionHandling
                                .authenticationEntryPoint(authEntryPoint)
                                .accessDeniedHandler(customAccessDeniedHandler)
                )
                .sessionManagement(sessionManagement ->
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authorizeHttpRequests(authorize ->
                        authorize
                                // Endpoints yang dapat diakses oleh semua role
                                .requestMatchers("/api/v1/auth/**").permitAll()

                                .requestMatchers("/api/v1/report/**").permitAll()

                                // Akses khusus untuk admin
                                .requestMatchers("/api/v1/admin/**").hasRole("ADMIN")

//                                .requestMatchers("/api/v1/admin/**").permitAll()

                                .requestMatchers("/api/v1/product/**").hasRole("ADMIN")

                                // Akses untuk kasir dan admin
                                .requestMatchers("/api/v1/transactions/**", "/api/v1/reports/**").hasAnyRole("ADMIN", "CASHIER")

                                // Semua endpoint lain harus diautentikasi
                                .anyRequest().authenticated()
                )
                .addFilterBefore(authTokenFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

}