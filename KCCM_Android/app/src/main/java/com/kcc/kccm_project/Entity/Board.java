package com.kcc.kccm_project.Entity;

import com.kcc.kccm_project.util.DateUtil;

import java.util.Date;

public class Board {
    private String title;
    private String content;
    private String uid;
    private String classification;
    private String date;

    public Board() {
        date = DateUtil.today();
    }

    public Board(String title, String content, String uid, String classification) {
        this.title = title;
        this.content = content;
        this.uid = uid;
        this.classification = classification;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
