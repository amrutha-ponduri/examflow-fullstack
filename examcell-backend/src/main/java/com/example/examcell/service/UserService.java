package com.example.examcell.service;

import com.example.examcell.config.Mapper;
import com.example.examcell.dto.UserDTO;
import com.example.examcell.dto.UserDropdownDTO;
import com.example.examcell.model.User;
import com.example.examcell.repository.UserJpaRepository;
import com.example.examcell.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Service
public class UserService implements UserRepository {

    private final UserJpaRepository userJpaRepository;
    private final Mapper mapper;

    @Autowired
    public UserService(UserJpaRepository userJpaRepository, Mapper mapper) {
        this.userJpaRepository = userJpaRepository;
        this.mapper = mapper;
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
        return mapper.toUserDTO(userJpaRepository.save(user));
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
            if (user.getRoles() != null) {
                savedUser.setRoles(user.getRoles());
            }
            if (user.getUsername() != null) {
                savedUser.setUsername(user.getUsername());
            }
            return mapper.toUserDTO(userJpaRepository.save(savedUser));
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
