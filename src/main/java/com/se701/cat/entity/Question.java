package com.se701.cat.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document("questions")
public class Question {
    @Id
    private String id;
    private double difficultyParameter;
    private String type;
    private String content;
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

    public String getId() {
        return id;
    }

    public double getDifficultyParameter() {
        return difficultyParameter;
    }

    public String getType() {
        return type;
    }

    public String getContent() {
        return content;
    }


    public Question(String id, double difficultyParameter, String type, String content, Integer moduleNumber, List<Option> questionOptions) {
        this.id = id;
        this.difficultyParameter = difficultyParameter;
        this.type = type;
        this.content = content;
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
