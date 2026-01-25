package com.example.examcell.repository;

import com.example.examcell.model.Regulation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegulationJpaRepository extends JpaRepository<Regulation, String> {

}
