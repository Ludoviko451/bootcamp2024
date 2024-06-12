package com.bootcamp2024.bootcamp2024.domain.api.model;

import com.bootcamp2024.bootcamp2024.domain.model.Role;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RoleTest {

    @Test
    public void testRoleConstructorAndGetters() {
        Long idRole = 1L;
        String name = "ADMIN";

        Role role = new Role(idRole, name);

        assertEquals(idRole, role.getIdRole());
        assertEquals(name, role.getName());
    }
}
