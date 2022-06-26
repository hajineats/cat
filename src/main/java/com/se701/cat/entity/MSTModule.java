package com.se701.cat.entity;

import java.util.List;

public class MSTModule {
    private int moduleNumber;
    private List<String> questionIdList;

    public MSTModule(int moduleNumber, List<String> questionIdList) {
        this.moduleNumber = moduleNumber;
        this.questionIdList = questionIdList;
    }
}
