package com.bootcamp2024.bootcamp2024.configuration.security.jwt;


import com.bootcamp2024.bootcamp2024.configuration.security.SecurityContants;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenProvider {





    public static String getUserNameFromToken(String token) {
        return Jwts.parser().setSigningKey("secret").parseClaimsJws(token).getBody().getSubject();
    }

    public static String findUsername(String token) {
        // Parsear el token y extraer el nombre de usuario
        return Jwts.parser()
                .setSigningKey(SecurityContants.JWT_SECRET) // Establecer la clave secreta para verificar la firma del token
                .parseClaimsJws(token) // Parsear el token
                .getBody() // Obtener el cuerpo del token
                .getSubject(); // Obtener el nombre de usuario del cuerpo del token
    }


    public Boolean validateToken(String token) {
        try {
            // Parsear y verificar la firma del token
            Jwts.parser().setSigningKey(SecurityContants.JWT_SECRET).parseClaimsJws(token);
            // Si no hay excepciones, el token es válido
            return true;
        } catch (Exception e) {
            // Si se produce una excepción, el token es inválido
            throw new AuthenticationCredentialsNotFoundException("Jwt ha expirado o es inválido");
        }
    }

}