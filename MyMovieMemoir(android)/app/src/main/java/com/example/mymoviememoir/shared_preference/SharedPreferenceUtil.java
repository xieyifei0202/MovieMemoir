package com.example.mymoviememoir.shared_preference;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferenceUtil {
    private static SharedPreferenceUtil sharedPreferenceUtil;
    private static SharedPreferences sharedPreferences;
    private static SharedPreferences userSP;


    private final static String KEY = "sp_general";

    private final static String KEY_USER = "sp_user";
    private final static String KEY_USER_NAME = "sp_user_name";
    private final static String KEY_USER_DOB = "sp_user_dob";


    private SharedPreferenceUtil(Context context) {
        sharedPreferences = context.getSharedPreferences(KEY, Context.MODE_PRIVATE);
        userSP = context.getSharedPreferences(KEY_USER, Context.MODE_PRIVATE);
    }

    public static SharedPreferenceUtil getInstance(Context context) {
        if (sharedPreferenceUtil == null) {
            sharedPreferenceUtil = new SharedPreferenceUtil(context);
        }
        return sharedPreferenceUtil;
    }


    public void putUserName(String value) {
        SharedPreferences.Editor editor = userSP.edit();
        editor.putString(KEY_USER_NAME, value);
        editor.apply();
    }
    public String getUserName() {
        return getString(KEY_USER_NAME, "");
    }

    public void putUserDOB(String value) {
        SharedPreferences.Editor editor = userSP.edit();
        editor.putString(KEY_USER_DOB, value);
        editor.apply();
    }

    public void putString(String key, String value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public void putLong(String key, long value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putLong(key, value);
        editor.apply();
    }

    public void putInt(String key, int value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public void putBoolean(String key, boolean value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public void putFloat(String key, float value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putFloat(key, value);
        editor.apply();
    }

    public String getString(String key) {
        return getString(key, "");
    }

    public String getString(String key, String defaultValue) {
        return sharedPreferences.getString(key, defaultValue);
    }

    public boolean getBoolean(String key) {
        return getBoolean(key, false);
    }

    public boolean getBoolean(String key, boolean defaultValue) {
        return sharedPreferences.getBoolean(key, defaultValue);
    }

    public int getInt(String key) {
        return getInt(key, 0);
    }

    public int getInt(String key, int defaultValue) {
        return sharedPreferences.getInt(key, defaultValue);
    }

    public long getLong(String key) {
        return getLong(key, 0L);
    }

    public long getLong(String key, Long defaultValue) {
        return sharedPreferences.getLong(key, defaultValue);
    }

    public float getFloat(String key) {
        return getFloat(key, 0f);
    }

    public float getFloat(String key, Float defaultValue) {
        return sharedPreferences.getFloat(key, defaultValue);
    }

    public boolean contains(String key) {
        return sharedPreferences.contains(key);
    }

    public boolean has(String key) {
        return sharedPreferences.contains(key);
    }

    public boolean remove(String key) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(key);
        return editor.commit();
    }

    public boolean removeAll() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        return editor.commit();
    }
}
