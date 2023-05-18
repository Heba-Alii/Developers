package com.example.developers.controller;

import android.content.Context;

import androidx.room.Database;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.developers.model.data.DeveloperDao;
import com.example.developers.model.pojo.DeveloperEntity;
import com.example.developers.view.developers.DeveloperAdapter;

@Database(entities = DeveloperEntity.class, version = 2)

public abstract class LocalBuilder extends RoomDatabase {
    private static LocalBuilder dbInstance;

    public abstract DeveloperDao developerDao();

    public static LocalBuilder getInstance(Context context) {
        if (dbInstance == null) {
            dbInstance = Room.databaseBuilder(context.getApplicationContext(), LocalBuilder.class, "DeveloperDB").build();
        }
        return dbInstance;
    }


}