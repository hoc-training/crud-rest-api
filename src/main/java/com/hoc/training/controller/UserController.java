package com.hoc.training.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hoc.training.dummy.UserDummy;
import com.hoc.training.model.User;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private List<User> users = new ArrayList<>();
    private UserDummy dummy = new UserDummy();

    @GetMapping("/{id}")
    public ResponseEntity<User> getProductById(@PathVariable Long id) {
        User user = new UserDummy().getUserById(id);
        if (user.getId() == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @GetMapping("/get")
    public ResponseEntity<List<User>> getUsers() {
        List<User> users = new UserDummy().getUsers();

        if (users.size() == 0) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }

    @GetMapping
    public List<User> getAllUsers() {
        return new UserDummy().getUsers();
    }

    /**
     * get data from private list
     * 
     * @param user
     * @return
     */
    @PostMapping
    public ResponseEntity<List<User>> addUser(@RequestBody User user) {
        users.add(user);

        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<UserDummy> addDummy(@RequestBody User user) {
        dummy.addUserDummy(user);

        return new ResponseEntity<UserDummy>(dummy, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<List<User>> editDummy(@PathVariable Long id, @RequestBody User user) {
        List<User> users = new UserDummy().getUsers();
        for (User tmp : users) {
            if (tmp.getId() == id) {
                users.remove(tmp);
                tmp.setEmail(user.getEmail());
                tmp.setName(user.getName());
                users.add(tmp);
                break;
            }
        }

        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<List<User>> deleteDummy(@PathVariable Long id) {
        User dataRemoved = null;
        List<User> tmp = new UserDummy().getUsers();
        for (User user : tmp) {
            if (user.getId() == id) {
                dataRemoved = user;
                break;
            }
        }

        if (dataRemoved == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        tmp.remove(dataRemoved);

        return new ResponseEntity<List<User>>(tmp, HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public String getUserById(@PathVariable Long id) {
        User user = new UserDummy().getUserById(id);
        if (user.equals(null)) {
            return "Data not found";
        }

        return user.toString();
    }
}
