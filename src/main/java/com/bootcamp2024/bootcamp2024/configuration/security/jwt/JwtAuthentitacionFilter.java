package com.bootcamp2024.bootcamp2024.configuration.security.jwt;

import com.bootcamp2024.bootcamp2024.adapters.driven.jpa.mysql.adapter.UserDetailsServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;


public class JwtAuthentitacionFilter extends OncePerRequestFilter {

    private final UserDetailsServiceImpl userDetailsService;


    private final JwtTokenProvider jwtProvider;

    @Autowired
    public JwtAuthentitacionFilter(UserDetailsServiceImpl userDetailsService, JwtTokenProvider jwtProvider) {
        this.userDetailsService = userDetailsService;
        this.jwtProvider = jwtProvider;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain filterChain)
            throws ServletException, IOException {

        String tokenWithoutBearer = getToken(req);
        if (tokenWithoutBearer != null && jwtProvider.validateToken(tokenWithoutBearer)) {
            String nameUser = JwtTokenProvider.getUserNameFromToken(tokenWithoutBearer);


            UserDetails userDetails = userDetailsService.loadUserByUsername(nameUser);
            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userDetails, null,
                    userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(auth);
        }
        filterChain.doFilter(req, res);
    }



    private String getToken(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")) {
            return header.substring(7); // return everything after "Bearer "
        }
        return null;
    }

}
