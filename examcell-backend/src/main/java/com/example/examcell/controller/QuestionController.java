package com.example.examcell.controller;


import com.example.examcell.dto.questiondtos.QuestionDTO;
import com.example.examcell.model.Question;
import com.example.examcell.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/questions")
@CrossOrigin(origins = "http://localhost:3000")
public class QuestionController {

    private final QuestionService questionService;

    @Autowired
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @PostMapping("")
    public QuestionDTO addQuestion(@RequestBody Question question) {
        return questionService.addQuestion(question);
    }

    @PutMapping("/{id}")
    public QuestionDTO updateQuestion(@PathVariable("id") int id, @RequestBody Question question) {
        return questionService.updateQuestion(id, question);
    }

    @DeleteMapping("/{id}")
    public void deleteQuestion(@PathVariable("id") int id) {
        questionService.deleteQuestion(id);
    }
}
