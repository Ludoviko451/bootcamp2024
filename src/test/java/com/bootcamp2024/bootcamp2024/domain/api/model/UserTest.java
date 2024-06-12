package com.bootcamp2024.bootcamp2024.domain.api.model;


import com.bootcamp2024.bootcamp2024.domain.model.Role;
import com.bootcamp2024.bootcamp2024.domain.model.User;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    @Test
    public void testUserConstructorAndGetters() {
        Long id = 1L;
        String name = "John";
        String lastName = "Doe";
        String dni = "12345678";
        String phoneNumber = "1234567890";
        String email = "john.doe@example.com";

        User user = new User(id, name, lastName, dni, phoneNumber, email);

        assertEquals(id, user.getId());
        assertEquals(name, user.getName());
        assertEquals(lastName, user.getLastName());
        assertEquals(dni, user.getDni());
        assertEquals(phoneNumber, user.getPhoneNumber());
        assertEquals(email, user.getEmail());
    }

    @Test
    public void testSetPassword() {
        User user = new User(1L, "John", "Doe", "12345678", "1234567890", "john.doe@example.com");
        String password = "password123";

        user.setPassword(password);

        assertEquals(password, user.getPassword());
    }

    @Test
    public void testSetRole() {
        User user = new User(1L, "John", "Doe", "12345678", "1234567890", "john.doe@example.com");
        Role role1 = Mockito.mock(Role.class);
        Role role2 = Mockito.mock(Role.class);
        List<Role> roles = List.of(role1, role2);

        user.setRole(roles);

        assertEquals(roles, user.getRole());
    }

    @Test
    public void testUserWithoutRolesAndPassword() {
        User user = new User(1L, "John", "Doe", "12345678", "1234567890", "john.doe@example.com");

        assertNull(user.getPassword());
        assertNull(user.getRole());
    }
}
