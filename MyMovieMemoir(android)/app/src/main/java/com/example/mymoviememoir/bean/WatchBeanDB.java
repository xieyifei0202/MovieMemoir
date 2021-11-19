package com.example.mymoviememoir.bean;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {WatchBean.class}, version = 3, exportSchema = false)
public abstract class WatchBeanDB extends RoomDatabase {

    public abstract WathcBeanDao watchDAO();

    private static WatchBeanDB INSTANCE;

    public static synchronized WatchBeanDB getInstance(final Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    WatchBeanDB.class, "WatchBeanDB")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return INSTANCE;
    }
}
