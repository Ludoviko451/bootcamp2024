package com.bootcamp2024.bootcamp2024.domain.model;

public class Role {

    public Long getIdRole() {
        return idRole;
    }

    public String getName() {
        return name;
    }

    public Role(Long idRole, String name) {
        this.idRole = idRole;
        this.name = name;
    }

    private final Long idRole;
    private final String name;
}
