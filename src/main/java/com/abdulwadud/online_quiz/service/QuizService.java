package com.abdulwadud.online_quiz.service;

import com.abdulwadud.online_quiz.model.ExamQuestions;
import com.abdulwadud.online_quiz.dao.QuestionDao;
import com.abdulwadud.online_quiz.dao.QuizDao;
import com.abdulwadud.online_quiz.model.QuestionWrapper;
import com.abdulwadud.online_quiz.model.Quiz;
import com.abdulwadud.online_quiz.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {
    @Autowired
    QuizDao quizDao;
    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<String> createQuiz(String examType, int numQ, String title) {

        List<ExamQuestions> questions = questionDao.findRandomQuestionByExamType(examType, numQ);

        Quiz quiz = new Quiz();
        quiz.setExamType(examType);
        quiz.setNumQ(numQ);
        quiz.setTitle(title);
        quiz.setQuestions(questions);

        quizDao.save(quiz);
        return ResponseEntity.ok("Quiz created");

    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(int id) {
        Optional<Quiz> quiz = quizDao.findById(id);
        List<ExamQuestions> questionsFromDB = quiz.get().getQuestions();
        List<QuestionWrapper> questionForUsers = new ArrayList<>();
        for (ExamQuestions q : questionsFromDB) {
            QuestionWrapper qw = new QuestionWrapper(q.getId(), q.getQuestionText(), q.getOption1(), q.getOption2(), q.getOption3());
            questionForUsers.add(qw);
        }
        return ResponseEntity.ok(questionForUsers);
    }

    public ResponseEntity<Integer> calculateResult(int id, List<Response> responses) {
        Quiz quiz = quizDao.findById(id).orElseThrow(() -> new RuntimeException("Quiz not found"));
        List<ExamQuestions> questionsFromDB = quiz.getQuestions();
        int right = 0;

        // Iterate over responses safely
        for (int i = 0; i < responses.size() && i < questionsFromDB.size(); i++) {
            Response response = responses.get(i);
            ExamQuestions question = questionsFromDB.get(i);

            // Example check: Ensure response and question properties are not null before comparison
            if (response != null && question != null && response.getResponse() != null && question.getRightAnswer() != null) {
                if (response.getResponse().equals(question.getRightAnswer())) {
                    right++;
                }
            }
        }

        return ResponseEntity.ok(right);
    }


    public List<Quiz> getAllQuizzes() {
        return quizDao.findAll();
    }

    public Quiz getQuizById(int id) {
        return quizDao.findById(id).orElseThrow(() -> new RuntimeException("Quiz not found"));
    }
}
