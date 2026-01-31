package com.example.examcell.repository.jparepos;

import com.example.examcell.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionJpaRepository extends JpaRepository<Question, Integer> {

}
