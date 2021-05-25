package com.autenticate.security;

import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class JWTTokenFilter extends OncePerRequestFilter {

    private final UserDetailsService userDetailsService;
    private final JWTUtil jwtUtil;
    private final String tokenHeader;

    public JWTTokenFilter(UserDetailsService userDetailsService, JWTUtil jwtUtil, String tokenHeader) {
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
        this.tokenHeader = tokenHeader;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        authorizeRequest(request);
        chain.doFilter(request, response);
    }

    private void authorizeRequest(HttpServletRequest request) {
        log.debug("Processing authentication for '{}'", request.getRequestURL());

        final String requestHeader = request.getHeader(this.tokenHeader);
        if (requestHeader == null || !requestHeader.startsWith("Bearer ")) {
            log.warn("Authorization failed. No JWT token found");
            return;
        }

        String username;
        String authToken = requestHeader.substring(7);

        try {
            username = jwtUtil.getUsernameFromToken(authToken);
        } catch (IllegalArgumentException e) {
            log.error("Error during getting username from token", e);
            return;
        } catch (ExpiredJwtException e) {
            log.warn("The token has expired", e);
            return;
        }

        if (username == null || SecurityContextHolder.getContext().getAuthentication() != null) return;
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
        if (!jwtUtil.validateToken(authToken, userDetails)) {
            log.error("Not a valid token!!!");
            return;
        }

        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        log.info("Authorized user '{}', setting security context...", username);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}