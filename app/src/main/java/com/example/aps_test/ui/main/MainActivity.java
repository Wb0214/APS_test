package com.example.aps_test.ui.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.aps_test.R;
import com.example.aps_test.ui.second.SecondActivity;

public class MainActivity extends AppCompatActivity implements MainContract.view{

    private EditText accountEditText,passwordEditText;
    private Button LogInButton;

    private Context context = this;
    private MainPresenter mainpresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        accountEditText = findViewById(R.id.main_account_et);
        passwordEditText = findViewById(R.id.main_password_et);
        LogInButton = findViewById(R.id.main_LogIn_btn);

        mainpresenter = new MainPresenter(this);

        //登入按鈕
        LogInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainpresenter.getData(accountEditText.getText().toString(),passwordEditText.getText().toString());
            }
        });
    }

    @Override
    public void isError(String s){
        Toast.makeText(context,s,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void correct(){
        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        startActivity(intent);
    }
}