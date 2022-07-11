package com.se701.cat.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document("questions")
public class Question {
    public static final int FIXED_LENGTH_MODULE_NUMBER = -1;
    @Id
    private String id;
    private double difficultyParameter;
    private QuestionType type;
    private String content;
    /**
     * MST module number this question belongs to
     * If the question does not belong to MST module, moduleNumber is -1
     */
    private int moduleNumber;
    private List<Option> questionOptions;

    private String correctAnswer;

    public Question(String id, double difficultyParameter, QuestionType type, String content, int moduleNumber, List<Option> questionOptions, String correctAnswer) {
        this.id = id;
        this.difficultyParameter = difficultyParameter;
        this.type = type;
        this.content = content;
        this.moduleNumber = moduleNumber;
        this.questionOptions = questionOptions;
        this.correctAnswer = correctAnswer;
    }

    public int getModuleNumber() {
        return moduleNumber;
    }

    public List<Option> getQuestionOptions() {
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

    public String getContent() {
        return content;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public static class Option{
        private String optionId;
        private String optionString;

        public Option(String optionId, String optionString) {
            this.optionId = optionId;
            this.optionString = optionString;
        }

        public String getOptionId() {
            return optionId;
        }

        public String getOptionString() {
            return optionString;
        }
    }

}
