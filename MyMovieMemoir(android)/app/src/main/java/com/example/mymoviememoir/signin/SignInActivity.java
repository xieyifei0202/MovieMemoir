package com.example.mymoviememoir.signin;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.mymoviememoir.MainActivity;
import com.example.mymoviememoir.R;
import com.example.mymoviememoir.signin.signup.SignUpFragment;

import static android.content.ContentValues.TAG;


public class SignInActivity extends AppCompatActivity {

    private SignInViewModel signInViewModel;

    private EditText et_username;
    private EditText et_password;
    private Button btn_signIn;
    private Button btn_signUp;
    private Button btn_forget;
    private ProgressBar progressBar;



    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        signInViewModel = new ViewModelProvider(this).get(SignInViewModel.class);
        signInViewModel.initContext(this);
        et_username = findViewById(R.id.username);
        et_password = findViewById(R.id.password);
        btn_signIn = findViewById(R.id.btnSignin);
        btn_signUp = findViewById(R.id.btnSignup);
        btn_forget = findViewById(R.id.btn_forget);
        progressBar = findViewById(R.id.progressbar);

        btn_signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
//                Intent intent = new Intent(SignInActivity.this, testActivity.class);
//                startActivity(intent);
                signInViewModel.singInProcess(et_username.getText().toString().trim(), et_password.getText().toString().trim());
            }
        });

        signInViewModel.getSignInResultLiveDate().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                if (integer == 0) {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(getApplicationContext(),
                            "Sign in successful",
                            Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SignInActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();

                } else if (integer == 1) {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(getApplicationContext(),
                            "Sign in failed",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateTo(SignUpFragment.newInstance());
            }


        });

        btn_forget.setOnClickListener(new View.OnClickListener() {
            //This is for quickly entering the default user information to test
            @Override
            public void onClick(View v) {
                et_username.setText("yxie0034@student.monash.edu");
                Log.d(TAG,et_username.getText().toString() );


                et_password.setText("101");
                btn_signIn.callOnClick();
            }
        });

    }

    private void replaceFragment(Fragment nextFragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.content_frame, nextFragment);

        fragmentTransaction.commit();
    }

    private void replaceFragmentWithBundle(Fragment nextFragment, Bundle bundle) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.content_frame, nextFragment);
        nextFragment.setArguments(bundle);
        fragmentTransaction.commit();
    }
    @Override
    protected void onResume() {
        super.onResume();
        progressBar.setVisibility(View.GONE);

    }

    private void navigateTo(Fragment fragment) {
        String NAVIGATION_TAG = "nav";
        this.getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container_sign_in, fragment)
                .addToBackStack(NAVIGATION_TAG)
                .commit();
    }


}
