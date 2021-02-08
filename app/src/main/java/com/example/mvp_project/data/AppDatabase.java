package com.example.mvp_project.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {News.class}, version = 1,exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract LocalDataSource getLocalDataSource();

    private static AppDatabase instance;

    public static AppDatabase getInstance(Context contex) {
        if (instance == null) {
            instance = Room.databaseBuilder(contex, AppDatabase.class, "novin_dbs").allowMainThreadQueries().build();

        }
        return instance;
    }
}
