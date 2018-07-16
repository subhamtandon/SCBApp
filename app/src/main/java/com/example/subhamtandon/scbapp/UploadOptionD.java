package com.example.subhamtandon.scbapp;

public class UploadOptionD {

    private String optionDText;
    private String optionDImageUrl;
    private Boolean optionDValue;

    public UploadOptionD() {

    }

    public UploadOptionD(String optionDText, String optionDImageUrl, Boolean optionDValue) {
        this.optionDText = optionDText;
        this.optionDImageUrl = optionDImageUrl;
        this.optionDValue = optionDValue;
    }

    public String getOptionDText() {
        return optionDText;
    }

    public void setOptionDText(String optionDText) {
        this.optionDText = optionDText;
    }

    public String getOptionDImageUrl() {
        return optionDImageUrl;
    }

    public void setOptionDImageUrl(String optionDImageUrl) {
        this.optionDImageUrl = optionDImageUrl;
    }

    public Boolean getOptionDValue() {
        return optionDValue;
    }

    public void setOptionDValue(Boolean optionDValue) {
        this.optionDValue = optionDValue;
    }
}
