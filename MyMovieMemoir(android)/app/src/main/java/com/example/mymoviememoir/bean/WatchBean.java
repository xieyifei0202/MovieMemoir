package com.example.mymoviememoir.bean;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.mymoviememoir.utils.StringTypeConverter;

import java.util.List;

@Entity
public class WatchBean {

    @NonNull
    @PrimaryKey
    private int id;

    private String userName;

    @TypeConverters(StringTypeConverter.class)
    private List<String> movieTitle;

    public String getUserName() {
        return userName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<String> getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(List<String> movieTitle) {
        this.movieTitle = movieTitle;
    }

    @Override
    public String toString() {
        return "{" +
                "\"id\":\'" + id + "\'" +
                ", \"userName\":\'" + userName + "\'" +
                ", \"movieTitle\":" + movieTitle +
                '}';
    }
}
