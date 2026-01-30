package com.example.examcell.controller;

import com.example.examcell.dto.dropdowndtos.UserDropdownDTO;
import com.example.examcell.dto.userdtos.UserDTO;
import com.example.examcell.model.User;
import com.example.examcell.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

// https://localhost:8080/users => get
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService courseService) {
        this.userService = courseService;
    }

    @GetMapping("")
    public ArrayList<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public UserDTO getUserByUsername(@PathVariable("id") int id) {
        return userService.getUserById(id);
    }

    @PostMapping("")
    public UserDTO addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @PutMapping("/{id}")
    public UserDTO updateUser(@PathVariable("id") int userId, User user) {
        return userService.updateUser(userId, user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") int id) {
        userService.deleteUser(id);
    }

    @GetMapping("/dropdown")
    public ArrayList<UserDropdownDTO> getAllUserDropdownItems() {
        return userService.getAllUserDropdownItems();
    }
}


