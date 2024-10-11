package com.example.aps_test.sharedPreferences;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.APS_test.R;


public class SP {
    private SharedPreferences spf;
    private Context context;
    private static String TOKEN = "token";


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
}
