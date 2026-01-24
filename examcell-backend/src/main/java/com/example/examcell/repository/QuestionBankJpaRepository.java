package com.example.examcell.repository;

import com.example.examcell.model.QuestionBank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionBankJpaRepository extends JpaRepository<QuestionBank, Integer> {

}
