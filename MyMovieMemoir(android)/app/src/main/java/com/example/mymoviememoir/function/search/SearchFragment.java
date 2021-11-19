package com.example.mymoviememoir.function.search;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mymoviememoir.MovieInfoActivity;
import com.example.mymoviememoir.R;
import com.example.mymoviememoir.adapter.SearchMovieAdapter;
import com.example.mymoviememoir.bean.MovieBean;
import com.example.mymoviememoir.bean.WatchBean;
import com.example.mymoviememoir.bean.WatchBeanDB;
import com.example.mymoviememoir.bean.WathcBeanDao;
import com.example.mymoviememoir.database.SearchMovieBean;
import com.example.mymoviememoir.networking.NetWorkHttp;
import com.example.mymoviememoir.networking.RxSchedulersHelper;
import com.example.mymoviememoir.shared_preference.SharedPreferenceUtil;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import static com.example.mymoviememoir.networking.NetWorkHttp.API_BASE_SEARCH_URL;

public class SearchFragment extends Fragment implements View.OnClickListener, SearchMovieAdapter.IOnClick {

    private SearchViewModel searchViewModel;

    private EditText edt_search;
    private TextView tv_cancel;
    private RecyclerView rlv_main;
    private SearchMovieAdapter movieAdapter;
    private WathcBeanDao wathcBeanDao;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        searchViewModel =
                ViewModelProviders.of(this).get(SearchViewModel.class);
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
        edt_search = root.findViewById(R.id.edt_search);
        tv_cancel = root.findViewById(R.id.tv_cancel);
        rlv_main = root.findViewById(R.id.rlv_main);
        rlv_main.setLayoutManager(new LinearLayoutManager(getActivity()));

        movieAdapter = new SearchMovieAdapter(getActivity());
        rlv_main.setAdapter(movieAdapter);
        movieAdapter.setiOnClick(this);

        tv_cancel.setOnClickListener(this);

        wathcBeanDao = WatchBeanDB.getInstance(getActivity()).watchDAO();
        return root;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_cancel:
                // 搜索
                String input = edt_search.getText().toString();
                if (input == null || "".equals(input)) {
                    Toast.makeText(getActivity(),"Please enter the name of the movie",Toast.LENGTH_SHORT).show();
                    return;
                }
                requestMovie(input);
                break;
        }
    }

    private void requestMovie(String movieName) {

        NetWorkHttp.getApi(API_BASE_SEARCH_URL).obtainMovie("40e4bd3ef8c1c267df00d332be869716",movieName)
                .compose(RxSchedulersHelper.defaultComposeRequest())
                .subscribe(new Observer<SearchMovieBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(SearchMovieBean movieBean) {
                        String resultcode = movieBean.getResultcode();
                        if ("200".equals(resultcode)) {
                            movieAdapter.changeData(movieBean.getResult());
                            SearchDatabase searchDatabase = new SearchDatabase();
                            searchDatabase.execute(movieName);
                        }else {
                            Toast.makeText(getActivity(),"The request failed",Toast.LENGTH_SHORT).show();
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
    public void iOnClickCheck(SearchMovieBean.ResultBean movieId) {
        // 跳转之前先拼接
        Intent intent = new Intent(getActivity(), MovieInfoActivity.class);
        intent.putExtra("bean",movieId);
        startActivity(intent);
    }

    private class SearchDatabase extends AsyncTask<String, Void, MovieBean.SubjectsBean> {
        @Override
        protected MovieBean.SubjectsBean doInBackground(String... params) {

            WatchBean watchBean = wathcBeanDao.findByUsername(SharedPreferenceUtil.getInstance(getActivity()).getUserName());

            boolean isAdd = false;
            if (watchBean == null) {
                isAdd = true;
                watchBean = new WatchBean();
            }

            List<String> byUsername = watchBean.getMovieTitle();

            if (byUsername == null || byUsername.size() == 0) {
                byUsername = new ArrayList<>();
            }
            boolean isHave = false;
            for (String title : byUsername) {
                if (title.equals(params[0])) {
                    isHave = true;
                }
            }
            if (!isHave) {
                byUsername.add(params[0]);
            }

            watchBean.setUserName(SharedPreferenceUtil.getInstance(getActivity()).getUserName());
            watchBean.setMovieTitle(byUsername);

            Log.e("SSSS","add search item："+watchBean.toString());

            if (isAdd) {
                wathcBeanDao.insert(watchBean);
            }else {
                wathcBeanDao.updateCustomer(watchBean);
            }

            return null;
        }

        @Override
        protected void onPostExecute(MovieBean.SubjectsBean details) {
        }
    }
}
