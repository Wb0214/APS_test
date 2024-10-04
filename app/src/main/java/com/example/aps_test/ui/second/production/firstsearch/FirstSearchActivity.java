package com.example.aps_test.ui.second.production.firstsearch;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.aps_test.R;
import com.example.aps_test.ui.second.production.firstsearch.search_schedule.SearchScheduleActivity;

import java.util.Calendar;

public class FirstSearchActivity extends AppCompatActivity implements FirstSearchContract.view{
    private Button searchButton;;
    private ImageView backButton;
    private TextView dot1TextView,dot2TextView,dot3TextView;
    private EditText dataEditText, numEditText, personEditText;
    private Spinner listSpinner;

    private FirstSearchPresenter firstSearchPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_first_search);

        Intent intent = getIntent();

        backButton = findViewById(R.id.firstsearch_back_iv);
        searchButton = findViewById(R.id.firstsearch_search_btn);
        dot1TextView = findViewById(R.id.firstsearch_dot1_tv);
        dot2TextView = findViewById(R.id.firstsearch_dot2_tv);
        dot3TextView = findViewById(R.id.firstsearch_dot3_tv);
        dataEditText = findViewById(R.id.firstsearch_data_et);
        numEditText = findViewById(R.id.firstsearch_num_et);
        personEditText = findViewById(R.id.firstsearch_person_et);
        listSpinner = findViewById(R.id.firstsearch_list_sp);

        firstSearchPresenter =  new FirstSearchPresenter(this);
        //返回
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //確定跳轉
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FirstSearchActivity.this, SearchScheduleActivity.class);
                startActivity(intent);
            }
        });

        //Spinner
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,new String[]{"點焊"});
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_shape);
        listSpinner.setAdapter(adapter);

        //dialog
        //日期
        dot1TextView.setOnClickListener((v) -> {
            Calendar calendar = Calendar.getInstance();
            DatePickerDialog dialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                // 當日期選擇完畢並按下"OK"按鈕 的事件觸發處理
                @Override
                public void onDateSet(DatePicker datePicker, int y, int m, int d) {
                    StringBuilder result = new StringBuilder();
                    result.append(y).append("-").append(m+1).append("-").append(d);
                    dataEditText.setText(result);
                }
            },calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
            dialog.show();
        });

        //輸入單號
        dot2TextView.setOnClickListener((v) -> {
            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            final EditText editText = new EditText(this);
            dialog.setTitle("輸入單號");
            dialog.setView(editText);

            //設置左邊按鈕和點擊事件
            dialog.setNegativeButton("關閉", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    // TODO: ...
                }
            });
            //設置右邊按鈕和點擊事件
            dialog.setPositiveButton("確定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    // TODO: ...
                    numEditText.setText(editText.getText().toString());
                }
            });
            //顯示Dialog
            dialog.show();
        });

        //輸入客戶
        dot3TextView.setOnClickListener((v) -> {
            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            final EditText editText2 = new EditText(this);
            dialog.setTitle("輸入客戶");
            dialog.setView(editText2);
            //設置左邊按鈕和點擊事件
            dialog.setNegativeButton("關閉", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    // TODO: ...
                }
            });
            //設置右邊按鈕和點擊事件
            dialog.setPositiveButton("確定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    // TODO: ...
                    personEditText.setText(editText2.getText().toString());
                }
            });
            //顯示Dialog
            dialog.show();
        });
    }
}