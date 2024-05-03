package com.example.learningmanagementsystem.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "support")
public class Support {
    @PrimaryKey(autoGenerate = true)
    private int supportId;
    private String supportContent;

    private  byte[] pictureSupport;

    public Support(int supportId, String supportContent, byte[] pictureSupport) {
        this.supportId = supportId;
        this.supportContent = supportContent;
        this.pictureSupport = pictureSupport;
    }

    public Support() {
    }

    public int getSupportId() {
        return supportId;
    }

    public void setSupportId(int supportId) {
        this.supportId = supportId;
    }

    public String getSupportContent() {
        return supportContent;
    }

    public void setSupportContent(String supportContent) {
        this.supportContent = supportContent;
    }

    public byte[] getPictureSupport() {
        return pictureSupport;
    }

    public void setPictureSupport(byte[] pictureSupport) {
        this.pictureSupport = pictureSupport;
    }
}
