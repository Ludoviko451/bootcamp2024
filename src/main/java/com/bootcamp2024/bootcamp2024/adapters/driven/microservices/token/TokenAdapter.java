package com.bootcamp2024.bootcamp2024.adapters.driven.microservices.token;

import com.bootcamp2024.bootcamp2024.adapters.driven.microservices.feignconfiguration.SecurityFeignRequestInterceptor;
import com.bootcamp2024.bootcamp2024.configuration.security.SecurityContants;
import com.bootcamp2024.bootcamp2024.configuration.security.jwt.JwtTokenProvider;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class TokenAdapter implements IToken{
    @Override
    public String getBearerToken() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes != null) {
            return attributes.getRequest().getHeader(SecurityContants.AUTHORIZATION_HEADER);
        } else {
            return null; // O manejar el caso cuando no se puede obtener el encabezado de autorización
        }
    }

    @Override
    public String getEmail(String token) {

        return JwtTokenProvider.findUsername(token);
    }
}
