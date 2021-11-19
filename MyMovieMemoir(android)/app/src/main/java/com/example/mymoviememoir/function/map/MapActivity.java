package com.example.mymoviememoir.function.map;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.mymoviememoir.PermissionUtils;
import com.example.mymoviememoir.R;
import com.example.mymoviememoir.bean.PlaceBean;
import com.example.mymoviememoir.shared_preference.SharedPreferenceUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private MapViewModel viewModel;
    private String address = "address";
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    private PlaceBean.ResultsBean.GeometryBean.LocationBean endLocation;
    private Marker perth ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        viewModel = new ViewModelProvider(this).get(MapViewModel.class);
        viewModel.init(this);
        viewModel.getAddProcessing();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng melbourne = new LatLng(-37.813629, 144.963058);
        LatLng monashClayton = new LatLng(-37.9161872, 145.140213);
        mMap.addMarker(new MarkerOptions()
                .position(monashClayton)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
                .title("Marker in Monash Clayton")
                .snippet("Latitude: " + monashClayton.latitude + ", Longitude: " + monashClayton.longitude));
        mMap.addMarker(new MarkerOptions().position(melbourne).title("Marker in Melbourne"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(melbourne));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(10));

        new Thread(){
            @Override
            public void run() {
                super.run();

                Log.e("SSSS","start searching position");

                address = SharedPreferenceUtil.getInstance(MapActivity.this).getString("address");

                if (address == null || "".equals(address)) {
                    address = "au";
                }
                String newAddress = getCoordinate(address);

                Message message = new Message();
                message.obj = newAddress;
                message.what = 0;
                handler.sendMessage(message);
            }
        }.start();

//        viewModel.getLiveDataAddList().observe(this, latLngs -> {
//            int i = 0;
//            for (LatLng l : latLngs) {
//                if (l != null)
//                    mMap.addMarker(new MarkerOptions()
//                            .position(l)
//                            .title(String.valueOf(i++))
//                            .snippet("Latitude: " + l.latitude + ", Longitude: " + l.longitude));
//                Log.d("mapActivity",l.latitude + " " + l.longitude);
//            }
//        });
    }



    /**
     * 如果取得了权限,显示地图定位层
     */
    private void enableMyLocation() {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission to access the location is missing.
            PermissionUtils.requestPermission(this, LOCATION_PERMISSION_REQUEST_CODE,
                    android.Manifest.permission.ACCESS_FINE_LOCATION, true);
        } else if (mMap != null) {
            // Access to the location has been granted to the app.
            mMap.setMyLocationEnabled(true);
        }
    }

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0) {
                String address = (String) msg.obj;
                Log.e("SSSS","request address："+address);
                PlaceBean placeBean = new Gson().fromJson(address, PlaceBean.class);

                List<PlaceBean.ResultsBean> results = placeBean.getResults();

                endLocation = results.get(0).getGeometry().getLocation();
                if (endLocation == null) {
                    enableMyLocation();
                    return;
                }
                LatLng latLng = new LatLng(endLocation.getLat(), endLocation.getLng());
                displayPerth(true, latLng);

                mMap.addMarker(new MarkerOptions()
                        .position(latLng)
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
                        .title("Marker in Monash Clayton")
                        .snippet("Latitude: " + latLng.latitude + ", Longitude: " + latLng.longitude));
                mMap.addMarker(new MarkerOptions().position(latLng).title("Marker in Melbourne"));
                perth.setPosition(latLng);
                initCamera(latLng);
            }
        }
    };

    /**
     * 添加标记
     */
    private void displayPerth(boolean isDraggable,LatLng latLng) {
        if (perth == null){
            perth = mMap.addMarker(new MarkerOptions().position(latLng).title("Your Position"));
            perth.setDraggable(isDraggable); //设置可移动
        }
    }

    /**
     * 将地图视角切换到定位的位置
     */
    private void initCamera(final LatLng sydney) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(800);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                runOnUiThread(() -> mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(sydney,14)));
            }
        }).start();
    }

    public static String getCoordinate(String addr) {

        String address = null;

        try {

            address = java.net.URLEncoder.encode(addr,"UTF-8");

        } catch (UnsupportedEncodingException e1) {

            e1.printStackTrace();

        }

        String output = "csv";

        String key = "AIzaSyAkPiBHUOl9E29NMNM82Ajal9P5_rA4iog";

        String url = String.format("https://maps.googleapis.com/maps/api/geocode/json?address=%s&output=%s&key=%s", address, output, key);

        URL myURL = null;

        URLConnection httpsConn = null;

        //进行转码

        try {

            myURL = new URL(url);

        } catch (MalformedURLException e) {

            e.printStackTrace();

        }
        try {

            httpsConn = (URLConnection) myURL.openConnection();

            if (httpsConn != null) {

                InputStreamReader insr = new InputStreamReader(

                        httpsConn.getInputStream(), "UTF-8");

                BufferedReader br = new BufferedReader(insr);

                String content = "";

                String line = null;

                while((line = br.readLine()) != null) {
                    content += line;
                    Log.e("SSSS","splice："+content);
                }

                insr.close();

                return content;
            }

        } catch (IOException e) {

            e.printStackTrace();
            Log.e("SSSS","error："+e.toString());

        }

        return "";
    }

}