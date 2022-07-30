package com.se701.cat.entity;

public class QuestionOption {

    private String optionId;

    private String optionImage;
    private String optionText;

    public QuestionOption(String optionId, String optionImage, String optionText) {
        this.optionId = optionId;
        this.optionImage = optionImage;
        this.optionText = optionText;
    }

    public String getOptionId() {
        return optionId;
    }

    public String getOptionImage() {
        return optionImage;
    }

    public String getOptionText() {
        return optionText;
    }
}
