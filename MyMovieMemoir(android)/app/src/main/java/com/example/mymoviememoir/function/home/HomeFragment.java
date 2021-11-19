package com.example.mymoviememoir.function.home;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.mymoviememoir.Main2Activity;
import com.example.mymoviememoir.R;
import com.example.mymoviememoir.adapter.HomeMovieAdapter;
import com.example.mymoviememoir.bean.MovieBean;
import com.example.mymoviememoir.bean.MovieBeanDB;
import com.example.mymoviememoir.bean.MovieBeanDao;
import com.example.mymoviememoir.networking.NetWorkHttp;
import com.example.mymoviememoir.networking.RxSchedulersHelper;
import com.example.mymoviememoir.shared_preference.SharedPreferenceUtil;

import java.util.Calendar;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import static com.example.mymoviememoir.networking.NetWorkHttp.API_BASE_URL;

public class HomeFragment extends Fragment implements HomeMovieAdapter.IOnClickAdd {

    private HomeViewModel homeViewModel;

    RecyclerView rlv_view;
    TextView tv_title;
    SwipeRefreshLayout sl_view;
    ProgressBar pb_bar;
    TextView tv_time;
    private HomeMovieAdapter movieAdapter;
    private MovieBeanDao movieBeanDao;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);

        View root = inflater.inflate(R.layout.fragment_home, container, false);

        initView(root);

        return root;
    }

    private void initView(View root) {
        rlv_view = root.findViewById(R.id.rlv_view);
        sl_view = root.findViewById(R.id.sl_view);
        pb_bar = root.findViewById(R.id.pb_bar);
        tv_title = root.findViewById(R.id.tv_title);
        tv_time = root.findViewById(R.id.tv_time);

        movieBeanDao = MovieBeanDB.getInstance(getActivity()).movieDAO();

        rlv_view.setLayoutManager(new LinearLayoutManager(getActivity()));

        movieAdapter = new HomeMovieAdapter(getActivity());

        rlv_view.setAdapter(movieAdapter);

        movieAdapter.setiOnClickAdd(this);

        requestHttp();

        sl_view.setColorSchemeColors(Color.parseColor("#0077ff"));

        sl_view.setOnRefreshListener(() -> requestHttp());

        String sp_user_name = SharedPreferenceUtil.getInstance(getActivity()).getString("sp_user_name");

        tv_title.setText(sp_user_name+",Welcome back");

        Calendar calendar = Calendar.getInstance();

        tv_time.setText("The current time："+calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH + 1)) + "-"+ calendar.get(Calendar.DAY_OF_MONTH));

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
                        sl_view.setRefreshing(false);
                        pb_bar.setVisibility(View.GONE);
                        if (movieAdapter != null) {
                            movieAdapter.changeData(movieBean.getSubjects());
                        }

                        addMovieBean(movieBean.getSubjects());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void addMovieBean(List<MovieBean.SubjectsBean> subjects) {
        for (MovieBean.SubjectsBean subjectsBean : subjects) {
            SearchDatabase searchDatabase = new SearchDatabase();
            searchDatabase.execute(subjectsBean);
        }
    }

    @Override
    public void iOnClickAddGallery(MovieBean.SubjectsBean subjectsBean) {
        SearchGalleryDatabase galleryDatabase = new SearchGalleryDatabase();
        galleryDatabase.execute(subjectsBean);
    }

    @Override
    public void iOnClickCheck(String subjectsBean) {
        Intent intent = new Intent(getActivity(), Main2Activity.class);
        intent.putExtra("path",subjectsBean);
        startActivity(intent);
    }

    private class SearchGalleryDatabase extends AsyncTask<MovieBean.SubjectsBean, Void, MovieBean.SubjectsBean> {
        @Override
        protected MovieBean.SubjectsBean doInBackground(MovieBean.SubjectsBean... params) {

            MovieBean.SubjectsBean byId = movieBeanDao.findById(params[0].getId());
            if (byId.getIsGallery() == 1) {
                return null;
            }
            byId.setIsGallery(1);
            movieBeanDao.updateCustomer(byId);
            return byId;
        }

        @Override
        protected void onPostExecute(MovieBean.SubjectsBean details) {
            if (details == null) {
                Toast.makeText(getActivity(),
                        "You have added the current movie",
                        Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(getActivity(),
                        "Add a success",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }

    private class SearchDatabase extends AsyncTask<MovieBean.SubjectsBean, Void, MovieBean.SubjectsBean> {
        @Override
        protected MovieBean.SubjectsBean doInBackground(MovieBean.SubjectsBean... params) {

            MovieBean.SubjectsBean byId = movieBeanDao.findById(params[0].getId());
            if (byId == null) {
                params[0].setUserName(SharedPreferenceUtil.getInstance(getActivity()).getUserName());
                movieBeanDao.insert(params[0]);

                return params[0];
            }
            return null;
        }

        @Override
        protected void onPostExecute(MovieBean.SubjectsBean details) {
        }
    }

}
