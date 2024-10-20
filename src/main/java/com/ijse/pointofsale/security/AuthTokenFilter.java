package com.ijse.pointofsale.security;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.web.filter.OncePerRequestFilter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class AuthTokenFilter extends OncePerRequestFilter  {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    private static final String[] WHITE_LIST_URL = { 
        "/api/v1/auth/*", 
        "/swagger-ui/**", 
        "/v3/api-docs/**", 
        "/v2/api-docs/**", 
        "/swagger-resources/**", 
        "/swagger-ui.html" 
    };

    private String extractTokenFromRequest(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.replace("Bearer ", "");
        } else {
            return null;
        }
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {

        // Extract token from the request
        String jwt = extractTokenFromRequest(request);

        try {
            // Validate the token if present
            if (jwt != null && jwtUtils.validateJwt(jwt)) {
                // Get username from JWT and load user details
                String username = jwtUtils.getUsernameFromJwt(jwt);

                UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                // Create an authentication token
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());

                authenticationToken.setDetails(new WebAuthenticationDetails(request));

                // Set the authentication in the security context
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Continue the filter chain
        filterChain.doFilter(request, response);
    }

    // Override this method to skip filtering for specific endpoints
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        for (String path : WHITE_LIST_URL) {
            if (requestURI.startsWith(path.replace("**", ""))) {
                return true;
            }
        }
        return false;
    }
    
}