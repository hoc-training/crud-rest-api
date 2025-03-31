package com.hoc.training.dummy;

import java.util.ArrayList;
import java.util.List;

import com.hoc.training.entity.User;

public class UserDummy {

    private List<User> users = new ArrayList<>();
    private User user = new User();

    public User getUserById(Long id) {
        user = new User();
        user.setEmail("user@mail.com");
        user.setId(1L);
        user.setName("User Name");

        return user;
    }

    public List<User> getUsers() {
        for (int i = 1; i < 10; i++) {
            user = new User();
            user.setEmail("mail@mail.com1");
            user.setId(Long.valueOf(i));
            user.setName("User " + i);
            users.add(user);
        }

        return users;
    }

    public void addUserDummy(User user) {
        users.add(user);
    }
}
