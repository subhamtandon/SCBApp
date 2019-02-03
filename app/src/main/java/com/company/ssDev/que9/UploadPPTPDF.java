package com.company.ssDev.que9;

public class UploadPPTPDF {

    String mName,mPDFURL, mThumbnailURL;
    public UploadPPTPDF(String mName, String mPDFURL, String mThumbnailURL) {
        if (mName.trim().equals(" ")){
            mName = "No name";
        }

        this.mName = mName;
        this.mPDFURL = mPDFURL;
        this.mThumbnailURL = mThumbnailURL;

    }
    public UploadPPTPDF(){

    }

    public String getmName() {
        return mName;
    }

    public String getmPDFURL() {
        return mPDFURL;
    }

    public String getmThumbnailURL() {
        return mThumbnailURL;
    }

    public void setmThumbnailURL(String mThumbnailURL) {
        this.mThumbnailURL = mThumbnailURL;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public void setmPDFURL(String mPDFURL) {
        this.mPDFURL = mPDFURL;
    }
}
