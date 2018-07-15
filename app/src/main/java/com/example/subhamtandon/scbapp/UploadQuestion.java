package com.example.subhamtandon.scbapp;

public class UploadQuestion {

    private String questionText;
    private String questionImageUrl;

    public UploadQuestion() {

    }

    public UploadQuestion(String questionText, String questionImageUrl) {
        this.questionText = questionText;
        this.questionImageUrl = questionImageUrl;
    }

    public String getQuestionText() {
        return questionText;
    }

    public String getQuestionImageUrl() {
        return questionImageUrl;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public void setQuestionImageUrl(String questionImageUrl) {
        this.questionImageUrl = questionImageUrl;
    }
}

