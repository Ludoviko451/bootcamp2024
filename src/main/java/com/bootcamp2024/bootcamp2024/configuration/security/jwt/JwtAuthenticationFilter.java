package com.bootcamp2024.bootcamp2024.configuration.security.jwt;

import com.bootcamp2024.bootcamp2024.adapters.driven.jpa.mysql.adapter.UserDetailsServiceImpl;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.util.Collections;


public class JwtAuthentitacionFilter extends OncePerRequestFilter {


    private final JwtTokenProvider jwtProvider;

    @Autowired
    public JwtAuthentitacionFilter(JwtTokenProvider jwtProvider) {
        this.jwtProvider = jwtProvider;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain filterChain)
            throws ServletException, IOException {

        String tokenWithoutBearer = getToken(req);
        try {
            if (tokenWithoutBearer != null && jwtProvider.validateToken(tokenWithoutBearer)) {
                String role = jwtProvider.getRoleFromToken(tokenWithoutBearer);

                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(null, null, Collections.singletonList(new SimpleGrantedAuthority(role)));


                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }
        catch (JwtException e){
            req.getSession().setAttribute("error", e.getMessage());
        }
        filterChain.doFilter(req, res);
    }



    private String getToken(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")) {
            return header.substring(7);
        }
        return null;
    }

}
