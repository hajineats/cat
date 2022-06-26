package com.se701.cat.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;

import java.util.HashMap;
import java.util.Map;

public class User {
    @Id
    public String id;
    private final String username;
    private int moduleNumberToTake;
    @JsonIgnore
    private int fixedScore;
    @JsonIgnore
    private int mstScore;

    @JsonProperty("scoresMap")
    public Map<String, Integer> getScores(){
        Map<String, Integer> map = new HashMap<>();
        map.put("mst", mstScore);
        map.put("fixed", fixedScore);
        return map;
    }

    public int getModuleNumberToTake() {
        return moduleNumberToTake;
    }

    public void setModuleNumberToTake(int moduleNumberToTake) {
        this.moduleNumberToTake = moduleNumberToTake;
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

    public User(String username, int moduleNumberToTake, int fixedScore, int mstScore) {
        this.username = username;
        this.moduleNumberToTake = moduleNumberToTake;
        this.fixedScore = fixedScore;
        this.mstScore = mstScore;
    }
}
