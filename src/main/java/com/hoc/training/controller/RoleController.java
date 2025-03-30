package com.hoc.training.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hoc.training.dummy.RoleDummy;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

    @GetMapping("/{id}")
    public String getRoleById(@PathVariable Long id) {
        String role = new RoleDummy().getRoleById(id);
        if (role.isBlank()) {
            return "Data not found";
        }

        return role;
    }
}
