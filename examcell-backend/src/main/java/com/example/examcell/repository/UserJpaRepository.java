package com.example.examcell.repository;

import com.example.examcell.dto.UserDropdownDTO;
import com.example.examcell.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserJpaRepository extends JpaRepository<User, Integer> {
    @Query("""
            SELECT new com.example.examcell.dto.UserDropdownDTO(u.id, u.username, u.name)
            FROM User u""")
    List<UserDropdownDTO> findAllUserDropdownItems();
}
