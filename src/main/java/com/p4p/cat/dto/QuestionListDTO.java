package com.p4p.cat.dto;

import com.p4p.cat.entity.Question;

import java.util.List;
import java.util.stream.Collectors;

public class QuestionListDTO {
    private List<QuestionDTO> questions;

    public List<QuestionDTO> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionDTO> questions) {
        this.questions = questions;
    }

    public List<Question> toDomainObject() {
        return questions.stream().map(e -> e.toDomainObject()).collect(Collectors.toList());
    }

    @Override
    public String toString() {
        String val = questions.stream().map(e -> e.toString()).collect(Collectors.joining(" "));
        return val;
    }
}
