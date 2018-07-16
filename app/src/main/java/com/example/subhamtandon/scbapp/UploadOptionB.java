package com.example.subhamtandon.scbapp;

public class UploadOptionB {

    private String optionBText;
    private String optionBImageUrl;
    private Boolean optionBValue;

    public UploadOptionB() {

    }

    public UploadOptionB(String optionBText, String optionBImageUrl, Boolean optionBValue) {
        this.optionBText = optionBText;
        this.optionBImageUrl = optionBImageUrl;
        this.optionBValue = optionBValue;
    }

    public String getOptionBText() {
        return optionBText;
    }

    public void setOptionBText(String optionBText) {
        this.optionBText = optionBText;
    }

    public String getOptionBImageUrl() {
        return optionBImageUrl;
    }

    public void setOptionBImageUrl(String optionBImageUrl) {
        this.optionBImageUrl = optionBImageUrl;
    }

    public Boolean getOptionBValue() {
        return optionBValue;
    }

    public void setOptionBValue(Boolean optionBValue) {
        this.optionBValue = optionBValue;
    }
}
