package com.example.mymoviememoir.networking;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.example.mymoviememoir.signin.model.Person;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class NetworkConnection {

    private OkHttpClient client = null;

    public static final MediaType JSON =
            MediaType.parse("application/json; charset=utf-8");

    public NetworkConnection() {
        client = new OkHttpClient();
    }

    private static final String BASE_URL =
            "http://192.168.1.11:8080/MyMovieMemoir2/webresources/";
//    private static final String BASE_URL =
//            "http://b8rhxy.natappfree.cc/MyMovieMemoir2/webresources/";

    //http://7cdn6q.natappfree.cc/MyMovieMemoir2/webresources/memoir2rest.credential/findByUsernameAndPasswordhash/yxie0034@student.monash.edu/38b3eff8baf56627478ec76a704e9b52

    @RequiresApi(api = Build.VERSION_CODES.Q)
    public String getCredential(String username, String password) {

        final String methodPath = "memoir2rest.credential/findByUsernameAndPasswordhash/" + username + "/" + md5(password);
        Request.Builder builder = new Request.Builder();
        builder.url(BASE_URL + methodPath);

        Log.e("SSSS","signin api："+BASE_URL+methodPath);
        Request request = builder.build();
        String results = "";
        try {
            Response response = client.newCall(request).execute();
            results = response.body().string();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return results;
    }

    public String addPerson(Person person) {
        Gson gson = new Gson();
        String personJson = gson.toJson(person);
        String results = "";
        final String methodPath = "memoir2rest.person/";
        RequestBody body = RequestBody.create(JSON, personJson);
        Log.d("sign_in", "json: " + personJson);

        Request request = new Request.Builder()
                .url(BASE_URL + methodPath)
                .post(body)
                .build();
        try {
            Response response = client.newCall(request).execute();

            Log.d("sign_in", "result: " + response.code());
            if (response.code() == 204) {
                return "204";
            }
            results = response.body().string();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return results;
    }

    public String getLatAndLng(String address) {
        final String methodPath = "http://api.opencagedata.com/geocode/v1/json?q=" + address + "&key=0452842a53cd4aaa96ca3af3eb9a38e0";
        Request.Builder builder = new Request.Builder();
        builder.url(methodPath);
        Request request = builder.build();
        String results = "";
        try {
            Response response = client.newCall(request).execute();
            results = response.body().string();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return results;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public Bitmap getMoviePoster() {
        final String methodPath = "http://image.tmdb.org/t/p/w92/xBHvZcjRiWyobQ9kxBhO6B2dtRI.jpg";
        Request.Builder builder = new Request.Builder();
        builder.url(methodPath);
        Request request = builder.build();
        Bitmap bitmap = null;
        try {
            Response response = client.newCall(request).execute();
            InputStream results = Objects.requireNonNull(response.body()).byteStream();
            bitmap = BitmapFactory.decodeStream(results);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    private String md5(String str) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            byte b[] = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if(i<0) i+= 256;
                if(i<16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }

            return buf.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
//        String encodeStr = "";
//        try {
//            messageDigest = MessageDigest.getInstance("SHA-256");
//            messageDigest.update(str.getBytes("UTF-8"));
//            encodeStr = byte2Hex(messageDigest.digest());
//        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//        return encodeStr;
    }


    private String byte2Hex(byte[] bytes) {
        StringBuilder stringBuffer = new StringBuilder();
        String temp = null;
        for (byte aByte : bytes) {
            temp = Integer.toHexString(aByte & 0xFF);
            if (temp.length() == 1) {
                stringBuffer.append("0");
            }
            stringBuffer.append(temp);
        }
        return stringBuffer.toString();
    }

    private Date convertToDate(String str) {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return simpleDateFormat.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}

