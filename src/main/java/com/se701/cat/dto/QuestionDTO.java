package com.se701.cat.dto;

import com.se701.cat.entity.Question;
import com.se701.cat.entity.QuestionType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class QuestionDTO{
    private String id;
    private double difficultyParameter;
    private QuestionType type;
    private int moduleNumber;
    private String content;
    private Map<String, String> options;
    private String correctAnswer;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getDifficultyParameter() {
        return difficultyParameter;
    }

    public void setDifficultyParameter(double difficultyParameter) {
        this.difficultyParameter = difficultyParameter;
    }

    public QuestionType getType() {
        return type;
    }

    public void setType(QuestionType type) {
        this.type = type;
    }

    public int getModuleNumber() {
        return moduleNumber;
    }

    public void setModuleNumber(int moduleNumber) {
        this.moduleNumber = moduleNumber;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Map<String, String> getOptions() {
        return options;
    }

    public void setOptions(Map<String, String> options) {
        this.options = options;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public Question toDomainObject() {
        List<Question.Option> optionList = options
                .entrySet()
                .stream()
                .map(e -> new Question.Option(e.getKey(), e.getValue()))
                .collect(Collectors.toList());
        return new Question(id, difficultyParameter, type, content, moduleNumber, optionList, correctAnswer);
    }

    @Override
    public String toString() {
        return "QuestionDTO{" +
                "id='" + id + '\'' +
                ", difficultyParameter=" + difficultyParameter +
                ", type='" + type + '\'' +
                ", moduleNumber=" + moduleNumber +
                ", content='" + content + '\'' +
                ", options=" + options +
                '}';
    }
}
