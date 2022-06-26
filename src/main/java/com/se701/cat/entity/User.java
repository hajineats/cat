package com.se701.cat.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.*;

@Document("users")
public class User {

    public enum TestType { MST, FL}

    public static final int DEFAULT_STAGE = 1;
    @Id
    public Long id;
    private int currentModule = DEFAULT_STAGE;
    private List<TestType> shouldTakes = new LinkedList<>();

    private int fixedScore;
    private int mstScore;
    private Map<String, String> fixedTestResponses = new HashMap<>();
    private Map<String, String> mstTestResponses = new HashMap<>();


    public List<TestType> getShouldTakes() {
        return shouldTakes;
    }

    public int getCurrentModule() {
        return currentModule;
    }

    public void setCurrentModule(int currentModule) {
        this.currentModule = currentModule;
    }

    public int getFixedScore() {
        return fixedScore;
    }

    public void setFixedScore(int fixedScore) {
        this.fixedScore = fixedScore;
    }

    public int getMstScore() {
        return mstScore;
    }

    public void setMstScore(int mstScore) {
        this.mstScore = mstScore;
    }

    public Map<String, String> getFixedTestResponses() {
        return Collections.unmodifiableMap(fixedTestResponses);
    }

    public Map<String, String> getMstTestResponses() {
        return Collections.unmodifiableMap(mstTestResponses);
    }

    public void addFixedTestResponse(String questionId, String answer){
        fixedTestResponses.put(questionId, answer);
    }

    public void addMSTTestResponse(String questionId, String answer){
        mstTestResponses.put(questionId, answer);
    }

    public User(Long id, int fixedScore, int mstScore, List<TestType> shouldTakes) {
        this.id = id;
        this.fixedScore = fixedScore;
        this.mstScore = mstScore;
        this.shouldTakes = shouldTakes;
    }
}
