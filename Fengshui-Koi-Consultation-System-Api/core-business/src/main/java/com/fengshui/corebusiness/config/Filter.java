package com.fengshui.corebusiness.config;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.Claim;
import com.fengshui.common.repository.postgresql.IAppUserRepository;
import com.fengshui.common.repository.postgresql.dto.AppUserDTO;
import com.fengshui.common.repository.postgresql.entities.AppUserEntity;
import com.fengshui.common.repository.postgresql.enums.AppUserRole;
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
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@ComponentScan(basePackages = "com.fengshui.common.services")
public class Filter extends OncePerRequestFilter {
    @Autowired
    private JwtService jwtService;

    @Autowired
    private IAppUserRepository appUserRepository;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = null;
        String authHeader = request.getHeader("Authorization");
        String uri = request.getRequestURI();

//        if (
//                uri.contains("/login") ||
//                        uri.contains("/fengshui-direction") ||
//                        uri.contains("/fengshui-element") ||
//                        uri.contains("/advertisement/get-list") ||
//                        uri.contains("/fengshui") ||
//                        uri.contains("/heavenly-earthly-elements") ||
//                        uri.contains("/koi-fish/get-list")) {
//
//            filterChain.doFilter(request, response);
//            return;
//        }

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);
            Claims userClaims = jwtService.decodeJwt(token);

            UUID sub = UUID.fromString(userClaims.get("sub", String.class));
            Optional<AppUserEntity> appUserOptional = appUserRepository.findById(sub);

            if (appUserOptional.isPresent()) {
                AppUserEntity appUser = appUserOptional.get();

                String roleName = appUser.getRole().name();  // Adjust if necessary
                SimpleGrantedAuthority authority = new SimpleGrantedAuthority(roleName);

                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(sub, null, List.of(authority));

                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}
