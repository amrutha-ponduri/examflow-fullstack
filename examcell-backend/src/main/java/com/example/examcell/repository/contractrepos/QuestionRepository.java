package com.example.examcell.repository.contractrepos;

import com.example.examcell.dto.questiondtos.QuestionDTO;
import com.example.examcell.model.Question;

public interface QuestionRepository {

    public QuestionDTO addQuestion(Question question);

    public QuestionDTO updateQuestion(int id, Question question);

    public void deleteQuestion(int id);
}
