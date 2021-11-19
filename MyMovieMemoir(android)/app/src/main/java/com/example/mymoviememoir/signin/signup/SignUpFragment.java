package com.example.mymoviememoir.signin.signup;

import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.mymoviememoir.R;
import com.example.mymoviememoir.shared_preference.SharedPreferenceUtil;
import com.example.mymoviememoir.signin.SignInActivity;
import com.example.mymoviememoir.signin.model.Credential;
import com.example.mymoviememoir.signin.model.Person;

import java.util.Date;
import java.util.Locale;

import static android.widget.RadioGroup.OnCheckedChangeListener;

public class SignUpFragment extends Fragment {

    private SignUpViewModel signUpViewModel;
    private EditText et_fistName;
    private EditText et_surName;
    private RadioGroup rb_gender;
    private DatePicker dp_dob;
    private EditText et_address;
    private Spinner spinner_state;
    private EditText et_postcode;
    private EditText et_email;
    private EditText et_passwrod;
    private Button bt_confirm;
    private ProgressBar progressBar;

    private String gender = "Male";
    private String dob;

    public static SignUpFragment newInstance() {
        return new SignUpFragment();
    }
    public SignUpFragment() {}

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_signup, container, false);
        et_fistName = view.findViewById(R.id.et_first_name);
        et_surName = view.findViewById(R.id.et_sur_name);
        rb_gender = view.findViewById(R.id.radio_button_gender);
        dp_dob = view.findViewById(R.id.date_picker_dob);
        et_address = view.findViewById(R.id.et_address);
        spinner_state = view.findViewById(R.id.spinner_state);
        et_postcode = view.findViewById(R.id.et_postcode);
        et_email = view.findViewById(R.id.et_email);
        et_passwrod = view.findViewById(R.id.et_password);
        bt_confirm = view.findViewById(R.id.bt_confirm);
        progressBar = view.findViewById(R.id.progressbar);
        signUpViewModel = new ViewModelProvider(this).get(SignUpViewModel.class);

        rb_gender.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radio_male:
                        gender = "Male";
                        break;
                    case R.id.radio_female:
                        gender = "Female";
                        break;
                }
            }
        });

        Calendar c = Calendar.getInstance();
        c.set(1990,1,1);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX", Locale.CHINA);
        dob = sdf.format(c);

        dp_dob.init(1990, 1, 1, new DatePicker.OnDateChangedListener() {

            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onDateChanged(DatePicker view, int year,
                                      int monthOfYear, int dayOfMonth) {
                Calendar c = Calendar.getInstance();
                c.set(year,monthOfYear,dayOfMonth);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX", Locale.CHINA);
                dob = sdf.format(c);
                Toast.makeText(getContext(),
                        "Date: " + dob,
                        Toast.LENGTH_SHORT).show();
            }
        });

        bt_confirm.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                signUpViewModel.singUpProcessing(initPerson());
            }
        });
        signUpViewModel.getAddPersonResult().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                progressBar.setVisibility(View.GONE);
                if (integer == 0) {
                    Toast.makeText(getContext(),
                            "Sign in succeed",
                            Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getContext(), SignInActivity.class));
                } else if (integer == 1) {
                    Toast.makeText(getContext(),
                            "Sign in failed",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });


        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private Person initPerson() {
        int creid = 11;
        int perid = 22;
        Person person = new Person(0);

        person.setPersonId(perid);//should get from server, last the largest id
        person.setFirstName(et_fistName.getText().toString().trim());
        person.setSurname(et_surName.getText().toString().trim());
        person.setGender(gender);
        person.setAddress(et_address.getText().toString().trim());
        person.setDob(dob);
        person.setPersonState(spinner_state.getSelectedItem().toString().trim());
        person.setPostcode(Integer.valueOf(et_postcode.getText().toString().trim()));

        SharedPreferenceUtil.getInstance(getActivity()).putString("address",et_address.getText().toString().trim());

        Credential credential = new Credential(creid,
                et_email.getText().toString().trim(),
                et_passwrod.getText().toString().trim(),
                new Date(),
                person);
        return person;
    }

}
