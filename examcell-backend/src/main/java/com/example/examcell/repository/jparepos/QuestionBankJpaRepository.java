package com.example.examcell.repository.jparepos;

import com.example.examcell.model.QuestionBank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionBankJpaRepository extends JpaRepository<QuestionBank, Integer> {

}
