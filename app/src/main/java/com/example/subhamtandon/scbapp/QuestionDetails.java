package com.example.subhamtandon.scbapp;

public class QuestionDetails {

    String questionText;
    String questionImageUrl;
    String explanationText;
    String explanationImageUrl;
    String option1Text;
    String option1ImageUrl;
    String option2Text;
    String  option2ImageUrl;
    String option3Text;
    String option3ImageUrl;
    String option4Text;
    String option4ImageUrl;
    String chapter;
    String mode;
    String set;

    public QuestionDetails(){

    }

    public QuestionDetails(String questionText, String questionImageUrl, String explanationText, String explanationImageUrl, String option1Text, String option1ImageUrl, String option2Text, String option2ImageUrl, String option3Text, String option3ImageUrl, String option4Text, String option4ImageUrl, String chapter, String mode, String set) {
        this.questionText = questionText;
        this.questionImageUrl = questionImageUrl;
        this.explanationText = explanationText;
        this.explanationImageUrl = explanationImageUrl;
        this.option1Text = option1Text;
        this.option1ImageUrl = option1ImageUrl;
        this.option2Text = option2Text;
        this.option2ImageUrl = option2ImageUrl;
        this.option3Text = option3Text;
        this.option3ImageUrl = option3ImageUrl;
        this.option4Text = option4Text;
        this.option4ImageUrl = option4ImageUrl;
        this.chapter = chapter;
        this.mode = mode;
        this.set = set;
    }

    public String getQuestionText() {
        return questionText;
    }

    public String getQuestionImageUrl() {
        return questionImageUrl;
    }

    public String getExplanationText() {
        return explanationText;
    }

    public String getExplanationImageUrl() {
        return explanationImageUrl;
    }

    public String getOption1Text() {
        return option1Text;
    }

    public String getOption1ImageUrl() {
        return option1ImageUrl;
    }

    public String getOption2Text() {
        return option2Text;
    }

    public String getOption2ImageUrl() {
        return option2ImageUrl;
    }

    public String getOption3Text() {
        return option3Text;
    }

    public String getOption3ImageUrl() {
        return option3ImageUrl;
    }

    public String getOption4Text() {
        return option4Text;
    }

    public String getOption4ImageUrl() {
        return option4ImageUrl;
    }

    public String getChapter() {
        return chapter;
    }

    public String getMode() {
        return mode;
    }

    public String getSet() {
        return set;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public void setQuestionImageUrl(String questionImageUrl) {
        this.questionImageUrl = questionImageUrl;
    }

    public void setExplanationText(String explanationText) {
        this.explanationText = explanationText;
    }

    public void setExplanationImageUrl(String explanationImageUrl) {
        this.explanationImageUrl = explanationImageUrl;
    }

    public void setOption1Text(String option1Text) {
        this.option1Text = option1Text;
    }

    public void setOption1ImageUrl(String option1ImageUrl) {
        this.option1ImageUrl = option1ImageUrl;
    }

    public void setOption2Text(String option2Text) {
        this.option2Text = option2Text;
    }

    public void setOption2ImageUrl(String option2ImageUrl) {
        this.option2ImageUrl = option2ImageUrl;
    }

    public void setOption3Text(String option3Text) {
        this.option3Text = option3Text;
    }

    public void setOption3ImageUrl(String option3ImageUrl) {
        this.option3ImageUrl = option3ImageUrl;
    }

    public void setOption4Text(String option4Text) {
        this.option4Text = option4Text;
    }

    public void setOption4ImageUrl(String option4ImageUrl) {
        this.option4ImageUrl = option4ImageUrl;
    }

    public void setChapter(String chapter) {
        this.chapter = chapter;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public void setSet(String set) {
        this.set = set;
    }
}

