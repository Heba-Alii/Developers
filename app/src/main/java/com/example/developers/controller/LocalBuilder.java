package com.example.developers.controller;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.developers.model.pojo.DeveloperEntity;

@Database(entities = DeveloperEntity.class,version = 1)
public abstract class LocalBuilder extends RoomDatabase {
}
