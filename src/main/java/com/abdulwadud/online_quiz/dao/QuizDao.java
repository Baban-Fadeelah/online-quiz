package com.abdulwadud.online_quiz.dao;

import com.abdulwadud.online_quiz.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizDao extends JpaRepository<Quiz, Integer> {


}
