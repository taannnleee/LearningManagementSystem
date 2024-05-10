package com.example.learningmanagementsystem.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "support")
public class Support {
    @PrimaryKey(autoGenerate = true)
    private int supportId;

    private String supportTitle;
    private String supportContent;

    public Support() {
    }

    public Support(String supportTitle, String supportContent) {
        this.supportTitle = supportTitle;
        this.supportContent = supportContent;
    }

    public int getSupportId() {
        return supportId;
    }

    public void setSupportId(int supportId) {
        this.supportId = supportId;
    }

    public String getSupportTitle() {
        return supportTitle;
    }

    public void setSupportTitle(String supportTitle) {
        this.supportTitle = supportTitle;
    }

    public String getSupportContent() {
        return supportContent;
    }

    public void setSupportContent(String supportContent) {
        this.supportContent = supportContent;
    }
}
