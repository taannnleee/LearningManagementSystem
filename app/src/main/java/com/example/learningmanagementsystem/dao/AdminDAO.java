package com.example.learningmanagementsystem.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.learningmanagementsystem.models.Admin;
import com.example.learningmanagementsystem.models.Classes;

import java.util.List;
@Dao
public interface AdminDAO {
    @Insert
    void insertAdmin(Admin... admin);

    @Query("select * from admin")
    List<Admin> getAllAdmin();

    @Query("SELECT COUNT(*) FROM admin")
    int getAdminCount();
    @Query("SELECT * FROM admin WHERE adminEmail = :email")
    Admin getAdminByEmail(String email);
}
