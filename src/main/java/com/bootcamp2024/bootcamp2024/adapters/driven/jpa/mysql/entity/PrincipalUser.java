package com.bootcamp2024.bootcamp2024.adapters.driven.jpa.mysql.entity;

import com.bootcamp2024.bootcamp2024.adapters.driven.microservices.dto.UserFeignDto;
import com.bootcamp2024.bootcamp2024.domain.model.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class PrincipalUser implements UserDetails {

    UserFeignDto userFeignDto;



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<Role> roles= userFeignDto.getRole();


        return roles
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return userFeignDto.getPassword();
    }

    @Override
    public String getUsername() {
        return userFeignDto.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
