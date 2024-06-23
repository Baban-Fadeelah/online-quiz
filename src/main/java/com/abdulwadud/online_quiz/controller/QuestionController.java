package com.abdulwadud.online_quiz.controller;

import com.abdulwadud.online_quiz.model.ExamQuestions;
import com.abdulwadud.online_quiz.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @GetMapping("allQuestions")

    public List<ExamQuestions> getAllQuestions() {
        return questionService.getAllQuestions();

    }

    @GetMapping("examType/{examType}")
    public List<ExamQuestions> getQuestionsByExamType(@PathVariable String examType) {
        return questionService.getQuestionsByExamType(examType);
    }

    @PostMapping("add")
    public String addQuestions( @RequestBody ExamQuestions examQuestions) {
        return questionService.addQuestions(examQuestions);
    }
}
