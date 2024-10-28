package com.fengshui.corebusiness.config;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.Claim;
import com.fengshui.common.security.JwtService;
import com.fengshui.common.services.AppUserService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Component
@ComponentScan(basePackages = "com.fengshui.common.services")
public class Filter extends OncePerRequestFilter {
    @Autowired
    private JwtService jwtService;

    @Autowired
    private AppUserService appUserService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = null;
        String authHeader = request.getHeader("Authorization");
        String uri = request.getRequestURI();
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);
            Claims userClaims = jwtService.decodeJwt(token);
            // Extract groups from userClaims
            List<String> groups = userClaims.get("cognito:groups", List.class);
            if (groups != null && !groups.isEmpty()) {
                // Convert each group to a SimpleGrantedAuthority, e.g., "ROLE_Member"
                List<SimpleGrantedAuthority> authorities = groups.stream()
                        .map(group -> new SimpleGrantedAuthority("ROLE_" + group))
                        .collect(Collectors.toList());

                // Create an authentication token with the extracted roles
                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(userClaims.getSubject(), null, authorities);

                // Set the authentication token in the security context
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);

            }

        }

        filterChain.doFilter(request, response);
    }
}
