package com.example.mymoviememoir.utils;

import androidx.room.TypeConverter;

import com.example.mymoviememoir.bean.MovieBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class RatingTypeConverter {
    Gson gson = new Gson();

    @TypeConverter
    public MovieBean.SubjectsBean.RatingBean stringToSomeObjectList(String data) {
        if (data == null) {
            return new MovieBean.SubjectsBean.RatingBean();
        }

        Type listType = new TypeToken<MovieBean.SubjectsBean.RatingBean>() {}.getType();

        return gson.fromJson(data, listType);
    }

    @TypeConverter
    public String someObjectListToString(MovieBean.SubjectsBean.RatingBean someObjects) {
        return gson.toJson(someObjects);
    }
}
