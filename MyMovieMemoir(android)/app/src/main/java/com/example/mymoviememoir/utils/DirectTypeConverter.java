package com.example.mymoviememoir.utils;

import androidx.room.TypeConverter;

import com.example.mymoviememoir.bean.MovieBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

public class DirectTypeConverter {
    Gson gson = new Gson();

    @TypeConverter
    public List<MovieBean.SubjectsBean.DirectorsBean> stringToSomeObjectList(String data) {
        if (data == null) {
            return Collections.emptyList();
        }

        Type listType = new TypeToken<List<MovieBean.SubjectsBean.DirectorsBean>>() {}.getType();

        return gson.fromJson(data, listType);
    }

    @TypeConverter
    public String someObjectListToString(List<MovieBean.SubjectsBean.DirectorsBean> someObjects) {
        return gson.toJson(someObjects);
    }
}
