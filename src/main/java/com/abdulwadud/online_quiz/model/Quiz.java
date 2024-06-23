package com.abdulwadud.online_quiz.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Data
public class Quiz {
    // Setter method
    // Getter method
    @Setter
    @Getter
    private String examType;

    // Setter method for numQ
    // Getter method for numQ
    @Setter
    @Getter
    @Column(name = "numq")
    private Integer numQ;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    @ManyToMany
    @JoinTable(
            name = "quiz_questions",
            joinColumns = @JoinColumn(name = "quiz_id"),
            inverseJoinColumns = @JoinColumn(name = "question_id")
    )
    private List<ExamQuestions> questions;
}
