package com.example.mymoviememoir;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Main2Activity extends AppCompatActivity {


    TextView tv_back;
    WebView wv_view;
    private String path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        tv_back = findViewById(R.id.tv_back);
        wv_view = findViewById(R.id.wv_view);

        path = getIntent().getStringExtra("path");

        initWebview();

        tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    private void initWebview() {
        WebSettings webSettings = wv_view.getSettings();

        webSettings.setJavaScriptEnabled(true);

        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        wv_view.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        wv_view.setVerticalScrollbarOverlay(true);

        webSettings.setDomStorageEnabled(true);
        webSettings.setGeolocationEnabled(true);
        webSettings.setBuiltInZoomControls(true);

        wv_view.clearCache(true);
        webSettings.setCacheMode(android.webkit.WebSettings.LOAD_NO_CACHE);
        webSettings.setAllowFileAccess(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setLoadsImagesAutomatically(true);
        webSettings.setDefaultTextEncodingName("utf-8");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webSettings.setMixedContentMode(android.webkit.WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);

        webSettings.setSupportZoom(true);
        webSettings.setDisplayZoomControls(false);

        wv_view.setWebChromeClient(new WebChromeClient(){
            @Override
            public boolean onJsAlert(WebView webView, String s, String s1, JsResult jsResult) {
                Log.e("SSSSS","popup："+s+"::"+s1);
                return super.onJsAlert(webView, s, s1, jsResult);
            }
        });

        wv_view.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Log.e("SSSS","address1111："+url);
                view.loadUrl(url);
                return super.shouldOverrideUrlLoading(view, url);
            }
        });

        webSettings.setDatabaseEnabled(true);   // database storage API
        webSettings.setAppCacheEnabled(true);//Application Caches

        Log.e("SSSS","address："+path);
        wv_view.loadUrl(path);
    }

}
