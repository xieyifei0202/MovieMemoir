package com.example.mymoviememoir.function.facebook;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mymoviememoir.R;
import com.example.mymoviememoir.adapter.ShareMovieAdapter;
import com.example.mymoviememoir.bean.MovieBean;
import com.example.mymoviememoir.networking.NetWorkHttp;
import com.example.mymoviememoir.networking.RxSchedulersHelper;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.share.Sharer;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import static com.example.mymoviememoir.networking.NetWorkHttp.API_BASE_URL;

public class FacebookFragment extends Fragment implements ShareMovieAdapter.IOnClickAdd {

    private FacebookViewModel mViewModel;

    RecyclerView rlv_main;
    ProgressBar pb_bar;
    private ShareMovieAdapter movieAdapter;
    private CallbackManager callbackManager;
    private ShareDialog shareDialog;

    public static FacebookFragment newInstance() {
        return new FacebookFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calender, container, false);
        rlv_main = view.findViewById(R.id.rlv_main);
        pb_bar = view.findViewById(R.id.pb_bar);
        rlv_main.setLayoutManager(new LinearLayoutManager(getActivity()));

        movieAdapter = new ShareMovieAdapter(getActivity());

        rlv_main.setAdapter(movieAdapter);

        movieAdapter.setiOnClickAdd(this);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        callbackManager = CallbackManager.Factory.create();

        shareDialog = new ShareDialog(getActivity());
        shareDialog.registerCallback(callbackManager, new FacebookCallback<Sharer.Result>() {
            @Override
            public void onSuccess(Sharer.Result result) {
                Toast.makeText(getActivity(),"Share success",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancel() {
                Toast.makeText(getActivity(),"Cancel the share",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(getActivity(),"Share the failure",Toast.LENGTH_SHORT).show();
            }
        });

        requestHttp();
    }

    private void requestHttp() {
        pb_bar.setVisibility(View.VISIBLE);
        NetWorkHttp.getApi(API_BASE_URL).obtainTheater(50)
                .compose(RxSchedulersHelper.defaultComposeRequest())
                .subscribe(new Observer<MovieBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(MovieBean movieBean) {
                        pb_bar.setVisibility(View.GONE);
                        if (movieAdapter != null) {
                            movieAdapter.changeData(movieBean.getSubjects());
                        }

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void iOnClickAddGallery(String title) {
        ShareLinkContent linkContent = new ShareLinkContent.Builder()
                .setContentUrl(Uri.parse("https://www.douban.com"))
                .setContentTitle("movie share")
                .setContentDescription(title + "")
                .build();
        shareDialog.show(linkContent);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode,resultCode,data);
    }
}