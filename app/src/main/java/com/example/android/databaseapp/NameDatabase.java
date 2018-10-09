package com.example.android.databaseapp;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {User.class}, version = 1)
public abstract class NameDatabase extends RoomDatabase {
    public abstract NameDao nameDao();
}

