package com.example.mymoviememoir.function.search;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.android.gms.maps.model.LatLng;
import com.example.mymoviememoir.networking.NetworkConnection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SearchViewModel extends ViewModel {
    NetworkConnection networkConnection = new NetworkConnection();
    private MutableLiveData<String> mText;
    private MutableLiveData<Bitmap> movieImage;

    public SearchViewModel() {
        mText = new MutableLiveData<>();
        movieImage = new MutableLiveData<>();
        mText.setValue("This is gallery fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }

    public LiveData<Bitmap> getImage() {
        return movieImage;
    }

    public void getImageProcessing(){
        GetImageTask getImageTask = new GetImageTask();
        getImageTask.execute();
    }

    private class GetImageTask extends AsyncTask<String, Void, Bitmap> {
        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        @Override
        protected Bitmap doInBackground(String... params) {
            return networkConnection.getMoviePoster();
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            movieImage.setValue(result);
        }
    }


}