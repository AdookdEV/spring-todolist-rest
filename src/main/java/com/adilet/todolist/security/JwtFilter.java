package com.adilet.todolist.security;

import com.adilet.todolist.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.security.sasl.AuthenticationException;
import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtFilter extends OncePerRequestFilter {
    private final JwtUtils jwtUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        // TODO: 20.11.2023
        String authHeader = request.getHeader("Authorization");
        String username = null;
        String token = null;

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);
            try {
                username = jwtUtils.getUsername(token);
            } catch (ExpiredJwtException e) {
                log.debug("Token lifetime is expired");
            } catch (SignatureException e) {
                log.debug("Invalid signature");
            }
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            var usernamePasswordAuthToken = new UsernamePasswordAuthenticationToken(
                    username,
                    null,
                    jwtUtils.getRoles(token).stream().map(SimpleGrantedAuthority::new).toList()
            );
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthToken);
        }
        filterChain.doFilter(request, response);
    }
}
