package com.company.ssDev.que9;

import java.io.Serializable;

public class Lists implements Serializable {
    String uniqueId, subjectName;

    public Lists() {
    }

    public Lists(String uniqueId, String subjectName) {
        this.uniqueId = uniqueId;
        this.subjectName = subjectName;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }
}
