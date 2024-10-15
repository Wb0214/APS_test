package com.example.aps_test.sharedPreferences;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.APS_test.R;


public class SP {
    private SharedPreferences spf;
    private Context context;
    private static String TOKEN = "token";
    private static String NAME = "name";

    public SP(Context context) {
        this.context = context;
        spf = this.context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE);
    }

    public void saveToken(String token) {
        spf.edit().putString(TOKEN, token).apply();
    }
    public String loadToken() {
        return spf.getString(TOKEN," ");
    }
  ////////////////////////////////////////////////////////////////////////////////
    public void saveName(String name) {
        spf.edit().putString(NAME, name).apply();
    }
    public String loadName() {
        return spf.getString(NAME," ");
    }
    ////////////////////////////////////////////////////////////////////////////////
}
