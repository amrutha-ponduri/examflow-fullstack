package com.example.examcell.repository;

import com.example.examcell.model.Program;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProgramJpaRepository extends JpaRepository<Program, Integer> {

}
