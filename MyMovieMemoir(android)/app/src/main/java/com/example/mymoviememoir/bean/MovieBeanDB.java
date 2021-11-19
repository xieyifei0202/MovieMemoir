package com.example.mymoviememoir.bean;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {MovieBean.SubjectsBean.class}, version = 3, exportSchema = false)
public abstract class MovieBeanDB extends RoomDatabase {

    public abstract MovieBeanDao movieDAO();

    private static MovieBeanDB INSTANCE;

    public static synchronized MovieBeanDB getInstance(final Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    MovieBeanDB.class, "MovieBeanDB")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return INSTANCE;
    }
}
