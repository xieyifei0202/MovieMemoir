package com.example.mymoviememoir.networking;


import android.util.Log;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class NetWorkHttp {
    public static final String API_BASE_URL = "https://douban.uieee.com/v2/movie/";

    public static final String API_BASE_SEARCH_URL = "http://v.juhe.cn/movie/";

    private static Retrofit mRetrofit;
    private static OkHttpClient mOkHttpClient;

    public final static int CONNECT_TIMEOUT = 60 * 60;
    public final static int READ_TIMEOUT = 60 * 60;
    public final static int WRITE_TIMEOUT = 60 * 60;

    public static OkHttpClient genericClient() {

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.i("xxx",message);
            }
        });


        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);



        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
                .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .addNetworkInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        return chain.proceed(chain.request().newBuilder().build());
                    }
                })
                .build();
        return httpClient;
    }

    public static ApiService getApi(String baseUrl) {
        return getRetrofit(baseUrl).create(ApiService.class);
    }

    protected static Retrofit getRetrofit(String baseUrl) {
        if (null == mOkHttpClient) {
            mOkHttpClient = genericClient();
        }
        mRetrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(mOkHttpClient)
                .build();

        return mRetrofit;
    }

}
