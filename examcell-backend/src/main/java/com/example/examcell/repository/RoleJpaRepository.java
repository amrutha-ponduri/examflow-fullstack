package com.example.examcell.repository;

import com.example.examcell.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleJpaRepository extends JpaRepository<Role, Integer> {

}
