package com.se701.cat.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Document("questions")
public class Question {
    @Id
    private String questionId;
    private double difficultyParameter;
    private String questionType;
    private String questionContent;
    /**
     * MST module number this question belongs to
     * If the question does not belong to MST module, moduleNumber is null
     */
    private Integer moduleNumber;
    private List<Option> questionOptions = new ArrayList<>();


    public Integer getModuleNumber() {
        return moduleNumber;
    }

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


    public Question(String questionId, double difficultyParameter, String questionType, String questionContent, Integer moduleNumber, List<Option> questionOptions) {
        this.questionId = questionId;
        this.difficultyParameter = difficultyParameter;
        this.questionType = questionType;
        this.questionContent = questionContent;
        this.moduleNumber = moduleNumber;
        this.questionOptions = questionOptions;
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
