package com.example.mymoviememoir.function.facebook;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.Events;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

public class FacebookViewModel extends ViewModel {
    private MutableLiveData<String> calender;
    private static final String APPLICATION_NAME = "Google Calendar API Java Quickstart";
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private static final String TOKENS_DIRECTORY_PATH = "tokens";
    private Context mContext;

    public FacebookViewModel() {
        calender = new MutableLiveData<>();
    }

    public void init(Context context) {
        mContext = context;
    }

    public void getCalenderProcessing() {
        GetEventTask task = new GetEventTask();
        task.execute();
    }

    public LiveData<String> getText() {
        return calender;
    }

    private class GetEventTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            String result = "";
            FacebookQuickStart quickstart = new FacebookQuickStart();
            final NetHttpTransport HTTP_TRANSPORT;
            try {
                HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
                Calendar service = null;
                service = new Calendar.Builder(HTTP_TRANSPORT, JSON_FACTORY, quickstart.getCredentials(HTTP_TRANSPORT))
                        .setApplicationName(APPLICATION_NAME)
                        .build();
                DateTime now = new DateTime(System.currentTimeMillis());
                Events events = service.events().list("primary")
                        .setMaxResults(10)
                        .setTimeMin(now)
                        .setOrderBy("startTime")
                        .setSingleEvents(true)
                        .execute();
                List<Event> items = events.getItems();
                if (items.isEmpty()) {
                    result = "No upcoming events found.";
                } else {
                    result = "Upcoming events";
                    for (Event event : items) {
                        DateTime start = event.getStart().getDateTime();
                        if (start == null) {
                            start = event.getStart().getDate();
                        }
                        result = result + ("%s (%s)\n" + event.getSummary() + start);
                    }
                }
            } catch (GeneralSecurityException | IOException e) {
                e.printStackTrace();
            }

            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            calender.setValue(result);
        }
    }

}
