package com.example.mymoviememoir;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.mymoviememoir.bean.MovieBean;
import com.example.mymoviememoir.bean.WatchBean;
import com.example.mymoviememoir.bean.WatchBeanDB;
import com.example.mymoviememoir.bean.WathcBeanDao;
import com.example.mymoviememoir.database.SearchMovieBean;
import com.example.mymoviememoir.shared_preference.SharedPreferenceUtil;
import com.example.mymoviememoir.utils.StarBar;

import java.util.ArrayList;
import java.util.List;

public class MovieInfoActivity extends AppCompatActivity implements View.OnClickListener {

    private SearchMovieBean.ResultBean resultBean;

    ImageView iv_img;
    TextView tv_title;
    TextView tv_star;
    StarBar rs_view;
    TextView tv_content;
    TextView tv_publish_time;
    TextView tv_time;
    TextView tv_w;
    TextView tv_m;
    TextView tv_write;
    TextView tv_info;
    private WathcBeanDao wathcBeanDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_info);
        tv_w = findViewById(R.id.tv_w);
        tv_m = findViewById(R.id.tv_m);
        iv_img = findViewById(R.id.iv_img);
        tv_title = findViewById(R.id.tv_title);
        tv_star = findViewById(R.id.tv_star);
        rs_view = findViewById(R.id.rs_view);
        tv_content = findViewById(R.id.tv_content);
        tv_publish_time = findViewById(R.id.tv_publish_time);
        tv_time = findViewById(R.id.tv_time);
        tv_write = findViewById(R.id.tv_write);
        tv_info = findViewById(R.id.tv_info);

        resultBean = (SearchMovieBean.ResultBean) getIntent().getSerializableExtra("bean");


        Glide.with(this).load(resultBean.getPoster()).into(iv_img);

        tv_title.setText(resultBean.getTitle() + "");

        String rating = resultBean.getRating();

        if (rating != null && !"".equals(rating)) {
            rs_view.setStarMark((float) (Double.parseDouble(rating) / 2.0f));
            tv_star.setText(rating);
        }else {
            rs_view.setStarMark(0);
            tv_star.setText("0");
        }

        tv_content.setText("actors："+resultBean.getActors());

        tv_write.setText("directors："+resultBean.getDirectors());

        String mainland_pubdate = resultBean.getRelease_date();

        if (mainland_pubdate == null || "".equals(mainland_pubdate)) {
            mainland_pubdate = "nothing";
        }

        tv_publish_time.setText("Release time："+mainland_pubdate);

        tv_time.setText("Running time："+resultBean.getRuntime());

        tv_info.setText("Content abstract："+resultBean.getPlot_simple());

        tv_w.setOnClickListener(this);

        wathcBeanDao = WatchBeanDB.getInstance(this).watchDAO();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_w:
                SearchDatabase searchDatabase = new SearchDatabase();
                searchDatabase.execute(tv_title.getText().toString());
                break;
            case R.id.tv_m:

                break;
        }
    }

    private class SearchDatabase extends AsyncTask<String, Void, MovieBean.SubjectsBean> {
        @Override
        protected MovieBean.SubjectsBean doInBackground(String... params) {

            WatchBean watchBean = wathcBeanDao.findByUsername(SharedPreferenceUtil.getInstance(MovieInfoActivity.this).getUserName());

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

            watchBean.setUserName(SharedPreferenceUtil.getInstance(MovieInfoActivity.this).getUserName());
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
