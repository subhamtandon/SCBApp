package com.example.subhamtandon.scbapp;

public class UploadOptionC {

    private String optionCText;
    private String optionCImageUrl;
    private Boolean optionCValue;

    public UploadOptionC() {

    }

    public UploadOptionC(String optionCText, String optionCImageUrl, Boolean optionCValue) {
        this.optionCText = optionCText;
        this.optionCImageUrl = optionCImageUrl;
        this.optionCValue = optionCValue;
    }

    public String getOptionCText() {
        return optionCText;
    }

    public void setOptionCText(String optionCText) {
        this.optionCText = optionCText;
    }

    public String getOptionCImageUrl() {
        return optionCImageUrl;
    }

    public void setOptionCImageUrl(String optionCImageUrl) {
        this.optionCImageUrl = optionCImageUrl;
    }

    public Boolean getOptionCValue() {
        return optionCValue;
    }

    public void setOptionCValue(Boolean optionCValue) {
        this.optionCValue = optionCValue;
    }
}
