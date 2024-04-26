package com.bootcamp2024.bootcamp2024.adapters.driven.microservices.feignconfiguration;

import com.bootcamp2024.bootcamp2024.configuration.security.SecurityContants;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Component
public class SecurityFeignRequestInterceptor implements RequestInterceptor {


    @Override
    public void apply(RequestTemplate template) {
        template.header(SecurityContants.AUTHORIZATION_HEADER, getBearerTokenHeader());
    }

    public static String getBearerTokenHeader() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes != null) {
            return attributes.getRequest().getHeader(SecurityContants.AUTHORIZATION_HEADER);
        } else {
            return null; // O manejar el caso cuando no se puede obtener el encabezado de autorización
        }
    }

}