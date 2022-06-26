package com.se701.cat.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;

import java.util.*;

public class User {
    public static final int DEFAULT_STAGE = 1;

    @Id
    public String id;
    private final String username;
    private int currentModule = DEFAULT_STAGE;
    @JsonIgnore
    private Queue<String> shouldTakes = new LinkedList<>();
    @JsonIgnore
    private int fixedScore;
    @JsonIgnore
    private int mstScore;
    @JsonIgnore
    private Map<String, String> fixedTestResponses = new HashMap<>();
    @JsonIgnore
    private Map<String, String> mstTestResponses = new HashMap<>();

    @JsonProperty("shouldTakes")
    public List<String> getShouldTakes(){
        return List.copyOf(shouldTakes);
    }

    @JsonProperty("scores")
    public Map<String, Integer> getScores(){
        Map<String, Integer> map = new HashMap<>();
        map.put("mst", mstScore);
        map.put("fixed", fixedScore);
        return map;
    }

    @JsonProperty("responses")
    public Map<String, Map<String, String>> getResponses(){
        Map<String, Map<String, String>> map = new HashMap<>();
        map.put("mst", mstTestResponses);
        map.put("fixed", fixedTestResponses);
        return map;
    }

    public void addShouldTakes(String shouldTake){
        shouldTakes.offer(shouldTake);
    }

    /**
     * @return null if queue is empty
     */
    public String currentShouldTake(){
        return shouldTakes.peek();
    }

    /**
     * @return null if queue is empty
     */
    public String popShouldTake(){
        return shouldTakes.poll();
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

    public User(String username, int fixedScore, int mstScore) {
        this.username = username;
        this.fixedScore = fixedScore;
        this.mstScore = mstScore;
    }
}
