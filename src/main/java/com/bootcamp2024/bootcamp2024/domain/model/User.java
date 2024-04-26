package com.bootcamp2024.bootcamp2024.domain.model;

import java.util.List;

public class User {


    private final Long id;
    private final String name;

    private final String lastName;

    private final String dni;

    private final String phoneNumber;

    private final String email;


    private String password;
    private List<Role> role;

    public User(Long id, String name, String lastName, String dni, String phoneNumber, String email) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.dni = dni;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getDni() {
        return dni;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public List<Role> getRole() {
        return role;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(List<Role> role) {
        this.role = role;
    }

}
