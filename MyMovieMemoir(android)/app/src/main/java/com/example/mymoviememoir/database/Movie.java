package com.example.mymoviememoir.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity
public class Movie {
    @PrimaryKey(autoGenerate = true)
    public int movID;
    @ColumnInfo(name = "movie_name")
    public String movieName;
    @ColumnInfo(name = "release_date")
    public Date releaseDate;
    @ColumnInfo(name = "add_date_time")
    public Date addDate;

    public Movie(int movID, String movieName, Date releaseDate, Date addDate) {
        this.movID = movID;
        this.movieName = movieName;
        this.releaseDate = releaseDate;
        this.addDate = addDate;
    }

    public int getMovieID() {
        return movID;
    }

    public void setMovieID(int movID) {
        this.movID = movID;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Date getAddDate() {
        return addDate;
    }

    public void setAddDate(Date addDate) {
        this.addDate = addDate;
    }
}
