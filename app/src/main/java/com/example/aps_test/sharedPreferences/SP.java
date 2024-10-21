package com.example.aps_test.sharedPreferences;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.APS_test.R;


public class SP {
    private SharedPreferences spf;
    private Context context;
    private static String TOKEN = "token";
    private static String NAME = "name";
    private static String SOID = "soId";
    private static String CUSTOMERNAME = "customerName";

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
    public void saveSoId(String soId) {
        spf.edit().putString(SOID, soId).apply();
    }

    public String loadSoId() {
        // 讀取 soId，若為空則返回空白字串
        return spf.getString(SOID, " ");
    }

    ////////////////////////////////////////////////////////////////////////////////
    public void saveCustomerName(String customerName) {
        spf.edit().putString(CUSTOMERNAME, customerName).apply();
    }
    public String loadCustomerName() {
        return spf.getString(CUSTOMERNAME," ");
    }
}
