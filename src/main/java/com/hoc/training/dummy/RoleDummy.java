package com.hoc.training.dummy;

import com.hoc.training.entity.Role;

public class RoleDummy {

    public String getRoleById(Long id) {
        Role role = new Role();
        role.setCode("ROLE_ADMIN");
        role.setId(1L);
        role.setName("Admin");

        return role.toString();
    }
}
