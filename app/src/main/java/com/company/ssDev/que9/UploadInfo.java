package com.company.ssDev.que9;

public class UploadInfo {

    String infoTitle, infoDescription, infoDate, infoTime, infoImageUri;

    public UploadInfo(String infoTitle,String infoDescription, String infoDate, String infoTime, String infoImageUri) {
        this.infoTitle = infoTitle;
        this.infoDescription = infoDescription;
        this.infoDate = infoDate;
        this.infoTime = infoTime;
        this.infoImageUri = infoImageUri;
    }
    public UploadInfo(){

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

    public String getInfoTitle() {
        return infoTitle;
    }

    public void setInfoTitle(String infoTitle) {
        this.infoTitle = infoTitle;
    }

    public String getInfoDescription() {
        return infoDescription;
    }

    public void setInfoDescription(String infoDescription) {
        this.infoDescription = infoDescription;
    }

    public void setInfoDate(String infoDate) {
        this.infoDate = infoDate;
    }

    public void setInfoTime(String infoTime) {
        this.infoTime = infoTime;
    }
}
