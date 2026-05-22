package com.example.examcell.repository.contractrepos;

import com.example.examcell.dto.questionbankconfigdtos.ConfigurationRequestDTO;
import com.example.examcell.dto.questionbankdtos.QuestionBankCompleteDetailsDTO;
import com.example.examcell.dto.questionbankdtos.QuestionBankDTO;
import com.example.examcell.dto.questiondtos.QuestionDTO;
import com.example.examcell.model.QuestionBank;

import java.util.ArrayList;

public interface QuestionBankRepository {
    public ArrayList<QuestionBankDTO> getAllQuestionBanks();

    public QuestionBankCompleteDetailsDTO getQuestionBankById(int id);

    QuestionBankDTO addQuestionBank(ConfigurationRequestDTO configurationRequestDTO);

    public QuestionBankCompleteDetailsDTO updateQuestionBank(int id, QuestionBank questionBank);

    public void deleteQuestionBank(int id);

    public ArrayList<QuestionDTO> getAllQuestionsForQuestionBank(int id);
}
