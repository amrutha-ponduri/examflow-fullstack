package com.example.examcell.repository.jparepos;

import com.example.examcell.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuestionJpaRepository extends JpaRepository<Question, Integer> {

    @Query("""
            SELECT sq.content
            FROM Question q JOIN q.subquestions sq
            WHERE q.moduleInfo.id = :moduleId""")
    List<String> findByModuleId(Integer moduleId);
}
