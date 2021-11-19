package com.example.mymoviememoir.signin.signup;

import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mymoviememoir.networking.NetworkConnection;
import com.example.mymoviememoir.signin.model.Person;


public class SignUpViewModel extends ViewModel {
    NetworkConnection networkConnection = new NetworkConnection();
    Person person = new Person(0);
    private MutableLiveData<Integer> addPersonResult;

    public SignUpViewModel() {
        addPersonResult = new MutableLiveData<>();
    }

    public LiveData<Integer> getAddPersonResult() {
        return addPersonResult;
    }


    public void singUpProcessing(Person person){
        try {
            AddPersonTask addPersonTask = new AddPersonTask();
            addPersonTask.execute(person);
        } catch (Exception e) {
            addPersonResult.setValue(1);
        }
    }

    private class AddPersonTask extends AsyncTask<Person, Void, String> {
        @Override
        protected String doInBackground(Person... params) {
            return networkConnection.addPerson(params[0]);
        }

        @Override
        protected void onPostExecute(String result) {
            if (result.equals("204"))
                addPersonResult.setValue(0);
            else
                addPersonResult.setValue(1);
            Log.d("sign_in",result);
        }
    }

}

