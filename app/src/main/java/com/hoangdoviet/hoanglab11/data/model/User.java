package com.hoangdoviet.hoanglab11.data.model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.hoangdoviet.hoanglab11.data.converter.DateConverter;

import java.util.Date;
@Entity(tableName = "user_table")
@TypeConverters(DateConverter.class)
public class User {
    @PrimaryKey(autoGenerate = true)
    public long id;
    public String name;
    public String address;
    public Date birthday;
    public String phone;
    public String email;
    @Ignore
    public User() {
        this.name = "";
        this.address = "";
        this.birthday = null;
        this.phone = "";
        this.email = "";
    }

    public User(String name, String address, Date birthday, String phone, String email) {
        this.name = name;
        this.address = address;
        this.birthday = birthday;
        this.phone = phone;
        this.email = email;
    }
}
