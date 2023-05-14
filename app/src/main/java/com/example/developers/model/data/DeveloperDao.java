package com.example.developers.model.data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.developers.model.pojo.DeveloperEntity;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface DeveloperDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addDeveloper(DeveloperEntity developer);

    @Query("delete from dev_table where id=:developerId")
    void deleteDeveloper(int developerId);

    @Query("select * from dev_table where title= :developerTitle")
    List<DeveloperEntity> getdeveloperByTitle(String developerTitle);
}

