package com.example.subhamtandon.scbapp;

public class UploadPDF {


    String mName,mURL;
    public UploadPDF(String mName, String mURL) {
        if (mName.trim().equals(" ")){
            mName = "No name";
        }

        this.mName = mName;
        this.mURL = mURL;
    }
     public UploadPDF(){

     }

    public String getmName() {
        return mName;
    }

    public String getmURL() {
        return mURL;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public void setmURL(String mURL) {
        this.mURL = mURL;
    }
}
