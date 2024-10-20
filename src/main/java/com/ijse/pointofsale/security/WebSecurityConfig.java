package com.ijse.pointofsale.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;


@Configuration
@EnableMethodSecurity

public class WebSecurityConfig {

      private static final String[] WHITE_LIST_URL = { 
        "/api/v1/auth/**", 
        "/v2/api-docs", 
        "/v3/api-docs", 
        "/v3/api-docs/**", 
        "/swagger-resources", 
        "/swagger-resources/**", 
        "/configuration/ui", 
        "/configuration/security", 
        "/swagger-ui/**", 
        "/webjars/**", 
        "/swagger-ui.html", 
        "/api/auth/**", 
        "/api/test/**", 
        "/authenticate" ,
          "/auth/login",
          "/users",
           "/customer/**",
            "/itemcategory/**",
             "/item/**",
              "/orders/**",
                "/stock/**"
        
    };

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Bean
    public UserDetailsService userDetailsService() {
        return userDetailsService;
    }

    @Bean
    public AuthTokenFilter authTokenFilter() {
        return new AuthTokenFilter();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
            .cors(cors -> cors.configurationSource(corsConfigurationSource())) // Enable CORS configuration
            .csrf(csrf -> csrf.disable())  // Disable CSRF since tokens are used
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))  // Stateless sessions
            .authorizeHttpRequests(
                auth -> auth
                    // Allow access to all whitelisted URLs
                    .requestMatchers(WHITE_LIST_URL).permitAll()
                    // Any other request must be authenticated
                    .anyRequest().authenticated()
            );

        // Add the custom authentication token filter
        httpSecurity.authenticationProvider(authenticationProvider());
        httpSecurity.addFilterBefore(authTokenFilter(), UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }

    // CORS configuration source bean
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("*")); // Allow all origins, adjust this for specific origins in production
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("Authorization", "Content-Type"));
        configuration.setAllowCredentials(true); // Allow credentials like cookies, if needed

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration); // Apply CORS settings to all endpoints
        return source;
    }
}