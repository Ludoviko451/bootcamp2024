package com.bootcamp2024.bootcamp2024.adapters.driven.microservices.dto;

import com.bootcamp2024.bootcamp2024.domain.model.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class UserFeignDto {

    private Long id;
    private String name;

    private String lastName;

    private String dni;

    private String phoneNumber;

    private String email;

    private String password;

    private List<Role> role;
}
