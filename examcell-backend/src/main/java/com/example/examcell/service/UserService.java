package com.example.examcell.service;

import com.example.examcell.config.Mapper;
import com.example.examcell.dto.dropdowndtos.UserDropdownDTO;
import com.example.examcell.dto.userdtos.UserDTO;
import com.example.examcell.model.Role;
import com.example.examcell.model.User;
import com.example.examcell.repository.contractrepos.UserRepository;
import com.example.examcell.repository.jparepos.RoleJpaRepository;
import com.example.examcell.repository.jparepos.UserJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements UserRepository {

    private final UserJpaRepository userJpaRepository;
    private final Mapper mapper;
    private final RoleJpaRepository roleJpaRepository;

    @Autowired
    public UserService(UserJpaRepository userJpaRepository, Mapper mapper, RoleJpaRepository roleJpaRepository) {
        this.userJpaRepository = userJpaRepository;
        this.mapper = mapper;
        this.roleJpaRepository = roleJpaRepository;
    }

    @Override
    public ArrayList<UserDTO> getAllUsers() {
        ArrayList<User> users = new ArrayList<>(userJpaRepository.findAll());
        return new ArrayList<>(users.stream().map(mapper::toUserDTO).collect(Collectors.toList()));
    }

    @Override
    public UserDTO getUserById(int id) {
        try {
            return mapper.toUserDTO(userJpaRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid arguments");
        }
    }

    @Override
    public UserDTO addUser(User user) {
        User savedUser = userJpaRepository.save(user);
        if (user.getRoles() != null && !user.getRoles().isEmpty()) {
            List<Integer> roleIds = savedUser.getRoles().stream().map(Role::getId).collect(Collectors.toList());
            List<Role> roles = roleJpaRepository.findAllById(roleIds);
            savedUser.setRoles(roles);
        }
        return mapper.toUserDTO(savedUser);
    }

    @Override
    public UserDTO updateUser(int id, User user) {
        try {
            User savedUser = userJpaRepository.findById(id).orElseThrow(() -> new ResponseStatusException((HttpStatus.NOT_FOUND)));
            if (user.getName() != null) {
                savedUser.setName(user.getName());
            }
            if (user.getPassword() != null) {
                savedUser.setPassword(user.getPassword());
            }
            if (user.getRoles() != null && !user.getRoles().isEmpty()) {
                List<Integer> roleIds = user.getRoles().stream().map(Role::getId).collect(Collectors.toList());
                List<Role> roles = roleJpaRepository.findAllById(roleIds);
                savedUser.setRoles(roles);
            }
            if (user.getUsername() != null) {
                savedUser.setUsername(user.getUsername());
            }
            userJpaRepository.save(savedUser);
            return getUserById(savedUser.getId());
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid arguments");
        }
    }

    @Override
    public void deleteUser(int id) {
        try {
            UserDTO userDTO = getUserById(id);
            userJpaRepository.deleteById(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }
    }

    @Override
    public ArrayList<UserDropdownDTO> getAllUserDropdownItems() {
        return new ArrayList<>(userJpaRepository.findAllUserDropdownItems());
    }
}
