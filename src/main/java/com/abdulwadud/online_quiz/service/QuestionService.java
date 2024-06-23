package com.abdulwadud.online_quiz.service;

import com.abdulwadud.online_quiz.dao.QuestionDao;
import com.abdulwadud.online_quiz.model.ExamQuestions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    @Autowired
    QuestionDao questionDao;
    public List<ExamQuestions> getAllQuestions() {
        return questionDao.findAll();
    }

    public List<ExamQuestions> getQuestionsByExamType(String examType) {
        return questionDao.findByExamType(examType);

    }
    public String addQuestions(ExamQuestions examQuestions) {
        questionDao.save(examQuestions);
        return "Question added successfully";
    }
}
