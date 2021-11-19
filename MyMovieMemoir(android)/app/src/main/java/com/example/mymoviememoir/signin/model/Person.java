package com.example.mymoviememoir.signin.model;

import android.icu.text.SimpleDateFormat;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.mymoviememoir.signin.model.Credential;

import java.util.Date;
import java.util.Locale;

public class Person {
    private Integer perID;
    private String fname;
    private String sname;
    private String gender;
    private String DoB;
    private String address;
    private String pstate;
    private Integer postcode;

    public Person(Integer perID) {
        this.perID = perID;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public Person(Integer perID, String fname, String sname, String gender, Date DoB, String address, String pstate, Integer postcode) {
        this.perID = perID;
        this.fname = fname;
        this.sname = sname;
        this.gender = gender;
        this.DoB = localToUTC(DoB);
        this.address = address;
        this.pstate = pstate;
        this.postcode = postcode;
    }

    public Person(Integer perID, String fname, String sname, String gender, String DoB, String address, String pstate, Integer postcode) {
        this.perID = perID;
        this.fname = fname;
        this.sname = sname;
        this.gender = gender;
        this.DoB = DoB;
        this.address = address;
        this.pstate = pstate;
        this.postcode = postcode;
    }

    public Integer getPersonId() {
        return perID;
    }

    public void setPersonId(Integer perID) {
        this.perID = perID;
    }

    public String getFirstName() {
        return fname;
    }

    public void setFirstName(String fname) {
        this.fname = fname;
    }

    public String getSurname() {
        return sname;
    }

    public void setSurname(String sname) {
        this.sname = sname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDob() { return DoB; }

    public void setDob(String DoB) { this.DoB = DoB; }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPersonState() {
        return pstate;
    }

    public void setPersonState(String pstate) {
        this.pstate = pstate;
    }

    public Integer getPostcode() {
        return postcode;
    }

    public void setPostcode(Integer postcode) {
        this.postcode = postcode;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private String localToUTC(Date localDate) {
        Locale Australia = new Locale ("en", "AT");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX", Australia);
        return sdf.format(localDate);
    }

}