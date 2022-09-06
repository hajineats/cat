package com.p4p.cat.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document("questions")
public class Question {
    public static final int FIXED_LENGTH_MODULE_NUMBER = -1;
    @Id
    private String id;
    private double difficultyParameter;
    private QuestionType type;

    private String imageContent;

    private String textContent;


    /**
     * MST module number this question belongs to
     * If the question does not belong to MST module, moduleNumber is -1
     */
    private int moduleNumber;
    private List<QuestionOption> questionOptions;

    private String correctAnswer;

    public Question(String id, double difficultyParameter, QuestionType type, String imageContent, String textContent, int moduleNumber, List<QuestionOption> questionOptions, String correctAnswer) {
        this.id = id;
        this.difficultyParameter = difficultyParameter;
        this.type = type;
        this.imageContent = imageContent;
        this.textContent = textContent;
        this.moduleNumber = moduleNumber;
        this.questionOptions = questionOptions;
        this.correctAnswer = correctAnswer;
    }

    public int getModuleNumber() {
        return moduleNumber;
    }

    public List<QuestionOption> getQuestionOptions() {
        return questionOptions;
    }

    public String getId() {
        return id;
    }

    public double getDifficultyParameter() {
        return difficultyParameter;
    }

    public QuestionType getType() {
        return type;
    }

    public String getImageContent() {
        return imageContent;
    }

    public String getTextContent() {
        return textContent;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }
}
