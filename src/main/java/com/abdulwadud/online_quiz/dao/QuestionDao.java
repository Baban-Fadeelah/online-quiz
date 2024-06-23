package com.abdulwadud.online_quiz.dao;

import com.abdulwadud.online_quiz.model.ExamQuestions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface QuestionDao extends JpaRepository<ExamQuestions, Integer> {

    List<ExamQuestions> findByExamType(String examType);

    @Query(value = "SELECT * FROM exam_questions eQ WHERE eQ.exam_type = ?1 ORDER BY RAND() LIMIT ?2", nativeQuery = true)
    List<ExamQuestions> findRandomQuestionByExamType(String examType, int limit);

}
