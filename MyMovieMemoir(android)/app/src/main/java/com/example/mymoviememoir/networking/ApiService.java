package com.example.mymoviememoir.networking;

import com.example.mymoviememoir.bean.MovieBean;
import com.example.mymoviememoir.database.SearchMovieBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;



public interface ApiService {

    /**
     * get list
     */
    @GET("top250")
    Observable<MovieBean> obtainTheater(@Query("count")int count);


    /**
     * search movie
     */
    @GET("index")
    Observable<SearchMovieBean> obtainMovie(@Query("key")String key, @Query("title")String title);

    /**
     * movie view
     */
    @GET("subject/{id}")
    Observable<SearchMovieBean> obtainMovieThing(@Path("id")String id);
}

