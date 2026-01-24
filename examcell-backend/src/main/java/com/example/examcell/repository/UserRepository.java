package com.example.examcell.repository;

import com.example.examcell.dto.UserDTO;
import com.example.examcell.model.User;

import java.util.ArrayList;

public interface UserRepository {
    public ArrayList<UserDTO> getAllUsers();
    public UserDTO getUserByUsername(String username);
    public UserDTO addUser(User user);
    public UserDTO updateUser(String username, User user);
    public void deleteUser(String username);
}
