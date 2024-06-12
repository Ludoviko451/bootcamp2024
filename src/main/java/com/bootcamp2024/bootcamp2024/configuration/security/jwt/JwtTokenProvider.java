package com.bootcamp2024.bootcamp2024.configuration.security.jwt;


import com.bootcamp2024.bootcamp2024.adapters.driven.jpa.mysql.exception.InvalidJwtClaimFormat;
import com.bootcamp2024.bootcamp2024.configuration.security.SecurityContants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.stereotype.Component;


import java.util.List;

@Component
public class JwtTokenProvider {


    public List<String> getRolesFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(SecurityContants.JWT_SECRET)
                .parseClaimsJws(token)
                .getBody();

        Object authorities = claims.get("authorities");
        if (authorities instanceof List) {
            @SuppressWarnings("unchecked")
            List<String> roles = (List<String>) authorities;
            return roles;
        } else {
            throw new InvalidJwtClaimFormat();
        }
    }

    public Boolean validateToken(String token) {
        try {

            Jwts.parser().setSigningKey(SecurityContants.JWT_SECRET).parseClaimsJws(token);

            return true;
        } catch (Exception e) {

            throw new AuthenticationCredentialsNotFoundException("Jwt ha expirado o es inválido");
        }
    }
}
