package com.se701.cat.dto;

import com.se701.cat.entity.Question;
import com.se701.cat.entity.QuestionOption;
import com.se701.cat.entity.QuestionType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class QuestionDTO{
    public String id;

    public double difficultyParameter;

    public QuestionType type;

    public int moduleNumber;

    public String image;

    public String text;

    public List<Map<String,String>> options;

    public String correctAnswer;

    public Question toDomainObject() {
        List<QuestionOption> optionList = options
                .stream()
                .map(e -> new QuestionOption(e.get("id"), e.get("image"), e.get("text")))
                .collect(Collectors.toList());
        return new Question(id, difficultyParameter, type, image, text, moduleNumber, optionList, correctAnswer);
    }

    @Override
    public String toString() {
        return "QuestionDTO{" +
                "id='" + id + '\'' +
                ", difficultyParameter=" + difficultyParameter +
                ", type='" + type + '\'' +
                ", moduleNumber=" + moduleNumber +
                ", content='" + text + '\'' +
                ", options=" + options +
                '}';
    }
}
