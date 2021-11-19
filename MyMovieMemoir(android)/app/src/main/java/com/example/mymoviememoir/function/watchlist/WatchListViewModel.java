package com.example.mymoviememoir.function.watchlist;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mymoviememoir.database.Movie;
import com.example.mymoviememoir.database.MovieDB;

import java.util.List;

public class WatchListViewModel extends ViewModel {

    private MutableLiveData<List<Movie>> movieList;
    private MutableLiveData<String> movieListStr;
    MovieDB db = null;

    private int id = 0;

    public WatchListViewModel() {
        movieList = new MutableLiveData<>();
        movieListStr = new MutableLiveData<>();
    }

    public void initDB(Context context){
        db = MovieDB.getInstance(context);
    }

    public void readMovieList() {
        ReadDatabase readDatabase = new ReadDatabase();
        readDatabase.execute();
    }

    public void insertMovie(Movie movie){
        movie.setMovieID(++id);
        InsertDatabase insertDatabase = new InsertDatabase();
        insertDatabase.execute(movie);
    }

    public LiveData<List<Movie>> getMovieList() {
        return movieList;
    }

    public LiveData<String> getMovieListStr() {
        return movieListStr;
    }


    private class ReadDatabase extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... params) {
            List<Movie> movies = db.movieDAO().getAll();
            if (!(movies.isEmpty() || movies == null)) {
                String allUsers = "";
                for (Movie temp : movies) {
                    String tempStr = (temp.getMovieID() + " " + temp.getMovieName() +
                            " " + temp.getReleaseDate() + " " + temp.getAddDate() + " , ");
                    allUsers = allUsers + System.getProperty("line.separator") +
                            tempStr;
                    id = temp.getMovieID();
                }
                return allUsers;
            } else
                return "";
        }

        @Override
        protected void onPostExecute(String details) {
            // 需要返回值判断，成功succeed/失败failed，返回值不同
            movieListStr.setValue("All data: " + details);
        }
    }


    private class InsertDatabase extends AsyncTask<Movie, Void, String> {
        @Override
        protected String doInBackground(Movie... params) {
//            Movie movie = new Movie(params[0], params[1],
//                    Double.parseDouble(params[2]));
            long id = db.movieDAO().insert(params[0]);
            return (id + " "
                    + params[0].getMovieID() + " "
                    + params[0].getMovieName() + " "
                    + params[0].getReleaseDate() + " "
                    + params[0].getAddDate());
        }

        @Override
        protected void onPostExecute(String details) {
            movieListStr.setValue("Added Record: " + details);
        }
    }

}