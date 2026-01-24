package com.example.examcell.controller;

import com.example.examcell.dto.UserDTO;
import com.example.examcell.model.User;
import com.example.examcell.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

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

    @GetMapping("/{username}")
    public UserDTO getUserByUsername(@PathVariable("username") String username) {
        return userService.getUserByUsername(username);
    }

    @PostMapping("")
    public UserDTO addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @PutMapping("/{username}")
    public UserDTO updateUser(String username, User user) {
        return userService.updateUser(username, user);
    }

    @DeleteMapping("/{username}")
    public void deleteUser(@PathVariable("username") String username) {
        userService.deleteUser(username);
    }
}
