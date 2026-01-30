package com.example.examcell.repository.jparepos;

import com.example.examcell.model.ModuleInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModuleJpaRepository extends JpaRepository<ModuleInfo, Integer> {
}
