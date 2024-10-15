package com.example.aps_test.ui.second.production.setup;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.APS_test.R;
import com.example.aps_test.sharedPreferences.SP;


public class SetupActivity extends AppCompatActivity {

    private ImageView backImageView;
    private TextView NameTextView;
    private SP sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_setup);

        Intent intent = getIntent();

        backImageView = findViewById(R.id.setup_back_iv);
        NameTextView = findViewById(R.id.setup_name_tv);
        sp = new SP(this);
        NameTextView.setText(sp.loadName());

        backImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}