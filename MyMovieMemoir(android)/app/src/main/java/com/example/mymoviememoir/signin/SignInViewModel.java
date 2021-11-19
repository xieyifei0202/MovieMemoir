package com.example.mymoviememoir.signin;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mymoviememoir.bean.PersonBean;
import com.example.mymoviememoir.networking.NetworkConnection;
import com.example.mymoviememoir.shared_preference.SharedPreferenceUtil;
import com.example.mymoviememoir.signin.model.Credential;
import com.example.mymoviememoir.signin.model.Person;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.Date;
import java.util.List;

@RequiresApi(api = Build.VERSION_CODES.N)
public class SignInViewModel extends ViewModel {

    NetworkConnection networkConnection = new NetworkConnection();
    //    Person currentPerson = new Person(1, "firstName", "surname", "Male", new Date(), "address", "personState", 3168);
    Credential currentUser = new Credential(1, "Test", "1234", new Date(), new Person(0));


    private MutableLiveData<Integer> signInResultLiveDate;
    private Context context;

    Gson gson;
    public SignInViewModel() {
        gson = new Gson();
        signInResultLiveDate = new MutableLiveData<>();
        signInResultLiveDate.setValue(99);
    }

    public void initContext(Context context) {
        this.context = context;
    }

    public void singInProcess(String username, String password) {
        try {
            GetCredentialTask getCredentialTask = new GetCredentialTask();
            getCredentialTask.execute(username, password);
        } catch (Exception e) {
            signInResultLiveDate.setValue(1);
        }
    }


    public LiveData<Integer> getSignInResultLiveDate() {
        return signInResultLiveDate;
    }

    private class GetCredentialTask extends AsyncTask<String, Void, String> {
        @RequiresApi(api = Build.VERSION_CODES.Q)
        @Override
        protected String doInBackground(String... params) {
            return networkConnection.getCredential(params[0], params[1]);
        }

        @Override
        protected void onPostExecute(String result) {
            int credentialid = 999;
            String username = "";
            String passwordhash = "";
            String signupDate = "";

            try {
                List<PersonBean> personBeans = gson.fromJson(result, new TypeToken<List<PersonBean>>() {
                }.getType());

                credentialid = personBeans.get(0).getCreid();
                passwordhash = personBeans.get(0).getPasswordhash();
                signupDate = personBeans.get(0).getSignupdate();
                username = personBeans.get(0).getUsername();
                currentUser =
                        new Credential(
                                credentialid,
                                passwordhash,
                                signupDate,
                                username
                        );
                SharedPreferenceUtil sp = SharedPreferenceUtil.getInstance(context);
                sp.putInt("creId", currentUser.getCredentialId());
                sp.putString("sp_user_name", currentUser.getUsername());
                sp.putString("signupDate", currentUser.getSignUpDate());

                signInResultLiveDate.setValue(0);
            }catch (Exception e) {
                signInResultLiveDate.setValue(1);

            }
        }
    }
}

