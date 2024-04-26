package com.bootcamp2024.bootcamp2024.adapters.driven.jpa.mysql.adapter;

import com.bootcamp2024.bootcamp2024.adapters.driven.jpa.mysql.entity.PrincipalUser;
import com.bootcamp2024.bootcamp2024.adapters.driven.microservices.client.IUserFeignClient;
import com.bootcamp2024.bootcamp2024.adapters.driven.microservices.dto.UserFeignDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {


    private final IUserFeignClient userFeignClient;

    @Autowired
    public UserDetailsServiceImpl(IUserFeignClient userFeignClient) {
        this.userFeignClient = userFeignClient;
    }

    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserFeignDto userFeignDto = userFeignClient.getUserByMail(email);

        return new PrincipalUser(userFeignDto);
    }

}