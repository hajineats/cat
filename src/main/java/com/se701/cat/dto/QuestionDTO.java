package com.se701.cat.dto;

import com.se701.cat.entity.Question;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class QuestionDTO{
    String id;
    double difficultyParameter;
    String type;
    int moduleNumber;
    String content;
    Map<String, String> options;

    public QuestionDTO(String id, double difficultyParameter, String type, int moduleNumber, String content, Map<String, String> options) {
        this.id = id;
        this.difficultyParameter = difficultyParameter;
        this.type = type;
        this.moduleNumber = moduleNumber;
        this.content = content;
        this.options = options;
    }

    public Question toDomainObject() {
        List<Question.Option> optionList = options.entrySet().stream().map(e -> new Question.Option(e.getKey(), e.getValue())).collect(Collectors.toList());
        return new Question(id, difficultyParameter, type, content, moduleNumber, optionList);
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
