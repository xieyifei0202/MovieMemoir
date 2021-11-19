package com.example.mymoviememoir.signin.model;

import android.icu.text.SimpleDateFormat;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.Date;
import java.util.Locale;

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
public class Credential {

    private Integer creID;
    private String username;
    private String passwordHash;
    private String signupDate;
    private Person perID;

    public Credential(Integer creId) {
        this.creID = creId;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public Credential(Integer creID, String username, String passwordHash, Date signupDate, Person perID) {
        this.creID = creID;
        this.username = username;
        this.passwordHash = passwordHash;
        this.signupDate = localToUTC(signupDate);
        this.perID = perID;
    }

    public Credential(Integer credID, String username, String passwordHash, String signupDate) {
        this.creID = credID;
        this.username = username;
        this.passwordHash = passwordHash;
        this.signupDate = signupDate;
    }

    public Integer getCredentialId() {
        return creID;
    }

    public void setCredentialId(Integer creID) {
        this.creID = creID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getSignUpDate() {
        return signupDate;
    }

    public void setSignUpDate(String signupDate) {
        this.signupDate = signupDate;
    }

    public Person getPersonId() {
        return perID;
    }

    public void setPersonId(Person perID) {
        this.perID = perID;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private String localToUTC(Date localDate) {
        Locale Australia = new Locale ("en", "AT");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX", Australia);
        return sdf.format(localDate);
    }
}
