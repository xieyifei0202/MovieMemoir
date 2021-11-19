package com.example.mymoviememoir.bean;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface WathcBeanDao {

    @Query("SELECT * FROM watchbean WHERE userName =:user ")
    WatchBean findByUsername(String user);

    @Update(onConflict = REPLACE)
    void updateCustomer(WatchBean customers);

    @Insert
    long insert(WatchBean subjectsBean);

}
