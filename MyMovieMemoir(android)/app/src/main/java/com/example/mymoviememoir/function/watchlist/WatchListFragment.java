package com.example.mymoviememoir.function.watchlist;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.mymoviememoir.R;
import com.example.mymoviememoir.bean.WatchBean;
import com.example.mymoviememoir.bean.WatchBeanDB;
import com.example.mymoviememoir.bean.WathcBeanDao;
import com.example.mymoviememoir.shared_preference.SharedPreferenceUtil;

import java.util.List;

public class WatchListFragment extends Fragment {

    private WatchListViewModel watchListViewModel;
    private WathcBeanDao wathcBeanDao;
    private TextView textView;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        watchListViewModel = new ViewModelProvider(this).get(WatchListViewModel.class);
        View root = inflater.inflate(R.layout.fragment_watchlist, container, false);
        textView = root.findViewById(R.id.text_movie);

        wathcBeanDao = WatchBeanDB.getInstance(getActivity()).watchDAO();

        SearchDatabase searchDatabase = new SearchDatabase();
        searchDatabase.execute("");

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (getArguments() != null && getArguments().getInt("alarm",0) == 1) {
            // do delete some movies in watchlist
            Toast.makeText(getContext(), "Alarm service Activate", Toast.LENGTH_LONG).show();
        }
    }

    private class SearchDatabase extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {

            WatchBean watchBean = wathcBeanDao.findByUsername(SharedPreferenceUtil.getInstance(getActivity()).getUserName());

            Log.e("SSSS","storage："+SharedPreferenceUtil.getInstance(getActivity()).getUserName() + "：："+(watchBean == null));
            if (watchBean == null) {
                watchBean = new WatchBean();
            }

            List<String> byUsername = watchBean.getMovieTitle();

            String details = "";
            if (byUsername == null || byUsername.size() == 0) {
                details = "The current search list is empty";
            }else {
                for (String title : byUsername) {
                    if (title == null || "".equals(title)) {
                        details = title;
                    }else {
                        details = details + "\n" + title;
                    }
                }
            }
            return details;

        }

        @Override
        protected void onPostExecute(String details) {
            textView.setText(details);
        }
    }
}