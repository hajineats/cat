package com.se701.cat.entity;

import java.util.List;

public class FixedLengthTest {
    private String name;
    private List<String> questionIdList;

    public FixedLengthTest(String name, List<String> questionIdList) {
        this.name = name;
        this.questionIdList = questionIdList;
    }
}
