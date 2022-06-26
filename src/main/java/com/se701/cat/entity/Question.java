package com.se701.cat.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Question {
    private String questionId;
    private double difficultyParameter;
    private String questionType;
    private String questionContent;

    private List<Option> questionOptions = new ArrayList<>();


    public List<Option> getQuestionOptions() {
        return questionOptions;
    }

    public String getQuestionId() {
        return questionId;
    }

    public double getDifficultyParameter() {
        return difficultyParameter;
    }

    public String getQuestionType() {
        return questionType;
    }

    public String getQuestionContent() {
        return questionContent;
    }

    public void addOption(String optionId, String optionString){
        questionOptions.add(new Option(optionId, optionString));
    }

    public Question(String questionId, double difficultyParameter, String questionType, String questionContent) {
        this.questionId = questionId;
        this.difficultyParameter = difficultyParameter;
        this.questionType = questionType;
        this.questionContent = questionContent;
    }
}


class Option{
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