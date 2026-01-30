package com.example.examcell.repository.contractrepos;

import com.example.examcell.dto.dropdowndtos.UserDropdownDTO;
import com.example.examcell.dto.userdtos.UserDTO;
import com.example.examcell.model.User;

import java.util.ArrayList;

public interface UserRepository {
    public ArrayList<UserDTO> getAllUsers();

    public UserDTO getUserById(int id);

    public UserDTO addUser(User user);

    public UserDTO updateUser(int id, User user);

    public void deleteUser(int id);

    ArrayList<UserDropdownDTO> getAllUserDropdownItems();
}
