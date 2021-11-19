package com.example.mymoviememoir.function.map;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.android.gms.maps.model.LatLng;
import com.google.gson.JsonArray;
import com.example.mymoviememoir.networking.NetworkConnection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MapViewModel extends ViewModel {
    NetworkConnection networkConnection = new NetworkConnection();
    private MutableLiveData<List<LatLng>> liveDataAddList;
    private Context context;
    private List<LatLng> addList;

    public MapViewModel() {
        liveDataAddList = new MutableLiveData<>();
    }

    public void init(Context context){
        this.context = context;
        this.addList = new ArrayList<>();
    }
    public LiveData<List<LatLng>> getLiveDataAddList() {
        return liveDataAddList;
    }

    public void getAddProcessing(){
        try {
            GetAddTask addPersonTask = new GetAddTask();
            addPersonTask.execute("Monash University Caulfield Campus, Australia");
            GetAddTask addPersonTask2 = new GetAddTask();
            addPersonTask2.execute("st kilda VIC Australia");
        } catch (Exception e) {
            Log.e("MapView","getAddProcessing error");
        }
    }

    private class GetAddTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            return networkConnection.getLatAndLng(params[0]);
        }

        @Override
        protected void onPostExecute(String result) {
            try {
                JSONObject jsonObject = new JSONObject(result);
                JSONArray resultArray = jsonObject.getJSONArray("results");
                if (resultArray.length() > 0){
                    JSONObject firstAddObj = new JSONObject(resultArray.get(0).toString());
                    JSONObject firstResult = firstAddObj.getJSONObject("geometry");
                    LatLng latLng = new LatLng(firstResult.getDouble("lat"),firstResult.getDouble("lng"));
                    Log.i("mapModelView",latLng.latitude + " " + latLng.longitude);
                    addList.add(latLng);
                    liveDataAddList.setValue(addList);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }

    private LatLng getLocationFromAddress(String strAddress) {
        Geocoder coder = new Geocoder(context);
        List<Address> address;
        LatLng place = null;
        try {
            address = coder.getFromLocationName(strAddress, 5);
            if (address == null) {
                return null;
            }
            Address location = address.get(0);
            location.getLatitude();
            location.getLongitude();
            place = new LatLng(location.getLatitude(), location.getLongitude());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return place;
    }
}
