package com.example.android.databaseapp;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class User {

    public User(String name) {

        this.name = name;
    }

    @PrimaryKey(autoGenerate = true)
    private int uid;

    @ColumnInfo(name = "name")
    private String name;


    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {

        this.uid = uid;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    @Override
    public String toString() {

        return uid + ": " + name;
    }
}
