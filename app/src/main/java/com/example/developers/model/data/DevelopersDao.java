package com.example.developers.model.data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

import com.example.developers.model.pojo.DeveloperEntity;

@Dao
public interface DevelopersDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addDeveloper(DeveloperEntity developer);

}
