package com.example.subhamtandon.scbapp;

public class UploadOptionA {

    private String optionAText;
    private String optionAImageUrl;
    private Boolean optionAValue;

    public UploadOptionA() {

    }

    public UploadOptionA(String optionAText, String optionAImageUrl, Boolean optionAValue) {
        this.optionAText = optionAText;
        this.optionAImageUrl = optionAImageUrl;
        this.optionAValue = optionAValue;
    }

    public String getOptionAText() {
        return optionAText;
    }

    public void setOptionAText(String optionAText) {
        this.optionAText = optionAText;
    }

    public String getOptionAImageUrl() {
        return optionAImageUrl;
    }

    public void setOptionAImageUrl(String optionAImageUrl) {
        this.optionAImageUrl = optionAImageUrl;
    }

    public Boolean getOptionAValue() {
        return optionAValue;
    }

    public void setOptionAValue(Boolean optionAValue) {
        this.optionAValue = optionAValue;
    }
}
