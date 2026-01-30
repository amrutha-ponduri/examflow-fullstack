package com.example.examcell.service;

import com.example.examcell.model.Role;
import com.example.examcell.repository.contractrepos.RoleRepository;
import com.example.examcell.repository.jparepos.RoleJpaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class RoleService implements RoleRepository {
    private final RoleJpaRepository roleJpaRepository;

    public RoleService(RoleJpaRepository roleJpaRepository) {
        this.roleJpaRepository = roleJpaRepository;
    }

    @Override
    public ArrayList<Role> getRoleDropdown() {
        return new ArrayList<>(roleJpaRepository.findAll());
    }
}
