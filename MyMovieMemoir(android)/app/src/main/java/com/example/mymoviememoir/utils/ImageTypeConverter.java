package com.example.mymoviememoir.utils;

import androidx.room.TypeConverter;

import com.example.mymoviememoir.bean.MovieBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class ImageTypeConverter {
    Gson gson = new Gson();

    @TypeConverter
    public MovieBean.SubjectsBean.ImagesBean stringToSomeObjectList(String data) {
        if (data == null) {
            return new MovieBean.SubjectsBean.ImagesBean();
        }

        Type listType = new TypeToken<MovieBean.SubjectsBean.ImagesBean>() {}.getType();

        return gson.fromJson(data, listType);
    }

    @TypeConverter
    public String someObjectListToString(MovieBean.SubjectsBean.ImagesBean someObjects) {
        return gson.toJson(someObjects);
    }
}
