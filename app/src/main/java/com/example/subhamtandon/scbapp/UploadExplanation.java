package com.example.subhamtandon.scbapp;

public class UploadExplanation {

    private String explanationText;
    private String explanationImageUrl;

    public UploadExplanation(){

    }

    public UploadExplanation(String explanationText, String explanationImageUrl) {
        this.explanationText = explanationText;
        this.explanationImageUrl = explanationImageUrl;
    }

    public String getExplanationText() {
        return explanationText;
    }

    public void setExplanationText(String explanationText) {
        this.explanationText = explanationText;
    }

    public String getExplanationImageUrl() {
        return explanationImageUrl;
    }

    public void setExplanationImageUrl(String explanationImageUrl) {
        this.explanationImageUrl = explanationImageUrl;
    }
}
