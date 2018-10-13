package com.example.subhamtandon.scbapp;

public class UploadInfo {

    String infoText, infoDate, infoTime, infoImageUri;

    public UploadInfo(String infoText, String infoDate, String infoTime, String infoImageUri) {
        this.infoText = infoText;
        this.infoDate = infoDate;
        this.infoTime = infoTime;
        this.infoImageUri = infoImageUri;
    }
    public UploadInfo(){

    }

    public String getInfoText() {
        return infoText;
    }

    public String getInfoDate() {
        return infoDate;
    }

    public String getInfoTime() {
        return infoTime;
    }

    public String getInfoImageUri() {
        return infoImageUri;
    }

    public void setInfoImageUri(String infoImageUri) {
        this.infoImageUri = infoImageUri;
    }

    public void setInfoText(String infoText) {
        this.infoText = infoText;
    }

    public void setInfoDate(String infoDate) {
        this.infoDate = infoDate;
    }

    public void setInfoTime(String infoTime) {
        this.infoTime = infoTime;
    }
}
