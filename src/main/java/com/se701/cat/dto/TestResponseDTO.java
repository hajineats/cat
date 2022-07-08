package com.se701.cat.dto;

import java.util.Map;

public class TestResponseDTO {
    private Long userId;
    private Map<String,String> responses;

    public TestResponseDTO(Long userId, Map<String, String> responses) {
        this.userId = userId;
        this.responses = responses;
    }

    public TestResponseDTO(){

    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Map<String, String> getResponses() {
        return responses;
    }

    public void setResponses(Map<String, String> responses) {
        this.responses = responses;
    }
}
