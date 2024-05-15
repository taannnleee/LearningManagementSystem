package com.example.learningmanagementsystem.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity(tableName = "admin")
public class Admin {
    @PrimaryKey(autoGenerate = true)
    private int adminId;

    private String adminEmail;

    private String adminPassword;

    public Admin(String adminEmail, String adminPassword) {
        this.adminEmail = adminEmail;
        this.adminPassword = adminPassword;
    }
    public Admin() {
    }

    public int getAdminId() {
        return adminId;
    }

    public String getAdminEmail() {
        return adminEmail;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public void setAdminEmail(String adminEmail) {
        this.adminEmail = adminEmail;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }
}
